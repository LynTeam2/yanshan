package com.xingkong.lyn.shiro;

import com.xingkong.lyn.model.SysPermission;
import com.xingkong.lyn.model.SysRole;
import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.service.IUserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by lyn on 2017/5/17.
 */
public class MyShiroRealm extends AuthorizingRealm{

    private Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Resource
    private IUserInfo userInfoService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //用户登录次数计数  redisKey 前缀
    private static final String SHIRO_LOGIN_COUNT = "shiro_login_count:";

    //用户登录是否被锁定    一小时 redisKey 前缀
    private static final String SHIRO_IS_LOCK = "shiro_is_lock:";

    @Value(value = "${shiro.lock.count}")
    private int SHIRO_LOCK_COUNT;

    /**
     * 认证信息(身份认证)
     * @param token
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");

        //获取用户的输入账号
        String username = (String)token.getPrincipal();
        System.out.println(token.getCredentials());

        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.increment(SHIRO_LOGIN_COUNT + username, 1);
        //计数大于限定值时锁定用户
        if(Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT + username)) > SHIRO_LOCK_COUNT){
            opsForValue.set(SHIRO_IS_LOCK+username, "LOCK");
            stringRedisTemplate.expire(SHIRO_IS_LOCK+username, 1, TimeUnit.HOURS);
        }
        if("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK+username))){
            throw new DisabledAccountException("由于密码输入错误次数大于3次，帐号已经禁止登录！");
        }

        //通过username从数据库中查找User对象，如果找到，没找到。
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己有个时间间隔，2分钟内不会重新实行此方法
        UserInfo userInfo = userInfoService.findByUsername(username);
        System.out.println("----->>userInfo="+userInfo);
        if (null == userInfo){
            return null;
        }else if("0".equals(userInfo.getStatus())){
            throw new DisabledAccountException("此账号已经被限制登录");
        }else{
            //登录成功
            //更新登录时间
            //清空登录计数
            opsForValue.set(SHIRO_LOGIN_COUNT+username, "0");
        }

        /*
        * 获取权限信息:这里没有进行实现，
        * 请自行根据UserInfo,Role,Permission进行实现；
        * 获取之后可以在前端for循环显示所有链接;
        */
        //userInfo.setPermissions(userService.findPermissions(user));

        //账号判断;

        //加密方式;
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo,//用户名
                userInfo.getPassword(),//密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()
        );

        //明文: 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
//      SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//           userInfo, //用户名
//           userInfo.getPassword(), //密码
//             getName()  //realm name
//      );
        logger.info("身份认证成功，登录用户：" + username);
        return authenticationInfo;
    }

    /**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     *
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*
        * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
        * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
        * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
        * 缓存过期之后会再次执行。
        */
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");

        UserInfo userInfo = (UserInfo)SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        for(SysRole role : userInfo.getRoles()){
            roleSet.add(role.getRole());
            for(SysPermission permission : role.getPermissions()){
                permissionSet.add(permission.getPermission());
            }
        }
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }
}

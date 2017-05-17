package com.xingkong.lyn.shiro;

import com.xingkong.lyn.model.SysPermission;
import com.xingkong.lyn.model.SysRole;
import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.service.impl.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lyn on 2017/5/17.
 */
public class MyShiroRealm extends AuthorizingRealm{

    @Resource
    private UserInfoService userInfoService;

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

        //通过username从数据库中查找User对象，如果找到，没找到。
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己有个时间间隔，2分钟内不会重新实行此方法
        UserInfo userInfo = userInfoService.findByUsername(username);
        System.out.println("----->>userInfo="+userInfo);
        if (null == userInfo){
            return null;
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
        Long userId = userInfo.getId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        for(SysRole role : userInfo.getRoleList()){
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

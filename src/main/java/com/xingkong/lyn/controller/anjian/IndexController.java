package com.xingkong.lyn.controller.anjian;


import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.User;
import com.xingkong.lyn.model.manage.LoginVo;
import com.xingkong.lyn.service.anjian.IUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by lyn on 2017/4/24.
 * @CrossGrigin springboot用于跨域的注解
 */
@RestController
//@CrossOrigin
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Resource
    private IUser userService;
    //todo
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody LoginVo loginVo){
        AjaxResults ajaxResults = new AjaxResults();
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        String vcode = loginVo.getVcode();
        Boolean rememberMe = loginVo.getRememberMe();
//        if(StringUtils.isBlank(vcode)){
//            ajaxResults.setCode(1);
//            ajaxResults.setMsg("验证码不能为空");
//            return ajaxResults;
//        }
//        Session session = SecurityUtils.getSubject().getSession();
        //转换成小写字母
//        vcode = vcode.toLowerCase();
//        String v = (String) session.getAttribute("_code");
//        if(!vcode.equals(v)){
//            ajaxResults.setCode(1);
//            ajaxResults.setMsg("验证码错误！");
//            return ajaxResults;
//        }

        try{
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
            SecurityUtils.getSubject().login(token);
            User user = userService.findByName(username);
            ajaxResults.put("user", user);
        } catch (Exception e){
            ajaxResults.setCode(0);
            ajaxResults.setMsg("验证失败："+e.getMessage());
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Object logout(){
        AjaxResults ajaxResults = new AjaxResults();
        try{
            //退出
            SecurityUtils.getSubject().logout();
        } catch (Exception e){
            logger.info(e.getMessage());
        }
        return ajaxResults;
    }
}

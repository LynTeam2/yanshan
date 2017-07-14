package com.xingkong.lyn.controller;

import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.model.manage.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lyn on 2017/6/21.
 */
@RestController
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

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
        } catch (Exception e){
            ajaxResults.setCode(1);
            ajaxResults.setMsg("验证失败："+e.getMessage());
        }
        return ajaxResults;
    }

    @RequestMapping(value = "／logout", method = RequestMethod.GET)
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

package com.xingkong.lyn.controller;

import com.xingkong.lyn.comment.AjaxResults;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lyn on 2017/6/21.
 */
@RestController
public class LoginController {
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(String username, String password, String vcode, Boolean rememberMe, Model model){
        AjaxResults ajaxResults = new AjaxResults();
        if(StringUtils.isBlank(vcode)){
            ajaxResults.setCode(1);
            ajaxResults.setMsg("验证码不能为空");
            return ajaxResults;
        }
        Session session = SecurityUtils.getSubject().getSession();
        //转换成小写字母
        vcode = vcode.toLowerCase();
        String v = (String) session.getAttribute("_code");
        if(!vcode.equals(v)){
            ajaxResults.setCode(1);
            ajaxResults.setMsg("验证码错误！");
            return ajaxResults;
        }

        try{
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
            SecurityUtils.getSubject().login(token);
        } catch (Exception e){
            ajaxResults.setCode(1);
            ajaxResults.setMsg("验证失败");
        }
        return ajaxResults;
    }
}

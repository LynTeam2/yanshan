package com.xingkong.lyn.controller;


import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.SysPermission;
import com.xingkong.lyn.model.SysRole;
import com.xingkong.lyn.model.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by lyn on 2017/4/24.
 */
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/index")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("host", "HELLO DOCKER WORLD");
        return "test";
    }

    @RequestMapping(value = "/index/manage/leftmenu", method = RequestMethod.GET)
    @ResponseBody
    public Object leftMenu(){
        AjaxResults ajaxResults = new AjaxResults();

        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        //Set<String> roleSet =new HashSet<>();
        Set<SysPermission> permissionSet = new HashSet<>();
        for(SysRole role : userInfo.getRoles()){
             //roleSet.add(role.getRole());
            role.setUserInfos(null);
            for(SysPermission permission : role.getPermissions()){
                if(null == permission.getParentId()){
                    permissionSet.add(permission);
                }
            }
        }
        //info.setRoles(roleSet);
        ajaxResults.put("leftMenu", permissionSet);
        return ajaxResults;
    }
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(){
//        return "login";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String submitLogin(HttpServletRequest request, Map<String, Object> map){
//        System.out.println("HomeController.login()");
//        // 登录失败从request中获取shiro处理的异常信息。
//        // shiroLoginFailure:就是shiro异常类的全类名.
//        String exception = (String) request.getAttribute("shiroLoginFailure");
//
//        System.out.println("exception=" + exception);
//        String msg = "";
//        if (exception != null) {
//            if (UnknownAccountException.class.getName().equals(exception)) {
//                System.out.println("UnknownAccountException -- > 账号不存在：");
//                msg = "UnknownAccountException -- > 账号不存在：";
//            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
//                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
//                msg = "IncorrectCredentialsException -- > 密码不正确：";
//            } else if ("kaptchaValidateFailed".equals(exception)) {
//                System.out.println("kaptchaValidateFailed -- > 验证码错误");
//                msg = "kaptchaValidateFailed -- > 验证码错误";
//            } else {
//                msg = "else >> "+exception;
//                System.out.println("else -- >" + exception);
//            }
//        }
//        map.put("msg", msg);
//        // 此方法不处理登录成功,由shiro进行处理.
//        return "login";
//    }
}

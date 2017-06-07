package com.xingkong.lyn.controller;

import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.service.IUserInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lyn on 2017/5/26.
 */
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Resource
    private IUserInfo userInfoService;

    @RequestMapping("/user/add")
    @RequiresPermissions("userinfo:add")
    public Object add(UserInfo userInfo){
        return userInfoService.addUser(userInfo);
    }

    @RequestMapping("/user/findall")
    @RequiresPermissions("userinfo:view")
    public Object findAll(){
        return userInfoService.findAll();
    }

    @RequestMapping("/user/delete")
    @RequiresPermissions("userinfo:delete")
    public Object delete(Long id){
        return userInfoService.deleteUser(id);
    }

    @RequestMapping("/user/update")
    @RequiresPermissions("userinfo:update")
    public Object update(UserInfo userInfo){
        return userInfoService.updateUser(userInfo);
    }

    @RequestMapping("/user/detail")
    @RequiresPermissions("userinfo:detail")
    public Object detail(Long id){
        return userInfoService.findById(id);
    }
}

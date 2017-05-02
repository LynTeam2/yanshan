package com.xingkong.lyn.controller;


import com.xingkong.lyn.service.IUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by lyn on 2017/4/24.
 */
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private IUser userService;

    @RequestMapping("/index")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("host", "HELLO DOCKER WORLD");
        return "index";
    }

    @RequestMapping("/user/add")
    @ResponseBody
    public Object add(String name){
        return userService.addUser(name);
    }
}

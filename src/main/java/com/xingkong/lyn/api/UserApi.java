package com.xingkong.lyn.api;

import com.xingkong.lyn.service.anjian.IUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lyn on 2018/1/28.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserApi {
    @Resource
    private IUser userService;

    
}

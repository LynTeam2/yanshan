package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.mapper.UserMapper;
import com.xingkong.lyn.model.User;
import com.xingkong.lyn.service.IUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lyn on 2017/4/28.
 */
@Service("userService")
public class UserService implements IUser{

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean addUser(String name) {
        User user = new User();
        user.setName(name);
        return userMapper.insert(user) > 0;
    }
}

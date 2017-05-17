package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.dao.UserInfoDao;
import com.xingkong.lyn.mapper.UserMapper;
import com.xingkong.lyn.model.User;
import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.service.IUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/4/28.
 */
@Service("userService")
@CacheConfig(cacheNames = "user")
public class UserService implements IUser{

    @Resource
    private UserMapper userMapper;;

    @Override
    public boolean addUser(String name) {
        User user = new User();
        user.setName(name);
        return userMapper.insert(user) > 0;
    }

    @Override
    @Cacheable
    public List<User> findAll() {
        return userMapper.selectList();
    }
}

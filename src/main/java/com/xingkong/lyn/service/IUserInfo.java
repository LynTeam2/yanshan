package com.xingkong.lyn.service;

import com.xingkong.lyn.model.UserInfo;

import java.util.List;

/**
 * Created by lyn on 2017/5/17.
 */
public interface IUserInfo {
    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);
    List<UserInfo> findAll();
    boolean addUser(UserInfo userInfo);
}

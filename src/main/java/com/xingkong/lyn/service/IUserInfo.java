package com.xingkong.lyn.service;

import com.xingkong.lyn.model.UserInfo;

/**
 * Created by lyn on 2017/5/17.
 */
public interface IUserInfo {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}

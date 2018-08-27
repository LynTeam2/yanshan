package com.xingkong.lyn.service;

import com.xingkong.lyn.model.UserInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by lyn on 2017/5/17.
 */
public interface IUserInfo {
    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);
    List<UserInfo> findAll();
    boolean addUser(UserInfo userInfo);
    boolean deleteUser(Long id);
    boolean updateUser(UserInfo userInfo);
    UserInfo findById(Long id);
}

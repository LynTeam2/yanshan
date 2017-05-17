package com.xingkong.lyn.service;


import com.xingkong.lyn.model.User;
import com.xingkong.lyn.model.UserInfo;

import java.util.List;

/**
 * Created by lyn on 2017/4/28.
 */

public interface IUser {
    boolean addUser(String name);

    List<User> findAll();
}

package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.repository.UserInfoRepository;
import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.service.IUserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/5/17.
 */
@Service
public class UserInfoService implements IUserInfo {
    @Resource
    private UserInfoRepository userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }

    @Override
    public List<UserInfo> findAll() {
        System.out.println("UserInfoServiceImpl.findAll()");
        return userInfoDao.findAll();
    }

    @Override
    public boolean addUser(UserInfo userInfo) {
        try{
            userInfoDao.save(userInfo);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}

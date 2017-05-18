package com.xingkong.lyn.dao;

import com.xingkong.lyn.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lyn on 2017/5/17.
 */
public interface UserInfoDao extends JpaRepository<UserInfo, Long> {
    public UserInfo findByUsername(String username);
    List<UserInfo> findAll();
}
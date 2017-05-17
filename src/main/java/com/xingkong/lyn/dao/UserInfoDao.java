package com.xingkong.lyn.dao;

import com.xingkong.lyn.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lyn on 2017/5/17.
 */
public interface UserInfoDao extends CrudRepository<UserInfo, Long>{
    public UserInfo findByUsername(String username);
}
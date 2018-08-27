package com.xingkong.lyn.repository;

import com.xingkong.lyn.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lyn on 2017/5/17.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
    Long countByUsername(String username);
}
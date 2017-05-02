package com.xingkong.lyn.mapper;


import com.xingkong.lyn.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lyn on 2017/4/28.
 */
public interface UserMapper {
    int insert(@Param("user")User user);
}

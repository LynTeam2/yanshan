package com.xingkong.lyn.service.anjian;

import com.xingkong.lyn.entity.anjian.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUser {
    Page<User> findList(Pageable pageable);
    User findById(long id);
    boolean addUser(User user);
    boolean deleteList(List<Long> ids);
    boolean updateUser(User user);
    User findByName(String username);
}

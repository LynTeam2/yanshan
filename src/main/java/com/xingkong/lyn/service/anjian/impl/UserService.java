package com.xingkong.lyn.service.anjian.impl;

import com.xingkong.lyn.entity.anjian.User;
import com.xingkong.lyn.repository.anjian.UserRepository;
import com.xingkong.lyn.service.anjian.IUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements IUser {
    @Resource
    private UserRepository userDao;

    @Override
    public Page<User> findList(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    public User findById(long id) {
        return userDao.findOne(id);
    }

    @Override
    public boolean addUser(User user) {
        userDao.saveAndFlush(user);
        return true;
    }

    @Override
    public boolean deleteList(List<Long> ids) {
        userDao.deleteInBatch(userDao.findAll(ids));
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        userDao.saveAndFlush(user);
        return true;
    }

    @Override
    public User findByName(String username) {
        return userDao.findByUserName(username);
    }
}

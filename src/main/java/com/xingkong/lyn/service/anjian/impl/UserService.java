package com.xingkong.lyn.service.anjian.impl;

import com.xingkong.lyn.common.constant.PasswordConstant;
import com.xingkong.lyn.entity.anjian.User;
import com.xingkong.lyn.model.SysRole;
import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.repository.SysRoleRepository;
import com.xingkong.lyn.repository.UserInfoRepository;
import com.xingkong.lyn.repository.anjian.UnitRepository;
import com.xingkong.lyn.repository.anjian.UserRepository;
import com.xingkong.lyn.service.anjian.IUser;
import com.xingkong.lyn.util.EncodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUser {
    @Resource
    private UserRepository userDao;

    @Resource
    private UserInfoRepository userInfoDao;

    @Resource
    private SysRoleRepository sysRoleDao;

    @Resource
    private UnitRepository unitDao;

    @Override
    public Page<User> findList(Pageable pageable, String query) {
        return StringUtils.isBlank(query)?userDao.findAll(pageable):userDao.findByUserNameLike(query, pageable);
    }

    @Override
    public User findById(long id) {
        return userDao.findOne(id);
    }

    @Override
    @Transactional
    public boolean addUser(User user) {
        user.setUnit(unitDao.findOne(Long.parseLong(user.getUnitId())));
        userDao.saveAndFlush(user);
        UserInfo userInfo = convertToUserInfo(user);
        userInfoDao.saveAndFlush(userInfo);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteList(List<Long> ids) {
        List<User> users = userDao.findAll(ids);
        userDao.deleteInBatch(users);
        users.forEach(user -> {
            userInfoDao.delete(userInfoDao.findByUsername(user.getUserName()));
        });
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        user.setUnit(unitDao.findOne(Long.parseLong(user.getUnitId())));
        userDao.saveAndFlush(user);
        UserInfo userInfo = convertToUserInfo(user);
        userInfoDao.saveAndFlush(userInfo);
        return true;
    }

    @Override
    public User findByName(String username) {
        return userDao.findByUserName(username);
    }

    private UserInfo convertToUserInfo(User user) {
//        Subject currentUser = SecurityUtils.getSubject();
//        UserInfo loginUser = (UserInfo)currentUser.getPrincipal();
        UserInfo userInfo = new UserInfo();
        if (userInfoDao.countByUsername(user.getUserName()) > 0) {
            userInfo = userInfoDao.findByUsername(user.getUserName());
        } else {
            userInfo.setUsername(user.getUserName());
            userInfo.setStatus((byte)0);
            userInfo.setPassword(PasswordConstant.DELFAULT_PASSWORD);
            userInfo = EncodeUtil.encode(userInfo);
        }
        List<SysRole> roles = new ArrayList<>();
        roles.add(sysRoleDao.findOne(Long.parseLong(user.getRole())));
        userInfo.setRoles(roles);
        return userInfo;
    }
}
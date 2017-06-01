package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.SysRole;
import com.xingkong.lyn.repository.RoleRepository;
import com.xingkong.lyn.service.IRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/6/1.
 */
@Service
public class RoleService implements IRole{
    @Resource
    private RoleRepository roleDao;

    @Override
    public List<SysRole> findAll() {
        return roleDao.findAll();
    }

    @Override
    public boolean addRole(SysRole sysRole) {
        roleDao.saveAndFlush(sysRole);
        return true;
    }

    @Override
    public boolean deleteRole(Long id) {
        roleDao.delete(id);
        return true;
    }

    @Override
    public boolean updateRole(SysRole sysRole) {
        roleDao.saveAndFlush(sysRole);
        return true;
    }

    @Override
    public SysRole findById(Long id) {
        return roleDao.findOne(id);
    }
}

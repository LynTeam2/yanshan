package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.SysRole;
import com.xingkong.lyn.repository.SysRoleRepository;
import com.xingkong.lyn.service.ISysRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2018/8/13.
 */
@Service
public class SysRoleService implements ISysRole {
    @Resource
    private SysRoleRepository sysRoleDao;

    @Override
    public List<SysRole> findList(String query) {
        return StringUtils.isBlank(query) ? sysRoleDao.findAll() : sysRoleDao.findAllByRole(query);
    }
}

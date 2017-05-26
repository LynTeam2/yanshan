package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.SysPermissionInit;
import com.xingkong.lyn.repository.SysPermissionInitRepository;
import com.xingkong.lyn.service.ISysPermissionInit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/5/18.
 */
@Service
public class SysPermissionInitService implements ISysPermissionInit{

    @Resource
    private SysPermissionInitRepository sysPermissionInitDao;

    @Override
    public List<SysPermissionInit> selectAll() {
        return sysPermissionInitDao.findAll();
    }
}

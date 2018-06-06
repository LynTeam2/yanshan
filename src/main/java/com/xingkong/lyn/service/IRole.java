package com.xingkong.lyn.service;

import com.xingkong.lyn.model.SysRole;

import java.util.List;

/**
 * Created by lyn on 2017/6/1.
 */
public interface IRole {
    List<SysRole> findAll();
    boolean addRole(SysRole sysRole);
    boolean deleteRole(Long id);
    boolean updateRole(SysRole sysRole);
    SysRole findById(Long id);
}

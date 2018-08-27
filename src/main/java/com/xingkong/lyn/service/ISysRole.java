package com.xingkong.lyn.service;

import com.xingkong.lyn.model.SysRole;

import java.util.List;

/**
 * Created by lyn on 2018/8/13.
 */
public interface ISysRole {
    public List<SysRole> findList(String query);
}

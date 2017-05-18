package com.xingkong.lyn.mapper;

import com.xingkong.lyn.model.SysPermissionInit;
import java.util.List;

public interface SysPermissionInitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysPermissionInit record);

    SysPermissionInit selectByPrimaryKey(Long id);

    List<SysPermissionInit> selectAll();

    int updateByPrimaryKey(SysPermissionInit record);
}
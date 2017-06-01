package com.xingkong.lyn.controller;

import com.xingkong.lyn.model.SysRole;
import com.xingkong.lyn.service.IRole;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lyn on 2017/6/1.
 */
@RestController
public class RoleController {
    @Resource
    private IRole roleService;

    @RequestMapping(value = "/role/add")
    @RequiresPermissions("role:add")
    public Object add(SysRole sysRole) {
        return roleService.addRole(sysRole);
    }

    @RequestMapping(value = "/role/update")
    @RequiresPermissions("role:update")
    public Object update(SysRole sysRole) {
        return roleService.updateRole(sysRole);
    }

    @RequestMapping(value = "/role/delete")
    @RequiresPermissions("role:delete")
    public Object delete(Long id) {
        return roleService.deleteRole(id);
    }

    @RequestMapping(value = "/role/view")
    @RequiresPermissions("role:view")
    public Object findAll() {
        return roleService.findAll();
    }

    @RequestMapping(value = "/role/detail")
    @RequiresPermissions("role:detail")
    public Object detail(Long id) {
        return roleService.findById(id);
    }
}

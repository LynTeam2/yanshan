package com.xingkong.lyn.controller.anjian;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.service.ISysRole;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lyn on 2018/8/13.
 */
@RestController
public class RoleController {
    @Resource
    private ISysRole sysRoleService;

    @RequestMapping(name = "web/manage/role/list")
    public Object list(String query) {
        AjaxResults ajaxResults = new AjaxResults();
        ajaxResults.put("roles", sysRoleService.findList(query));
        return ajaxResults;
    }

}

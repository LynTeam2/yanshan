package com.xingkong.lyn.api;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.User;
import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.service.anjian.IUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by lyn on 2018/1/28.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserApi {
    @Resource
    private IUser userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @RequiresPermissions("user:detail")
    public Object get(@PathVariable Long id) {
        AjaxResults ajaxResults = new AjaxResults();
        User user = userService.findById(id);
        ajaxResults.put("User", user);
        return ajaxResults;
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.PUT)
    public Object update(@RequestBody User user, @PathVariable String type) {
        AjaxResults ajaxResults = new AjaxResults();
        Subject currentUser = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo)currentUser.getPrincipal();
        User oldUser = userService.findByName(userInfo.getUsername());
        switch (type) {
            case "nickname": oldUser.setNickname(user.getNickname()); break;
            case "icon": oldUser.setIcon(user.getIcon()); break;
            case "bean": oldUser.setBeanCount(user.getBeanCount()); break;
            default: break;
        }
        userService.updateUser(oldUser);
        return ajaxResults;
    }
}

package com.xingkong.lyn.api;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.User;
import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.service.IUserInfo;
import com.xingkong.lyn.service.anjian.IUser;
import com.xingkong.lyn.util.EncodeUtil;
import org.apache.commons.lang3.StringUtils;
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
    @Resource
    private IUserInfo userInfoService;

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

    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public Object password(@RequestBody String password) {
        AjaxResults ajaxResults = new AjaxResults();
        Subject currentUser = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo)currentUser.getPrincipal();
        userInfo = userInfoService.findById(userInfo.getId());
        if (StringUtils.isNotEmpty(password) && password.length() > 5) {
            userInfo.setPassword(password);
            EncodeUtil.encode(userInfo);
            userInfoService.updateUser(userInfo);
        } else {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("密码不符合要求，不允许修改");
        }
        return ajaxResults;
    }

//    @RequestMapping(value = "/password/{id}", method = RequestMethod.GET)
//    public Object password(@PathVariable Long id) {
//        AjaxResults ajaxResults = new AjaxResults();
//        if (null == id) {
//            ajaxResults.setCode(0);
//            ajaxResults.setMsg("参数错误，不允许重置密码");
//        }
//
//    }
}

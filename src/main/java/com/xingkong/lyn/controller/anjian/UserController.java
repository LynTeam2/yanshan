package com.xingkong.lyn.controller.anjian;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.common.constant.PasswordConstant;
import com.xingkong.lyn.entity.anjian.User;
import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.service.IUserInfo;
import com.xingkong.lyn.service.anjian.IUser;
import com.xingkong.lyn.util.EncodeUtil;
import com.xingkong.lyn.util.OtherUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lyn on 2017/5/26.
 */
@RestController
public class UserController {
    @Resource
    private IUserInfo userInfoService;

    @Resource
    private IUser userService;

    @RequestMapping("/web/manage/user/add")
//    @RequiresPermissions("userinfo:add")
    public Object add(@RequestBody User user){
        AjaxResults ajaxResults = new AjaxResults();
        if (null != user.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        }
        if (null == userService.findByName(user.getUserName())) {
            userService.addUser(user);
        } else {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("用户已存在");
        }
        return ajaxResults;
    }

    @RequestMapping("/web/manage/user/list")
//    @RequiresPermissions("userinfo:view")
    public Object list(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                   Pageable pageable, String query){
        AjaxResults ajaxResults = new AjaxResults();
        Page<User> users = userService.findList(pageable, query);
        users.forEach(user -> {
            user.setUnitId(null == user.getUnit() ? null : Long.toString(user.getUnit().getId()));
            user.setUnit(null);
            user.setRole(Long.toString(userInfoService.findByUsername(user.getUserName()).getRoles().get(0).getId()));
        });
        ajaxResults.put("users", JSON.parse(JSON.toJSONString(users, SerializerFeature.DisableCircularReferenceDetect)));
        return ajaxResults;
    }

    @RequestMapping("/web/manage/user/delete")
//    @RequiresPermissions("userinfo:delete")
    public Object delete(String id){
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        userService.deleteList(ids);
        return ajaxResults;
    }

    @RequestMapping("/web/manage/user/update")
//    @RequiresPermissions("userinfo:update")
    public Object update(@RequestBody User user){
        AjaxResults ajaxResults = new AjaxResults();
        if (null == user.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        }
        userService.updateUser(user);
        return ajaxResults;
    }

    @RequestMapping("/web/manage/user/detail")
//    @RequiresPermissions("userinfo:detail")
    public Object detail(Long id){
        return userInfoService.findById(id);
    }
}

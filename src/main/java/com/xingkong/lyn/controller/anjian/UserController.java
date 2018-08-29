package com.xingkong.lyn.controller.anjian;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.common.constant.PasswordConstant;
import com.xingkong.lyn.common.constant.ReviewConstant;
import com.xingkong.lyn.entity.anjian.Review;
import com.xingkong.lyn.entity.anjian.User;
import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.service.IUserInfo;
import com.xingkong.lyn.service.anjian.IReview;
import com.xingkong.lyn.service.anjian.IUser;
import com.xingkong.lyn.util.EncodeUtil;
import com.xingkong.lyn.util.OtherUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
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

    @Resource
    private IReview reviewService;

    @RequestMapping("/web/manage/user/add")
//    @RequiresPermissions("userinfo:add")
    public Object add(@RequestBody User user){
        AjaxResults ajaxResults = new AjaxResults();
        if (null != user.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        }
        if (null == userService.findByName(user.getUserName())) {
            Subject currentUser = SecurityUtils.getSubject();
            UserInfo userInfo = (UserInfo)currentUser.getPrincipal();
            System.out.println(userInfo);
            userInfo = userInfoService.findByUsername(userInfo.getUsername());
            if (commitReview(user, ReviewConstant.OPERATE_ADD, userInfo)) {
                ajaxResults.setCode(0);
                ajaxResults.setMsg("操作已提交，等待审核");
            } else {
                userService.addUser(user);
            }
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
        Subject currentUser = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo)currentUser.getPrincipal();
        userInfo = userInfoService.findByUsername(userInfo.getUsername());
        if (commitReviews(ids, ReviewConstant.OPERATE_DELETE, userInfo)) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("操作已提交，等待审核");
        } else {
            userService.deleteList(ids);
        }
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
        Subject currentUser = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo)currentUser.getPrincipal();
        userInfo = userInfoService.findByUsername(userInfo.getUsername());
        if (commitReview(user, ReviewConstant.OPERATE_MODIFY, userInfo)) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("操作已提交，等待审核");
        } else {
            userService.updateUser(user);
        }
        return ajaxResults;
    }

    @RequestMapping("/web/manage/user/detail")
//    @RequiresPermissions("userinfo:detail")
    public Object detail(Long id){
        return userInfoService.findById(id);
    }

    private boolean commitReview(User user, String operate, UserInfo userInfo) {
        if (StringUtils.equals(userInfo.getRoles().get(0).getRole(), "admin")) {
            User loginUser = userService.findByName(userInfo.getUsername());
            Review review = new Review();
            review.setModule(ReviewConstant.MODULE_USER);
            review.setOperate(operate);
            review.setOperateTime(new Date());
            review.setOperatorId(loginUser.getId());
            review.setOperatorName(loginUser.getRealName());
            review.setReviewContent(JSON.toJSONString(user));
            reviewService.addReview(review);
            return true;
        }
        return false;
    }

    private boolean commitReviews(List<Long> ids, String operate, UserInfo userInfo) {
        if (StringUtils.equals(userInfo.getRoles().get(0).getRole(), "admin")) {
            User loginUser = userService.findByName(userInfo.getUsername());
            List<User> users = userService.findByIds(ids);
            users.forEach(user -> {
                Review review = new Review();
                review.setModule(ReviewConstant.MODULE_USER);
                review.setOperate(operate);
                review.setOperateTime(new Date());
                review.setOperatorId(loginUser.getId());
                review.setOperatorName(loginUser.getRealName());
                review.setReviewContent(JSON.toJSONString(user));
                reviewService.addReview(review);
            });
            return true;
        }
        return false;
    }
}

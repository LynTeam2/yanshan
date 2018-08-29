package com.xingkong.lyn.controller.anjian;

import com.alibaba.fastjson.JSON;
import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.common.constant.ReviewConstant;
import com.xingkong.lyn.entity.anjian.Review;
import com.xingkong.lyn.entity.anjian.User;
import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.service.anjian.IReview;
import com.xingkong.lyn.service.anjian.IUnit;
import com.xingkong.lyn.service.anjian.IUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lyn on 2018/8/27.
 */
@RestController
public class ReviewController {
    @Resource
    private IReview reviewService;
    @Resource
    private IUser userService;
    @Resource
    private IUnit unitService;

    @RequestMapping(value = "/web/manage/review/list")
    public Object webManageReviewList(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.ASC)
                                                  Pageable pageable, String reviewResult) {
        AjaxResults ajaxResults = new AjaxResults();
        Page<Review> reviews = reviewService.findByResult(Integer.parseInt(reviewResult), pageable);
        ajaxResults.put("reviews", reviews);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/review/update")
    public Object webManageReviewUpdate(@RequestBody Review review) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null == review.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数异常");
        } else {
            Subject currentUser = SecurityUtils.getSubject();
            UserInfo userInfo = (UserInfo)currentUser.getPrincipal();
            User user = userService.findByName(userInfo.getUsername());
            if (review.getOperatorId() == user.getId()) {
                ajaxResults.setCode(0);
                ajaxResults.setMsg("只允许审核其他超级管理员的操作");
            } else if (!StringUtils.equals(userInfo.getRoles().get(0).getRole(), "admin")) {
                ajaxResults.setCode(0);
                ajaxResults.setMsg("用户权限不足");
            } else {
                review.setReviewerId(user.getId());
                review.setReviewerName(user.getRealName());
                review.setReviewTime(new Date());
                if (review.getReviewResult() == 1) {
                    switch (review.getModule()) {
                        case ReviewConstant.MODULE_USER:
                            handleUser(review);
                            break;
                        case ReviewConstant.MODULE_UNIT:
                            handleUnit(review);
                            break;
                        default:
                            break;
                    }
                }
                reviewService.updateReview(review);
            }
        }
        return ajaxResults;
    }

    private void handleUser(Review review) {
        User user = JSON.parseObject(review.getReviewContent(), User.class);
        switch (review.getOperate()) {
            case ReviewConstant.OPERATE_ADD:
                userService.addUser(user);
                break;
            case ReviewConstant.OPERATE_MODIFY:
                userService.updateUser(user);
                break;
            case ReviewConstant.OPERATE_DELETE:
                userService.deleteUser(user.getId());
                break;
            default:
                break;
        }
    }

    private void handleUnit(Review review) {

    }
}

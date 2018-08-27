package com.xingkong.lyn.api;

import com.xingkong.lyn.annotation.AdminLog;
import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.ExamHistory;
import com.xingkong.lyn.entity.anjian.User;
import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.service.anjian.IExamHistory;
import com.xingkong.lyn.service.anjian.IUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2018/8/26.
 */
@RestController
@RequestMapping(value = "/api/exam")
public class ExamApi {
    @Resource
    private IExamHistory examHistoryService;
    @Resource
    private IUser userService;

    @RequestMapping(method = RequestMethod.GET)
//    @RequiresPermissions("news:view")
    @AdminLog(value = "考试历史:查看列表")
    public Object get() {
        AjaxResults ajaxResults = new AjaxResults();
        Subject currentUser = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo)currentUser.getPrincipal();
        User user = userService.findByName(userInfo.getUsername());
        List<ExamHistory> examHistoryList = examHistoryService.findListByUser(user.getId());
        ajaxResults.put("examHistories", examHistoryList);
        return ajaxResults;
    }

    @RequestMapping(method = RequestMethod.POST)
//    @RequiresPermissions("news:view")
    @AdminLog(value = "考试历史:提交历史")
    public Object post(@RequestBody ExamHistory examHistory) {
        AjaxResults ajaxResults = new AjaxResults();
        Subject currentUser = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo)currentUser.getPrincipal();
        User user = userService.findByName(userInfo.getUsername());
        examHistory.setUserId(user.getId());
        examHistory.setUnitId(user.getUnit().getId());
        return ajaxResults;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @RequiresPermissions("news:view")
    @AdminLog(value = "考试历史:考试次数")
    public Object get(@PathVariable Long id) {
        AjaxResults ajaxResults = new AjaxResults();
        Subject currentUser = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo)currentUser.getPrincipal();
        User user = userService.findByName(userInfo.getUsername());
        ajaxResults.put("examCount", examHistoryService.getExamCount(user.getId(), id));
        return ajaxResults;
    }
}

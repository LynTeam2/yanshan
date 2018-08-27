package com.xingkong.lyn.api;

import com.xingkong.lyn.annotation.AdminLog;
import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.CourseProcess;
import com.xingkong.lyn.entity.anjian.User;
import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.service.anjian.ICourse;
import com.xingkong.lyn.service.anjian.IUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2018/8/26.
 */
@RestController
@RequestMapping(value = "/api/course")
public class CourseApi {

    @Resource
    private IUser userService;

    @Resource
    private ICourse courseService;

    @RequestMapping(value = "{id}",method = RequestMethod.POST)
//    @RequiresPermissions("news:view")
    @AdminLog(value = "课程进度:提交进度")
    public Object post(@PathVariable Long id, int duration) {
        AjaxResults ajaxResults = new AjaxResults();
        Subject currentUser = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo)currentUser.getPrincipal();
        User user = userService.findByName(userInfo.getUsername());
        List<CourseProcess> courseProcessList = user.getCourseProcessList();
        if (null == courseService.findById(id)) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("课程不存在或已被删除");
        }
        CourseProcess courseProcess = new CourseProcess();
        courseProcess.setCourseId(id);
        courseProcess.setProcess(1);
        courseProcess.setDuration(duration);
        courseProcessList.add(courseProcess);
        user.setCourseProcessList(courseProcessList);
        userService.updateUser(user);
        return ajaxResults;
    }
}

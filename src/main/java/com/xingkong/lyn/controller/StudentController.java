package com.xingkong.lyn.controller;

import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.web.Student;
import com.xingkong.lyn.service.IStudent;
import com.xingkong.lyn.util.OtherUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
@RestController
public class StudentController {
    @Resource
    private IStudent studentService;

    @RequestMapping(value = "/web/manage/student/list", method = RequestMethod.GET)
//    @RequiresPermissions("student:view")
    public Object webManageStudentList(@PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable) {
        AjaxResults ajaxResults = new AjaxResults();
        ajaxResults.put("students", studentService.getStudentList(pageable));
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/student/add", method = RequestMethod.POST)
//    @RequiresPermissions("student:add")
    public Object webManageStudentAdd(@RequestBody Student student) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null != student.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            studentService.addStudent(student);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/student/update", method = RequestMethod.PUT)
//    @RequiresPermissions("student:update")
    public Object webManageStudentUpdate(@RequestBody Student student) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null == student.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            studentService.updateStudent(student);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/student/delete", method = RequestMethod.DELETE)
//    @RequiresPermissions("student:delete")
    public Object webManageStudentDelete(String id) {
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        studentService.deleteStudent(ids);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/student/detail", method = RequestMethod.GET)
//    @RequiresPermissions("student:detail")
    public Object webManageStudentDetail(Long id) {
        AjaxResults ajaxResults = new AjaxResults();
        ajaxResults.put("student", studentService.getStudent(id));
        return ajaxResults;
    }
}

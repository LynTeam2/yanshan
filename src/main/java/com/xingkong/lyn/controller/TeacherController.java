package com.xingkong.lyn.controller;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.model.web.Course;
import com.xingkong.lyn.model.web.Teacher;
import com.xingkong.lyn.service.ITeacher;
import com.xingkong.lyn.util.OtherUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
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
public class TeacherController {
    @Resource
    private ITeacher teacherService;

    @RequestMapping(value = "/web/manage/teacher/list", method = RequestMethod.GET)
//    @RequiresPermissions("teacher:view")
    public Object webManageTeacherList(@PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable) {
        AjaxResults ajaxResults = new AjaxResults();
        Page<Teacher> teachers = teacherService.getTeacherList(pageable);
        for(Teacher teacher : teachers) {
            for(Course course : teacher.getCourses()){
                course.setTeachers(null);
                course.setReservations(null);
            }
        }
        ajaxResults.put("teachers", teachers);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/teacher/add", method = RequestMethod.POST)
//    @RequiresPermissions("teacher:add")
    public Object webManageTeacherAdd(@RequestBody Teacher teacher) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null != teacher.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            teacherService.addTeacher(teacher);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/teacher/update", method = RequestMethod.PUT)
//    @RequiresPermissions("teacher:update")
    public Object webManageTeacherUpdate(@RequestBody Teacher teacher) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null == teacher.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            teacherService.updateTeacher(teacher);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/teacher/delete", method = RequestMethod.DELETE)
//    @RequiresPermissions("teacher:delete")
    public Object webManageTeacherDelete(String id) {
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        teacherService.deleteTeacher(ids);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/teacher/detail", method = RequestMethod.GET)
//    @RequiresPermissions("teacher:detail")
    public Object webManageTeacherDetail(Long id) {
        AjaxResults ajaxResults = new AjaxResults();
        ajaxResults.put("teacher", teacherService.getTeacher(id));
        return ajaxResults;
    }
}

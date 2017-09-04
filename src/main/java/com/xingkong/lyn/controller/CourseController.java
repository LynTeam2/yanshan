package com.xingkong.lyn.controller;

import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.web.Course;
import com.xingkong.lyn.model.web.Teacher;
import com.xingkong.lyn.service.ICourse;
import com.xingkong.lyn.service.ITeacher;
import com.xingkong.lyn.util.OtherUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
@RestController
public class CourseController {
    @Resource
    private ICourse courseService;
    @Resource
    private ITeacher teacherService;

    @RequestMapping(value = "/web/manage/course/list", method = RequestMethod.GET)
//    @RequiresPermissions("course:view")
    public Object webManageCourseList(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                              Pageable pageable) {
        AjaxResults ajaxResults = new AjaxResults();
        Page<Course> courses = courseService.getCourseList(pageable);
        for(Course course : courses) {
            course.setTeachers(null);
            course.setReservations(null);
        }
        ajaxResults.put("courses", courses);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/course/add", method = RequestMethod.POST)
//    @RequiresPermissions("course:add")
    public Object webManageCourseAdd(@RequestBody Course course) {
        AjaxResults ajaxResults = new AjaxResults();
        Long teacherId = course.getTeachers().get(0).getId();
        Teacher teacher = teacherService.getTeacher(teacherId);
        List<Teacher> list = new ArrayList<>();
        list.add(teacher);
        course.setTeachers(list);
        if (!courseService.addCourse(course)) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("新增失败：参数错误");
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/course/update", method = RequestMethod.PUT)
//    @RequiresPermissions("course:update")
    public Object webManageCourseUpdate(@RequestBody Course course) {
        AjaxResults ajaxResults = new AjaxResults();
        Long teacherId = course.getTeachers().get(0).getId();
        Teacher teacher = teacherService.getTeacher(teacherId);
        List<Teacher> list = new ArrayList<>();
        list.add(teacher);
        course.setTeachers(list);
        if (!courseService.updateCourse(course)) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("修改失败：参数错误");
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/course/delete", method = RequestMethod.DELETE)
//    @RequiresPermissions("course:delete")
    public Object webManageCourseDelete(String id) {
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        courseService.deleteCourse(ids);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/course/detail", method = RequestMethod.GET)
//    @RequiresPermissions("course:detail")
    public Object webManageCourseDetail(Long id) {
        AjaxResults ajaxResults = new AjaxResults();
        ajaxResults.put("course", courseService.getCourse(id));
        return ajaxResults;
    }
}

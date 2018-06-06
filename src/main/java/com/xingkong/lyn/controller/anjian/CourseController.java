package com.xingkong.lyn.controller.anjian;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.Course;
import com.xingkong.lyn.service.anjian.ICourse;
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
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
@RestController
public class CourseController {
    @Resource
    private ICourse courseService;

    @RequestMapping(value = "/web/manage/course/list", method = RequestMethod.GET)
//    @RequiresPermissions("course:view")
    public Object webManageCourseList(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                              Pageable pageable, String query) {
        AjaxResults ajaxResults = new AjaxResults();
        Page<Course> courses = courseService.findList(pageable, query);
        ajaxResults.put("courses", JSON.parse(JSON.toJSONString(courses, SerializerFeature.DisableCircularReferenceDetect)));
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/course/all", method = RequestMethod.GET)
//    @RequiresPermissions("course:view")
    public Object webManageCourseList() {
        AjaxResults ajaxResults = new AjaxResults();
        List<Course> courses = courseService.findAll();
        ajaxResults.put("courses", courses);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/course/add", method = RequestMethod.POST)
//    @RequiresPermissions("course:add")
    public Object webManageCourseAdd(@RequestBody Course course) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null != course.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("新增失败：参数错误");
        } else {
            course.setCreateTime(new Date());
            course.setUpdateTime(new Date());
            courseService.addCourse(course);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/course/update", method = RequestMethod.PUT)
//    @RequiresPermissions("course:update")
    public Object webManageCourseUpdate(@RequestBody Course course) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null == course.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("修改失败：参数错误");
        } else {
            course.setUpdateTime(new Date());
            courseService.updateCourse(course);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/course/delete", method = RequestMethod.DELETE)
//    @RequiresPermissions("course:delete")
    public Object webManageCourseDelete(String id) {
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        courseService.deleteList(ids);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/course/detail", method = RequestMethod.GET)
//    @RequiresPermissions("course:detail")
    public Object webManageCourseDetail(Long id) {
        AjaxResults ajaxResults = new AjaxResults();
        ajaxResults.put("course", courseService.findById(id));
        return ajaxResults;
    }
}

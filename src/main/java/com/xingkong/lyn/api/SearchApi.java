package com.xingkong.lyn.api;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.Course;
import com.xingkong.lyn.entity.anjian.Law;
import com.xingkong.lyn.service.anjian.ICourse;
import com.xingkong.lyn.service.anjian.ILaw;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lyn on 2018/5/28.
 */
@RestController
public class SearchApi {
    @Resource
    private ILaw lawService;

    @Resource
    private ICourse courseService;

    @RequestMapping(value = "/api/search", method = RequestMethod.GET)
    public Object search(String query) {
        AjaxResults ajaxResults = new AjaxResults();
        List<Law> lawList = lawService.findByLawName(query);
        List<Course> courseList = courseService.findByCourseName(query);
        List<Map<String, String>> resultList = new LinkedList<>();
        lawList.forEach(law -> {
            Map<String, String> map = new HashMap<>();
            map.put("id", law.getId().toString());
            map.put("type", "law");
            resultList.add(map);
        });
        courseList.forEach(course -> {
            Map<String, String> map = new HashMap<>();
            map.put("id", course.getId().toString());
            map.put("type", "course");
            resultList.add(map);
        });
        return ajaxResults;
    }
}

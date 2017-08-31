package com.xingkong.lyn.service;

import com.xingkong.lyn.model.web.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
public interface ICourse {
    Page<Course> getCourseList(Pageable pageable);
    boolean addCourse(Course course);
    boolean updateCourse(Course course);
    boolean deleteCourse(List<Long> id);
    Course getCourse(Long id);
}

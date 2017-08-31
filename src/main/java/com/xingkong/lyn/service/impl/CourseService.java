package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.Course;
import com.xingkong.lyn.repository.web.CourseRepository;
import com.xingkong.lyn.service.ICourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
@Service
public class CourseService implements ICourse{
    @Resource
    private CourseRepository courseDao;

    @Override
    public Page<Course> getCourseList(Pageable pageable) {
        return courseDao.findAll(pageable);
    }

    @Override
    public boolean addCourse(Course course) {
        if (null != course.getId()) {
            return false;
        }
        courseDao.save(course);
        return true;
    }

    @Override
    public boolean updateCourse(Course course) {
        if (null == course.getId()) {
            return false;
        }
        courseDao.save(course);
        return true;
    }

    @Override
    public boolean deleteCourse(List<Long> id) {
        courseDao.deleteInBatch(courseDao.findAll(id));
        return true;
    }

    @Override
    public Course getCourse(Long id) {
        return courseDao.findOne(id);
    }
}

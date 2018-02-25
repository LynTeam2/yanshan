package com.xingkong.lyn.service.anjian.impl;

import com.xingkong.lyn.entity.anjian.Course;
import com.xingkong.lyn.repository.anjian.CourseRepository;
import com.xingkong.lyn.service.anjian.ICourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CourseService implements ICourse {
    @Resource
    private CourseRepository courseDao;

    @Override
    public Course findById(long id) {
        return courseDao.findOne(id);
    }

    @Override
    public Page<Course> findList(Pageable pageable) {
        return courseDao.findAll(pageable);
    }

    @Override
    public boolean addCourse(Course course) {
        courseDao.saveAndFlush(course);
        return true;
    }

    @Override
    public boolean deleteList(List<Long> ids) {
        courseDao.deleteInBatch(courseDao.findAll(ids));
        return true;
    }

    @Override
    public boolean updateCourse(Course course) {
        courseDao.saveAndFlush(course);
        return true;
    }

    @Override
    public List<Course> findList(String ajType) {
        return courseDao.findAllByAjType(ajType);
    }

    @Override
    public List<Long> findId(String ajType) {
        List<Long> ids = new LinkedList<>();
        List<Course> courses = courseDao.findAllByAjType(ajType);
        for (Course course : courses) {
            ids.add(course.getId());
        }
        return ids;
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public List<Course> findLatestCourseList() {
        return courseDao.findAllByHomePage(true);
    }
}

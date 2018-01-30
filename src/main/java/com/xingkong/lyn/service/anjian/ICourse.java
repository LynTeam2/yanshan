package com.xingkong.lyn.service.anjian;

import com.xingkong.lyn.entity.anjian.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICourse {
    Course findById(long id);
    Page<Course> findList(Pageable pageable);
    boolean addCourse(Course course);
    boolean deleteList(List<Long> ids);
    boolean updateCourse(Course course);
    List<Course> findList(String ajType);
    List<Long> findId(String ajType);
    List<Course> findAll();
}

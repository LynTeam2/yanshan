package com.xingkong.lyn.repository.anjian;

import com.xingkong.lyn.entity.anjian.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByAjType(String ajType);
    List<Course> findAllByHomePage(Boolean homepage);
    List<Course> findAllByCourseNameLike(String courseName);
    Page<Course> findByCourseNameLike(String courseName, Pageable pageable);
}

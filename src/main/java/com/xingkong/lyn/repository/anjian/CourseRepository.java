package com.xingkong.lyn.repository.anjian;

import com.xingkong.lyn.entity.anjian.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByAjType(String ajType);
}

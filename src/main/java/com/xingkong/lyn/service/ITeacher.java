package com.xingkong.lyn.service;

import com.xingkong.lyn.model.web.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
public interface ITeacher {
    Page<Teacher> getTeacherList(Pageable pageable);
    boolean addTeacher(Teacher teacher);
    boolean updateTeacher(Teacher teacher);
    boolean deleteTeacher(List<Long> id);
    Teacher getTeacher(Long id);
}

package com.xingkong.lyn.service;

import com.xingkong.lyn.model.web.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
public interface IStudent {
    Page<Student> getStudentList(Pageable pageable);
    boolean addStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(List<Long> id);
    Student getStudent(Long id);
}

package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.Student;
import com.xingkong.lyn.repository.web.StudentRepository;
import com.xingkong.lyn.service.IStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
@Service
public class StudentService implements IStudent{
    @Resource
    private StudentRepository studentDao;

    @Override
    public Page<Student> getStudentList(Pageable pageable) {
        return studentDao.findAll(pageable);
    }

    @Override
    public boolean addStudent(Student student) {
        if (null != student.getId()) {
            return false;
        }
        studentDao.save(student);
        return false;
    }

    @Override
    public boolean updateStudent(Student student) {
        if (null == student.getId()) {
            return false;
        }
        studentDao.save(student);
        return true;
    }

    @Override
    public boolean deleteStudent(List<Long> id) {
        studentDao.deleteInBatch(studentDao.findAll(id));
        return true;
    }

    @Override
    public Student getStudent(Long id) {
        return studentDao.findOne(id);
    }
}

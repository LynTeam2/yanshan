package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.Teacher;
import com.xingkong.lyn.repository.web.TeacherRepository;
import com.xingkong.lyn.service.ITeacher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
@Service
public class TeacherService implements ITeacher{
    @Resource
    private TeacherRepository teacherDao;

    @Override
    public Page<Teacher> getTeacherList(Pageable pageable) {
        return teacherDao.findAll(pageable);
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        if (null != teacher.getId()) {
            return false;
        }
        teacherDao.save(teacher);
        return true;
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        if (null == teacher.getId()) {
            return false;
        }
        teacherDao.save(teacher);
        return false;
    }

    @Override
    public boolean deleteTeacher(List<Long> id) {
        teacherDao.deleteInBatch(teacherDao.findAll(id));
        return true;
    }

    @Override
    public Teacher getTeacher(Long id) {
        return teacherDao.findOne(id);
    }
}

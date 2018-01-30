package com.xingkong.lyn.service.anjian.impl;

import com.xingkong.lyn.entity.anjian.Exam;
import com.xingkong.lyn.repository.anjian.ExamRepository;
import com.xingkong.lyn.service.anjian.IExam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExamService implements IExam {
    @Resource
    private ExamRepository examDao;

    @Override
    public Page<Exam> findList(Pageable pageable) {
        return examDao.findAll(pageable);
    }

    @Override
    public Exam findById(long id) {
        return examDao.findOne(id);
    }

    @Override
    public boolean addExam(Exam exam) {
        examDao.saveAndFlush(exam);
        return true;
    }

    @Override
    public boolean deleteList(List<Long> ids) {
        examDao.deleteInBatch(examDao.findAll(ids));
        return true;
    }

    @Override
    public boolean updateExam(Exam exam) {
        examDao.saveAndFlush(exam);
        return true;
    }

    @Override
    public List<Exam> findAll() {
        return examDao.findAll();
    }
}

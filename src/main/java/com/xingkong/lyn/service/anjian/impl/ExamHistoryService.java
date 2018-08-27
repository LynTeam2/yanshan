package com.xingkong.lyn.service.anjian.impl;

import com.xingkong.lyn.entity.anjian.ExamHistory;
import com.xingkong.lyn.repository.anjian.ExamHistoryRepository;
import com.xingkong.lyn.service.anjian.IExamHistory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2018/8/26.
 */
@Service
public class ExamHistoryService implements IExamHistory {

    @Resource
    private ExamHistoryRepository examHistoryDao;

    @Override
    public List<ExamHistory> findListByUser(Long userId) {
        return examHistoryDao.findByUserId(userId);
    }

    @Override
    public List<ExamHistory> findListByUnit(Long unitId) {
        return examHistoryDao.findByUnitId(unitId);
    }

    @Override
    public List<ExamHistory> findListByExam(Long examId) {
        return examHistoryDao.findByExamId(examId);
    }

    @Override
    public boolean addExamHistory(ExamHistory examHistory) {
        examHistoryDao.saveAndFlush(examHistory);
        return true;
    }

    @Override
    public Long getExamCount(Long userId, Long examId) {
        return examHistoryDao.countByUserIdAndExamId(userId, examId);
    }
}

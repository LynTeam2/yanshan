package com.xingkong.lyn.service.anjian;

import com.xingkong.lyn.entity.anjian.ExamHistory;

import java.util.List;

/**
 * Created by lyn on 2018/8/26.
 */
public interface IExamHistory {
    List<ExamHistory> findListByUser(Long userId);
    List<ExamHistory> findListByUnit(Long unitId);
    List<ExamHistory> findListByExam(Long examId);
    boolean addExamHistory(ExamHistory examHistory);
    Long getExamCount(Long userId, Long examId);
    List<ExamHistory> findListByUnitAndExam(Long unitId, Long examId);
}

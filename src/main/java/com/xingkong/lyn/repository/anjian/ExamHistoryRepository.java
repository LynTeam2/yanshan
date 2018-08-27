package com.xingkong.lyn.repository.anjian;

import com.xingkong.lyn.entity.anjian.ExamHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lyn on 2018/8/26.
 */
public interface ExamHistoryRepository extends JpaRepository<ExamHistory, Long> {
    List<ExamHistory> findByUserId(Long userId);
    List<ExamHistory> findByUnitId(Long unitId);
    List<ExamHistory> findByExamId(Long examId);
    Long countByUserIdAndExamId(Long userId, Long examId);
}

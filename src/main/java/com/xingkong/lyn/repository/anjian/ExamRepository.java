package com.xingkong.lyn.repository.anjian;

import com.xingkong.lyn.entity.anjian.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    Page<Exam> findByExamNameLike(String examName, Pageable pageable);
}

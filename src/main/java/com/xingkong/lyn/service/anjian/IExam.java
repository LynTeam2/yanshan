package com.xingkong.lyn.service.anjian;

import com.xingkong.lyn.entity.anjian.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IExam {
    Page<Exam> findList(Pageable pageable, String query);
    Exam findById(long id);
    boolean addExam(Exam exam);
    boolean deleteList(List<Long> ids);
    boolean updateExam(Exam exam);
    List<Exam> findAll();
}

package com.xingkong.lyn.service.anjian;

import com.xingkong.lyn.entity.anjian.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Date;
import java.util.List;

public interface IQuestion {
    List<TrueFalse> findNewtf(Date updateTime);
    List<BlankFilling> findNewbf(Date updateTime);
    List<SimpleChoice> findNewsc(Date updateTime);
    List<MultipleChoice> findNewmc(Date updateTime);
    Page<TrueFalse> findtfList(Pageable pageable);
    Page<BlankFilling> findbfList(Pageable pageable);
    Page<SimpleChoice> findscList(Pageable pageable);
    Page<MultipleChoice> findmcList(Pageable pageable);
    boolean addQuestion(Question question);
    boolean updateQuestion(Question question);
    boolean deleteList(String questionType, List<Long> ids);
    TrueFalse findtf(Long id);
    BlankFilling findbf(Long id);
    SimpleChoice findsc(Long id);
    MultipleChoice findmc(Long id);
    List<TrueFalse> findNewtf();
    List<BlankFilling> findNewbf();
    List<SimpleChoice> findNewsc();
    List<MultipleChoice> findNewmc();
}

package com.xingkong.lyn.service.anjian;

import com.xingkong.lyn.entity.anjian.Law;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lyn on 2018/5/3.
 */
public interface ILaw {
    Page<Law> findList(Pageable pageable, String type);
    Page<Law> findListByQuery(Pageable pageable, String query);
    Law findById(Long id);
    boolean addLaw(Law law);
    boolean deleteList(List<Long> ids);
    boolean updateLaw(Law law);
    List<Law> findByLawName(String lawName);
}

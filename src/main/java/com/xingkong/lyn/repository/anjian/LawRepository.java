package com.xingkong.lyn.repository.anjian;

import com.xingkong.lyn.entity.anjian.Law;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lyn on 2018/5/3.
 */
public interface LawRepository extends JpaRepository<Law, Long>{
    Page<Law> findAllByLawType(Pageable pageable, String lawType);
    List<Law> findAllByLawNameLike(String lawName);
    Page<Law> findByLawNameLike(String lawName, Pageable pageable);
}

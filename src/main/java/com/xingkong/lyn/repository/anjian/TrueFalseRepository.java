package com.xingkong.lyn.repository.anjian;

import com.xingkong.lyn.entity.anjian.TrueFalse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TrueFalseRepository extends JpaRepository<TrueFalse, Long> {
    List<TrueFalse> findAllByUpdateTimeAfter(Date updateTime);
    Page<TrueFalse> findByUidLike(String uid, Pageable pageable);
}

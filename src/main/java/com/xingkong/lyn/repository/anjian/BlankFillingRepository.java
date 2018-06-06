package com.xingkong.lyn.repository.anjian;

import com.xingkong.lyn.entity.anjian.BlankFilling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BlankFillingRepository extends JpaRepository<BlankFilling, Long> {
    List<BlankFilling> findAllByUpdateTimeAfter(Date updateTime);
}

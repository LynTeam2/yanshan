package com.xingkong.lyn.repository.anjian;

import com.xingkong.lyn.entity.anjian.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    Page<Unit> findByUnitNameLike(String unitName, Pageable pageable);
}

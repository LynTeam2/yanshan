package com.xingkong.lyn.repository.anjian;

import com.xingkong.lyn.entity.anjian.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    List<Unit> findByProvinceCode(String provinceCode);

    List<Unit> findByCityCode(String cityCode);

    List<Unit> findByCountyCode(String countyCode);
}

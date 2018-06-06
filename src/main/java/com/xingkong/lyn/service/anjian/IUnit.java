package com.xingkong.lyn.service.anjian;

import com.xingkong.lyn.entity.anjian.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUnit {
    List<Unit> findListByProvinceCode(String provinceCode);
    List<Unit> findListByCityCode(String cityCode);
    List<Unit> findListByCountyCode(String countyCode);
    boolean addUnit(Unit unit);
    Unit findById(long id);
    boolean deleteList(List<Long> ids);
    boolean updateUnit(Unit unit);
    Page<Unit> findList(Pageable pageable);
}

package com.xingkong.lyn.service.anjian.impl;

import com.xingkong.lyn.entity.anjian.Unit;
import com.xingkong.lyn.repository.anjian.UnitRepository;
import com.xingkong.lyn.service.anjian.IUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UnitService implements IUnit {
    @Resource
    private UnitRepository unitDao;

    @Override
    public List<Unit> findListByProvinceCode(String provinceCode) {
        return unitDao.findByProvinceCode(provinceCode);
    }

    @Override
    public List<Unit> findListByCityCode(String cityCode) {
        return unitDao.findByCityCode(cityCode);
    }

    @Override
    public List<Unit> findListByCountyCode(String countyCode) {
        return unitDao.findByCountyCode(countyCode);
    }

    @Override
    @Transactional
    public boolean addUnit(Unit unit) {
        unitDao.saveAndFlush(unit);
        return true;
    }

    @Override
    public Unit findById(long id) {
        return unitDao.findOne(id);
    }

    @Override
    public boolean deleteList(List<Long> ids) {
        unitDao.deleteInBatch(unitDao.findAll(ids));
        return true;
    }

    @Override
    @Transactional
    public boolean updateUnit(Unit unit) {
        unitDao.saveAndFlush(unit);
        return true;
    }

    @Override
    public Page<Unit> findList(Pageable pageable) {
        return unitDao.findAll(pageable);
    }
}

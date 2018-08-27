package com.xingkong.lyn.service.anjian.impl;

import com.xingkong.lyn.entity.anjian.Unit;
import com.xingkong.lyn.repository.anjian.UnitRepository;
import com.xingkong.lyn.service.anjian.IUnit;
import org.apache.commons.lang3.StringUtils;
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
    public Page<Unit> findList(Pageable pageable, String query) {
        return StringUtils.isBlank(query)?unitDao.findAll(pageable):unitDao.findByUnitNameLike(query, pageable);
    }

    @Override
    public List<Unit> findAll() {
        return unitDao.findAll();
    }
}

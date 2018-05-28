package com.xingkong.lyn.service.anjian.impl;

import com.xingkong.lyn.entity.anjian.Law;
import com.xingkong.lyn.repository.anjian.LawRepository;
import com.xingkong.lyn.service.anjian.ILaw;
import com.xingkong.lyn.util.OtherUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2018/5/3.
 */
@Service
public class LawService implements ILaw{
    @Resource
    private LawRepository lawDao;

    @Override
    public Page<Law> findList(Pageable pageable, String lawType) {
        return lawDao.findAllByLawType(pageable, lawType);
    }

    @Override
    public Page<Law> findListByQuery(Pageable pageable, String query) {
        return StringUtils.isBlank(query)?lawDao.findAll(pageable):lawDao.findByLawNameLike(OtherUtil.handleQuery(query), pageable);
    }

    @Override
    public Law findById(Long id) {
        return lawDao.findOne(id);
    }

    @Override
    public boolean addLaw(Law law) {
        lawDao.saveAndFlush(law);
        return true;
    }

    @Override
    public boolean deleteList(List<Long> ids) {
        lawDao.deleteInBatch(lawDao.findAll(ids));
        return true;
    }

    @Override
    public boolean updateLaw(Law law) {
        lawDao.saveAndFlush(law);
        return true;
    }

    @Override
    public List<Law> findByLawName(String lawName) {
        return StringUtils.isBlank(lawName)?lawDao.findAll():lawDao.findAllByLawNameLike(lawName);
    }
}

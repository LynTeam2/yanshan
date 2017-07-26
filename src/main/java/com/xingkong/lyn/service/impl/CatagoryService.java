package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.Catagory;
import com.xingkong.lyn.repository.web.CatagoryRepository;
import com.xingkong.lyn.repository.web.ProductRepository;
import com.xingkong.lyn.service.ICatagory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
@Service
public class CatagoryService implements ICatagory{
    @Resource
    private CatagoryRepository catagoryDao;
    @Resource
    private ProductRepository productDao;

    @Override
    public List<Catagory> getCatagoryList() {
        return catagoryDao.findAll();
    }

    @Override
    public List<Catagory> getSubCatagory(Long parentId) {
        return catagoryDao.findByParentId(parentId);
    }

    @Override
    public Page<Catagory> getCatagoryTree(Long parentId, Pageable pageable) {
        return catagoryDao.findAllByParentId(parentId, pageable);
    }

    @Override
    public boolean addCatagory(Catagory catagory) {
        catagoryDao.saveAndFlush(catagory);
        return true;
    }

    @Override
    public boolean updateCatagory(Catagory catagory) {
        catagoryDao.saveAndFlush(catagory);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCatagory(List<Long> id) {
        catagoryDao.deleteInBatch(catagoryDao.findAll(id));
        return true;
    }

    @Override
    public Catagory getCatagory(Long id) {
        return catagoryDao.findOne(id);
    }
}

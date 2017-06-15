package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.Catagory;
import com.xingkong.lyn.repository.web.CatagoryRepository;
import com.xingkong.lyn.service.ICatagory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
@Service
public class CatagoryService implements ICatagory{
    @Resource
    private CatagoryRepository catagoryDao;

    @Override
    public List<Catagory> getCatagoryList() {
        return catagoryDao.findAll();
    }

    @Override
    public List<Catagory> getSubCatagory(Long parentId) {
        return catagoryDao.findByParentId(parentId);
    }
}

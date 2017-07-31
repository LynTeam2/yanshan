package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.Category;
import com.xingkong.lyn.repository.web.CategoryRepository;
import com.xingkong.lyn.repository.web.ProductRepository;
import com.xingkong.lyn.service.ICategory;
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
public class CategoryService implements ICategory {
    @Resource
    private CategoryRepository categoryDao;
    @Resource
    private ProductRepository productDao;

    @Override
    public List<Category> getCategoryList() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category> getSubCategory(Long parentId) {
        return categoryDao.findByParentId(parentId);
    }

    @Override
    public Page<Category> getCategoryTree(Long parentId, Pageable pageable) {
        return categoryDao.findAllByParentId(parentId, pageable);
    }

    @Override
    public boolean addCategory(Category catagory) {
        categoryDao.saveAndFlush(catagory);
        return true;
    }

    @Override
    public boolean updateCategory(Category catagory) {
        categoryDao.saveAndFlush(catagory);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCategory(List<Long> id) {
        List<Category> list = categoryDao.findAll(id);
        categoryDao.deleteInBatch(list);
        return true;
    }

    @Override
    public Category getCategory(Long id) {
        return categoryDao.findOne(id);
    }
}

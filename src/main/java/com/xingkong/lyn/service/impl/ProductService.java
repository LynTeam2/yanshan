package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.Product;
import com.xingkong.lyn.repository.web.ProductRepository;
import com.xingkong.lyn.service.IProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
@Service
public class ProductService implements IProduct{
    @Resource
    private ProductRepository productDao;

    @Override
    public List<Product> getProductList() {
        return productDao.findAll();
    }

    @Override
    public Product getDetail(Long id) {
        return productDao.findOne(id);
    }

    @Override
    public Page<Product> getProductListByPageable(Long catagoryId, Pageable pageable) {
        return productDao.findByCatagoryId(catagoryId, pageable);
    }

    @Override
    public boolean addProduct(Product product) {
        productDao.saveAndFlush(product);
        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        productDao.saveAndFlush(product);
        return true;
    }

    @Override
    public boolean deleteProduct(Long id) {
        productDao.delete(id);
        return true;
    }

}

package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.Category;
import com.xingkong.lyn.model.web.Image;
import com.xingkong.lyn.model.web.Product;
import com.xingkong.lyn.repository.web.CategoryRepository;
import com.xingkong.lyn.repository.web.HtmlRepository;
import com.xingkong.lyn.repository.web.ImageRepository;
import com.xingkong.lyn.repository.web.ProductRepository;
import com.xingkong.lyn.service.IProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
@Service
public class ProductService implements IProduct{
    @Resource
    private ProductRepository productDao;
    @Resource
    private ImageRepository imageDao;
    @Resource
    private HtmlRepository htmlDao;
    @Resource
    private CategoryRepository catagoryDao;

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
        Page<Product> products = null;
        if(null == catagoryId){
            products = productDao.findAll(pageable);
        }else {
            Category catagory = catagoryDao.findOne(catagoryId);
            products = new PageImpl<Product>(catagory.getProducts());
        }
        return products;
    }

    @Override
    @Transactional
    public boolean addProduct(Product product) {
        productDao.save(product);
        return true;
    }

    @Override
    @Transactional
    public boolean updateProduct(Product product) {
        productDao.save(product);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteProduct(List<Long> id) {
        productDao.deleteInBatch(productDao.findAll(id));
        return true;
    }

    @Override
    public Image addImage(Image image) {
        return imageDao.save(image);
    }

    @Override
    public Page<Product> getIndexProducts(Byte homeFlag, Byte recommendFlag, Pageable pageable) {
        if(null != homeFlag){
            return productDao.findByHomeFlag(homeFlag, pageable);
        }else{
            return productDao.findByRecommendFlag(recommendFlag, pageable);
        }
    }

}

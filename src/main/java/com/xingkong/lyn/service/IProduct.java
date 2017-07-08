package com.xingkong.lyn.service;

import com.xingkong.lyn.model.web.Image;
import com.xingkong.lyn.model.web.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
public interface IProduct {
    List<Product> getProductList();

    Product getDetail(Long id);

    Page<Product> getProductListByPageable(Long catagoryId, Pageable pageable);

    boolean addProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(List<Long> id);

    Image addImage(Image image);
}

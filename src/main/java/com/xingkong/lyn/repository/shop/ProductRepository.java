package com.xingkong.lyn.repository.shop;

import com.xingkong.lyn.model.shop.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lyn on 2017/6/5.
 */
public interface ProductRepository extends JpaRepository<Product,Long>{
}

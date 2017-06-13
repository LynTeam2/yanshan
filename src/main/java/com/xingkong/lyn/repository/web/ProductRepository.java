package com.xingkong.lyn.repository.web;

import com.xingkong.lyn.model.web.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lyn on 2017/6/13.
 */
public interface ProductRepository extends JpaRepository<Product, Long>{
}

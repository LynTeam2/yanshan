package com.xingkong.lyn.repository.web;

import com.xingkong.lyn.model.web.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
public interface ProductRepository extends JpaRepository<Product, Long>{
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByHomeFlag(Byte homeFlag, Pageable pageable);
    Page<Product> findByRecommendFlag(Byte recommendFlag, Pageable pageable);
}

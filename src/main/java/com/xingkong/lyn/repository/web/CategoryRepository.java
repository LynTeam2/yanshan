package com.xingkong.lyn.repository.web;

import com.xingkong.lyn.model.web.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
public interface CategoryRepository extends JpaRepository<Category, Long>{
    List<Category> findByParentId(Long id);
    Page<Category> findAllByParentId(Long id, Pageable pageable);
}

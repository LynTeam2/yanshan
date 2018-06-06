package com.xingkong.lyn.service.anjian;

import com.xingkong.lyn.entity.anjian.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
public interface ICategory {
    List<Category> getCategoryList();
    List<Category> getSubCategory(Long parentId);
    Page<Category> getCategoryTree(Long parentId, Pageable pageable);
    boolean addCategory(Category catagory);
    boolean updateCategory(Category catagory);
    boolean deleteCategory(List<Long> id);
    Category getCategory(Long id);
}

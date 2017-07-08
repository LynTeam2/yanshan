package com.xingkong.lyn.service;

import com.xingkong.lyn.model.web.Catagory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
public interface ICatagory {
    List<Catagory> getCatagoryList();
    List<Catagory> getSubCatagory(Long parentId);
    Page<Catagory> getCatagoryTree(Pageable pageable);
    boolean addCatagory(Catagory catagory);
    boolean updateCatagory(Catagory catagory);
    boolean deleteCatagory(List<Long> id);
}

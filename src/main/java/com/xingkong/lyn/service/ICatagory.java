package com.xingkong.lyn.service;

import com.xingkong.lyn.model.web.Catagory;

import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
public interface ICatagory {
    List<Catagory> getCatagoryList();
    List<Catagory> getSubCatagory(Long parentId);
}

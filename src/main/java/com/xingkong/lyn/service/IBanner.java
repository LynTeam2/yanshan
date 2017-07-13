package com.xingkong.lyn.service;

import com.xingkong.lyn.model.web.Banner;

import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
public interface IBanner {
    List<Banner> findAll();
    List<Banner> findByPosition(String position);
    Boolean deleteList(List<Long> ids);
    Boolean addBanner(Banner banner);
}

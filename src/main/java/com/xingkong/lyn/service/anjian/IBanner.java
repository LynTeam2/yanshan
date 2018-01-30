package com.xingkong.lyn.service.anjian;

import com.xingkong.lyn.entity.anjian.Banner;

import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
public interface IBanner {
    List<Banner> findAll();
    Boolean deleteList(List<Long> ids);
    Boolean addBanner(Banner banner);
    Boolean saveBanners(List<Banner> banners);
}

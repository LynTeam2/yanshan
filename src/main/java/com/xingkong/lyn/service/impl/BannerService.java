package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.Banner;
import com.xingkong.lyn.repository.web.BannerRepository;
import com.xingkong.lyn.service.IBanner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
@Service
public class BannerService implements IBanner{
    @Resource
    private BannerRepository bannerDao;

    @Override
    public List<Banner> findAll() {
        return bannerDao.findAll();
    }

    @Override
    public List<Banner> findByPosition(String position) {
        return bannerDao.findByPosition(position);
    }

    @Override
    public Boolean deleteList(List<Long> ids) {
        bannerDao.deleteInBatch(bannerDao.findAll(ids));
        return true;
    }

    @Override
    public Boolean addBanner(Banner banner) {
        bannerDao.saveAndFlush(banner);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveBanners(List<Banner> banners) {
        bannerDao.deleteAllInBatch();
        bannerDao.save(banners);
        return true;
    }
}

package com.xingkong.lyn.service.anjian.impl;

import com.xingkong.lyn.entity.anjian.Banner;
import com.xingkong.lyn.repository.anjian.BannerRepository;
import com.xingkong.lyn.service.anjian.IBanner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BannerService implements IBanner {
    @Resource
    private BannerRepository bannerDao;

    @Override
    public List<Banner> findAll() {
        return bannerDao.findAll();
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

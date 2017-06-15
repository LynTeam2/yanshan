package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.WebInfo;
import com.xingkong.lyn.repository.web.WebInfoRepository;
import com.xingkong.lyn.service.IWebInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lyn on 2017/6/14.
 */
@Service
public class WebInfoService implements IWebInfo{
    @Resource
    private WebInfoRepository webInfoDao;

    @Override
    public WebInfo getWebInfo() {
        return webInfoDao.findOne(1L);
    }
}

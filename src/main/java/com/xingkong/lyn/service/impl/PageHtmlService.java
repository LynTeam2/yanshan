package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.PageHtml;
import com.xingkong.lyn.repository.web.PageHtmlRepository;
import com.xingkong.lyn.service.IPageHtml;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lyn on 2017/6/13.
 */
@Service
public class PageHtmlService implements IPageHtml{
    @Resource
    private PageHtmlRepository pageHtmlDao;

    @Override
    public PageHtml getPageHtml(String page) {
        return pageHtmlDao.findByPage(page);
    }
}

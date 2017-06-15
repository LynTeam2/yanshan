package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.News;
import com.xingkong.lyn.repository.web.NewsRepository;
import com.xingkong.lyn.service.INews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
@Service
public class NewsService implements INews{
    @Resource
    private NewsRepository newsDao;

    @Override
    public List<News> getNewsList() {
        return newsDao.findAll();
    }

    @Override
    public News getNews(Long id) {
        return newsDao.findOne(id);
    }

    @Override
    public List<News> getIndexNews(Integer limit) {
        return newsDao.findTopOrderByNewsTimeDesc(limit);
    }

    @Override
    public Page<News> getNewsByPageable(Pageable pageable) {
        return newsDao.findByPageable(pageable);
    }
}

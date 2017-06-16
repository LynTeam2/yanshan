package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.News;
import com.xingkong.lyn.repository.web.NewsRepository;
import com.xingkong.lyn.service.INews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public Page<News> getNewsByPageable(Pageable pageable) {
        return newsDao.findAll(pageable);
    }

    @Override
    public boolean addNews(News news) {
        newsDao.saveAndFlush(news);
        return true;
    }

    @Override
    public boolean deleteNews(Long id) {
        newsDao.delete(id);
        return true;
    }
}

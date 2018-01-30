package com.xingkong.lyn.service.anjian.impl;

import com.xingkong.lyn.entity.anjian.News;
import com.xingkong.lyn.repository.anjian.NewsRepository;
import com.xingkong.lyn.service.anjian.INews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsService implements INews {
    @Resource
    private NewsRepository newsDao;

    @Override
    public News getNews(Long id) {
        return newsDao.findOne(id);
    }

    @Override
    public boolean addNews(News news) {
        newsDao.saveAndFlush(news);
        return true;
    }

    @Override
    public boolean updateNews(News news) {
        newsDao.saveAndFlush(news);
        return true;
    }

    @Override
    public boolean deleteNews(List<Long> ids) {
        newsDao.deleteInBatch(newsDao.findAll(ids));
        return true;
    }

    @Override
    public Page<News> getNewsList(Pageable pageable) {
        return newsDao.findAll(pageable);
    }
}

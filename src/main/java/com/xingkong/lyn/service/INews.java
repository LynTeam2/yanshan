package com.xingkong.lyn.service;

import com.xingkong.lyn.model.web.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
public interface INews {
    List<News> getNewsList();
    News getNews(Long id);
    Page<News> getNewsByPageable(Pageable pageable);
}

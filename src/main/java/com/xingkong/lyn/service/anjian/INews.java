package com.xingkong.lyn.service.anjian;

import com.xingkong.lyn.entity.anjian.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
public interface INews {
    News getNews(Long id);
    boolean addNews(News news);
    boolean updateNews(News news);
    boolean deleteNews(List<Long> ids);
    Page<News> getNewsList(Pageable pageable);
}

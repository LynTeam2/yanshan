package com.xingkong.lyn.controller;

import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.web.News;
import com.xingkong.lyn.service.INews;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lyn on 2017/6/14.
 */
@RestController
public class NewsController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Resource
    private INews newsService;

    @RequestMapping(value = "/web/news/list")
    public Object webNewsList(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                      Pageable pageable){
        AjaxResults ajaxResults = new AjaxResults();
        Page<News> news = newsService.getNewsByPageable(pageable);
        ajaxResults.put("newsList", news);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/news/detail")
    public Object webNewsDetail(Long id){
        AjaxResults ajaxResults = new AjaxResults();
        News news = newsService.getNews(id);
        ajaxResults.put("information", news.getHtml());
        return ajaxResults;
    }
}

package com.xingkong.lyn.api;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.News;
import com.xingkong.lyn.service.anjian.INews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lyn on 2018/1/28.
 */
@RestController
@RequestMapping(value = "/api/news")
public class NewsApi {
    @Resource
    private INews newsService;

    @RequestMapping(method = RequestMethod.GET)
    public Object get(@PageableDefault(value = 15, sort = { "createTime" }, direction = Sort.Direction.DESC)
                              Pageable pageable) {
        AjaxResults ajaxResults = new AjaxResults();
        Page<News> newsList = newsService.getNewsList(pageable);
        ajaxResults.put("newsList", newsList);
        return ajaxResults;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Object get(@PathVariable Long id) {
        AjaxResults ajaxResults = new AjaxResults();
        News news = newsService.getNews(id);
        ajaxResults.put("news", news);
        return ajaxResults;
    }
}

package com.xingkong.lyn.api;

import com.xingkong.lyn.annotation.AdminLog;
import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.News;
import com.xingkong.lyn.model.UserInfo;
import com.xingkong.lyn.service.anjian.INews;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
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
    @RequiresPermissions("news:view")
    @AdminLog(value = "新闻:查看列表")
    public Object get(@PageableDefault(value = 15, sort = { "createTime" }, direction = Sort.Direction.DESC)
                              Pageable pageable) {
        AjaxResults ajaxResults = new AjaxResults();
        Page<News> newsList = newsService.getNewsList(pageable, null);
        newsList.getContent().stream().forEach((news1) -> news1.setContent(new String(news1.getContentByte())));
        ajaxResults.put("newsList", newsList);
        return ajaxResults;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @RequiresPermissions("news:detail")
    @AdminLog(value = "新闻:查看详情")
    public Object get(@PathVariable Long id) {
        AjaxResults ajaxResults = new AjaxResults();
        News news = newsService.getNews(id);
        news.setContent(new String(news.getContentByte()));
        ajaxResults.put("news", news);
        return ajaxResults;
    }
}

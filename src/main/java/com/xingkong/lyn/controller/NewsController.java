package com.xingkong.lyn.controller;

import com.xingkong.lyn.annotation.AdminLog;
import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.web.News;
import com.xingkong.lyn.service.INews;
import com.xingkong.lyn.util.OtherUtil;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 2017/6/14.
 */
@RestController
public class NewsController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Resource
    private INews newsService;

    @RequestMapping(value = "/web/news/list", method = RequestMethod.GET)
    public Object webNewsList(@PageableDefault(value = 15, sort = { "newsTime" }, direction = Sort.Direction.DESC)
                                      Pageable pageable){
        AjaxResults ajaxResults = new AjaxResults();
        Page<News> news = newsService.getNewsByPageable(pageable);
        ajaxResults.put("newsList", news.getContent());
        return ajaxResults;
    }

    @RequestMapping(value = "/web/news/detail", method = RequestMethod.GET)
    public Object webNewsDetail(Long id){
        AjaxResults ajaxResults = new AjaxResults();
        News news = newsService.getNews(id);
//        ajaxResults.put("information", news.getHtml());
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/news/list", method = RequestMethod.GET)
//    @RequiresPermissions("news:view")
    public Object webManageNewsList(@PageableDefault(value = 15, sort = { "newsTime" }, direction = Sort.Direction.DESC)
                                        Pageable pageable){
        AjaxResults ajaxResults = new AjaxResults();
        Page<News> news = newsService.getNewsByPageable(pageable);
        ajaxResults.put("newsList", news.getContent());
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/news/detail", method = RequestMethod.GET)
//    @RequiresPermissions("news:detail")
    public Object webManageNewsDetail(Long id){
        AjaxResults ajaxResults = new AjaxResults();
        News news = newsService.getNews(id);
        ajaxResults.put("news", news);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/news/add", method = RequestMethod.POST)
//    @RequiresPermissions("news:add")
//    @AdminLog(value = "网站:添加新闻")
    public Object webManageNewsAdd(@RequestBody News news){
        AjaxResults ajaxResults = new AjaxResults();
        if(null != news.getId()){
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        }else{
            news.setCreateTime(new Date());
            newsService.addNews(news);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/news/update", method = RequestMethod.PUT)
//    @RequiresPermissions("news:update")
//    @AdminLog(value = "网站:修改新闻")
    public Object webManageNewsUpdate(@RequestBody News news){
        AjaxResults ajaxResults = new AjaxResults();
        if(null == news.getId()){
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        }else{
            news.setCreateTime(new Date());
            newsService.addNews(news);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/news/delete", method = RequestMethod.DELETE)
//    @RequiresPermissions("news:delete")
//    @AdminLog(value = "网站:删除新闻")
    public Object webManageNewsDelete(String id){
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        newsService.deleteNews(ids);
        return ajaxResults;
    }
}

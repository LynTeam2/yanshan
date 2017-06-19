package com.xingkong.lyn.controller;

import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.web.PageHtml;
import com.xingkong.lyn.service.IPageHtml;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lyn on 2017/6/14.
 */
@RestController
public class PageController {
    private Logger logger = LoggerFactory.getLogger(PageController.class);

    @Resource
    private IPageHtml pageHtmlService;

    @RequestMapping(value = "/web/introduction", method = RequestMethod.GET)
    public Object webIntroduction(){
        AjaxResults ajaxResults = new AjaxResults();
        PageHtml pageHtml = pageHtmlService.getPageHtml("introduction");
        ajaxResults.put("introduction", pageHtml.getHtml());
        return ajaxResults;
    }

    @RequestMapping(value = "/web/contactus", method = RequestMethod.GET)
    public Object webContactUs(){
        AjaxResults ajaxResults = new AjaxResults();
        PageHtml pageHtml = pageHtmlService.getPageHtml("contactUs");
        ajaxResults.put("contactUs", pageHtml.getHtml());
        return ajaxResults;
    }

    @RequestMapping(value = "/web/joinUs", method = RequestMethod.GET)
    public Object webJoinUs(){
        AjaxResults ajaxResults = new AjaxResults();
        PageHtml pageHtml = pageHtmlService.getPageHtml("introduction");
        ajaxResults.put("joinUs", pageHtml.getHtml());
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/pagehtml/list", method = RequestMethod.GET)
//    @RequiresPermissions("pagehtml:view")
    public Object webManageNewsList(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                                  Pageable pageable){
        AjaxResults ajaxResults = new AjaxResults();
        Page<PageHtml> pageHtmls = pageHtmlService.getPageHtmls(pageable);
        ajaxResults.put("pagehtmls", pageHtmls);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/pagehtml/detail", method = RequestMethod.GET)
//    @RequiresPermissions("pagehtml:detail")
    public Object webManageNewsDetail(Long id){
        AjaxResults ajaxResults = new AjaxResults();
        PageHtml pageHtml = pageHtmlService.getPageHtml(id);
        ajaxResults.put("pagehtml", pageHtml);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/pagehtml/add", method = RequestMethod.POST)
//    @RequiresPermissions("pagehtml:add")
    public Object webManageNewsAdd(PageHtml pageHtml){
        AjaxResults ajaxResults = new AjaxResults();
        pageHtmlService.addPageHtml(pageHtml);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/pagehtml/update", method = RequestMethod.PUT)
//    @RequiresPermissions("pagehtml:update")
    public Object webManageNewsUpdate(PageHtml pageHtml){
        AjaxResults ajaxResults = new AjaxResults();
        pageHtmlService.updatePageHtml(pageHtml);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/pagehtml/delete", method = RequestMethod.DELETE)
//    @RequiresPermissions("pagehtml:delete")
    public Object webManageNewsDelete(Long id){
        AjaxResults ajaxResults = new AjaxResults();
        pageHtmlService.deletePageHtml(id);
        return ajaxResults;
    }
}

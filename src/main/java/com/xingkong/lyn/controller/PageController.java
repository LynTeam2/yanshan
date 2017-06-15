package com.xingkong.lyn.controller;

import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.web.PageHtml;
import com.xingkong.lyn.service.IPageHtml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lyn on 2017/6/14.
 */
@RestController
public class PageController {
    private Logger logger = LoggerFactory.getLogger(PageController.class);

    @Resource
    private IPageHtml pageHtmlService;

    @RequestMapping(value = "/web/introduction")
    public Object webIntroduction(){
        AjaxResults ajaxResults = new AjaxResults();
        PageHtml pageHtml = pageHtmlService.getPageHtml("introduction");
        ajaxResults.put("introduction", pageHtml.getHtml());
        return ajaxResults;
    }

    @RequestMapping(value = "/web/contactus")
    public Object webContactUs(){
        AjaxResults ajaxResults = new AjaxResults();
        PageHtml pageHtml = pageHtmlService.getPageHtml("contactUs");
        ajaxResults.put("contactUs", pageHtml.getHtml());
        return ajaxResults;
    }

    @RequestMapping(value = "/web/joinUs")
    public Object webJoinUs(){
        AjaxResults ajaxResults = new AjaxResults();
        PageHtml pageHtml = pageHtmlService.getPageHtml("introduction");
        ajaxResults.put("joinUs", pageHtml.getHtml());
        return ajaxResults;
    }
}

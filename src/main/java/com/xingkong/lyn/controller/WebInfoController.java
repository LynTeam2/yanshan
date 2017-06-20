package com.xingkong.lyn.controller;

import com.xingkong.lyn.annotation.AdminLog;
import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.web.WebInfo;
import com.xingkong.lyn.service.IWebInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.OneToMany;

/**
 * Created by lyn on 2017/6/16.
 */
@RestController
public class WebInfoController {
    @Resource
    private IWebInfo webInfoService;

    @RequestMapping(value = "/web/manage/webInfo/detail", method = RequestMethod.GET)
//    @RequiresPermissions("webinfo:detail")
    public Object webManageWebInfoDetail(){
        AjaxResults ajaxResults = new AjaxResults();
        WebInfo webInfo = webInfoService.getWebInfo();
        ajaxResults.put("webInfo", webInfo);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/webInfo/update", method = RequestMethod.PUT)
//    @RequiresPermissions("webinfo:update")
    @AdminLog("网站:修改网站基本信息")
    public Object webManageWebInfoUpdate(WebInfo webInfo){
        AjaxResults ajaxResults = new AjaxResults();
        webInfoService.saveWebInfo(webInfo);
        return ajaxResults;
    }

}

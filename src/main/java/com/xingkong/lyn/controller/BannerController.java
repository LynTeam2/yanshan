package com.xingkong.lyn.controller;

import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.web.Banner;
import com.xingkong.lyn.service.IBanner;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/7/27.
 */
@RestController
public class BannerController {
    @Resource
    private IBanner bannerService;

    @RequestMapping(value = "/web/manage/banner/save", method = RequestMethod.POST)
///    @RequiresPermissions("banner:save")
    public Object webManageBannerSave(@RequestBody List<Banner> banners){
        AjaxResults ajaxResults = new AjaxResults();
        bannerService.saveBanners(banners);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/banner/list", method = RequestMethod.GET)
///    @RequiresPermissions("banner:view")
    public Object webManageBannerList(){
        AjaxResults ajaxResults = new AjaxResults();
        List<Banner> banners = bannerService.findAll();
        ajaxResults.put("banners", banners);
        return ajaxResults;
    }

//    @RequestMapping(value = "/web/banner/list", method = RequestMethod.GET)
//    public Object webBannerList(String position){
//        AjaxResults ajaxResults = new AjaxResults();
//        List<Banner> banners = bannerService.findByPosition(position);
//        ajaxResults.put("banners", banners);
//        return ajaxResults;
//    }
}

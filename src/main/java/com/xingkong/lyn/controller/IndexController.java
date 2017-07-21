package com.xingkong.lyn.controller;


import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.web.Banner;
import com.xingkong.lyn.model.web.WebInfo;
import com.xingkong.lyn.service.IBanner;
import com.xingkong.lyn.service.INews;
import com.xingkong.lyn.service.IProduct;
import com.xingkong.lyn.service.IWebInfo;
import com.xingkong.lyn.util.OtherUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 2017/4/24.
 * @CrossGrigin springboot用于跨域的注解
 */
@RestController
//@CrossOrigin
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private IBanner bannerService;

    @Resource
    private INews newsService;

    @Resource
    private IWebInfo webInfoService;

    @Resource
    private IProduct productService;

    @RequestMapping(value = "/web/index/banner", method = RequestMethod.GET)
    public Object webIndexBanner(){
        AjaxResults ajaxResults = new AjaxResults();
        List<Banner> banners = bannerService.findAll();
        ajaxResults.put("banners",banners);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/banner/delete",method = RequestMethod.DELETE)
    public Object webManageNewsDelete(String id){
        AjaxResults ajaxResults = new AjaxResults();
        Long[] ids = StringUtils.isBlank(id)? null : OtherUtil.parseStringtoLong(id);
        List<Long> idList= Arrays.asList(ids);
        bannerService.deleteList(idList);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/banner/add",method = RequestMethod.POST)
    public Object webManageMessAdd(Banner banner){
        AjaxResults ajaxResults = new AjaxResults();
        banner.setCreateTime(new Date());
        bannerService.addBanner(banner);
        return ajaxResults;
    }


    @RequestMapping(value = "/web/index/news", method = RequestMethod.GET)
    public AjaxResults webIndexNews(Integer limit){
        AjaxResults ajaxResults = new AjaxResults();
        if(null == limit){
            ajaxResults.put("news", newsService.getNewsList());
        }else{
            Sort sort = new Sort(Sort.Direction.DESC, "newsTime");
            Pageable pageable = new PageRequest(0, limit, sort);
            ajaxResults.put("news", newsService.getNewsByPageable(pageable).getContent());
        }

        return ajaxResults;
    }

    @RequestMapping(value = "/web/index/introduction", method = RequestMethod.GET)
    public Object webIndexIntroduction(){
        AjaxResults ajaxResults = new AjaxResults();
        WebInfo webInfo = webInfoService.getWebInfo();
        ajaxResults.put("title", webInfo.getTitle());
        ajaxResults.put("introduction", webInfo.getIntroduction());
        return ajaxResults;
    }

    @RequestMapping(value = "/web/index/product", method = RequestMethod.GET)
    public Object webIndexProduct(Integer limit, Byte homeFlag, Byte recommendFlag){
        AjaxResults ajaxResults = new AjaxResults();
        if(null == homeFlag && null == recommendFlag){
            ajaxResults.put("products", productService.getProductList());
        }else{
            ajaxResults.put("products", productService.getIndexProducts(homeFlag, recommendFlag));
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/index/contactus", method = RequestMethod.GET)
    public Object webIndexContactus(){
        AjaxResults ajaxResults = new AjaxResults();
        WebInfo webInfo = webInfoService.getWebInfo();
        ajaxResults.put("address", webInfo.getAddress());
        ajaxResults.put("map", webInfo.getMap());
        ajaxResults.put("phone", webInfo.getPhone());
        ajaxResults.put("time", webInfo.getOpenTime());
        return ajaxResults;
    }
}

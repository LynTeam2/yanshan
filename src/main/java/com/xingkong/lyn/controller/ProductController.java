package com.xingkong.lyn.controller;

import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.web.Product;
import com.xingkong.lyn.service.IProduct;
import org.slf4j.Logger;
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
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Resource
    private IProduct productService;

    @RequestMapping("/web/product/list")
    public Object webProductList(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                             Pageable pageable, Long catagoryId){
        AjaxResults ajaxResults = new AjaxResults();
        Page<Product> products = productService.getProductListByPageable(catagoryId, pageable);
        ajaxResults.put("products", products);
        return ajaxResults;
    }

    @RequestMapping("/web/product/detail")
    public Object webProductDetail(Long id){
        AjaxResults ajaxResults = new AjaxResults();
        Product product = productService.getDetail(id);
        ajaxResults.put("product", product.getHtml());
        return ajaxResults;
    }
}

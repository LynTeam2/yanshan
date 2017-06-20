package com.xingkong.lyn.controller;

import com.xingkong.lyn.annotation.AdminLog;
import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.web.Catagory;
import com.xingkong.lyn.model.web.Product;
import com.xingkong.lyn.service.ICatagory;
import com.xingkong.lyn.service.IProduct;
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
import java.util.List;

/**
 * Created by lyn on 2017/6/14.
 */
@RestController
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Resource
    private IProduct productService;
    @Resource
    private ICatagory catagoryService;

    @RequestMapping(value = "/web/product/list", method = RequestMethod.GET)
    public Object webProductList(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                             Pageable pageable, Long catagoryId){
        AjaxResults ajaxResults = new AjaxResults();
        Page<Product> products = productService.getProductListByPageable(catagoryId, pageable);
        ajaxResults.put("products", products.getContent());
        return ajaxResults;
    }

    @RequestMapping(value = "/web/product/detail", method = RequestMethod.GET)
    public Object webProductDetail(Long id){
        AjaxResults ajaxResults = new AjaxResults();
        Product product = productService.getDetail(id);
        ajaxResults.put("product", product.getHtml());
        return ajaxResults;
    }

    @RequestMapping(value = "/web/product/catagory", method = RequestMethod.GET)
    public Object webProductCatagory(Long parentId){
        AjaxResults ajaxResults = new AjaxResults();
        List<Catagory> catagories = catagoryService.getSubCatagory(parentId);
        ajaxResults.put("catagories", catagories);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/catagory/list", method = RequestMethod.GET)
//    @RequiresPermissions("catagory:view")
    public Object webManageCatagoryList(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                                 Pageable pageable){
        AjaxResults ajaxResults = new AjaxResults();
        Page<Catagory> catagories = catagoryService.getCatagoryTree(pageable);
        ajaxResults.put("catagories", catagories);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/catagory/add", method = RequestMethod.POST)
//    @RequiresPermissions("catagory:add")
    @AdminLog(value = "新增类别")
    public Object webManageCatagoryAdd(Catagory catagory){
        AjaxResults ajaxResults = new AjaxResults();
        catagoryService.addCatagory(catagory);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/catagory/update", method = RequestMethod.PUT)
//    @RequiresPermissions("catagory:update")
    @AdminLog(value = "修改类别")
    public Object webManageCatagoryUpdate(Catagory catagory){
        AjaxResults ajaxResults = new AjaxResults();
        catagoryService.updateCatagory(catagory);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/catagory/delete", method = RequestMethod.DELETE)
//    @RequiresPermissions("catagory:delete")
    @AdminLog(value = "删除类别")
    public Object webManageCatagoryDelete(Long id){
        AjaxResults ajaxResults = new AjaxResults();
        catagoryService.deleteCatagory(id);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/product/list", method = RequestMethod.GET)
//    @RequiresPermissions("product:view")
    public Object webManageProductList(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                                       Pageable pageable, Long catagoryId){
        AjaxResults ajaxResults = new AjaxResults();
        Page<Product> products = productService.getProductListByPageable(catagoryId, pageable);
        ajaxResults.put("products", products);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/product/detail", method = RequestMethod.GET)
//    @RequiresPermissions("product:detail")
    public Object webManageProductDetail(Long id){
        AjaxResults ajaxResults = new AjaxResults();
        Product product = productService.getDetail(id);
        ajaxResults.put("product", product);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/product/add", method = RequestMethod.POST)
//    @RequiresPermissions("product:add")
    @AdminLog(value = "网站:新增产品(服务)")
    public Object webManageProductAdd(Product product){
        AjaxResults ajaxResults = new AjaxResults();
        productService.addProduct(product);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/product/update", method = RequestMethod.PUT)
//    @RequiresPermissions("product:update")
    @AdminLog(value = "网站:更新产品(服务)")
    public Object webManageProductUpdate(Product product){
        AjaxResults ajaxResults = new AjaxResults();
        productService.updateProduct(product);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/product/delete", method = RequestMethod.DELETE)
//    @RequiresPermissions("product:delete")
    @AdminLog(value = "网站:删除产品(服务)")
    public Object webManageProductDelete(Long id){
        AjaxResults ajaxResults = new AjaxResults();
        productService.deleteProduct(id);
        return ajaxResults;
    }
}
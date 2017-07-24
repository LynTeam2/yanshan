package com.xingkong.lyn.controller;

import com.xingkong.lyn.annotation.AdminLog;
import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.web.Catagory;
import com.xingkong.lyn.model.web.Image;
import com.xingkong.lyn.model.web.Product;
import com.xingkong.lyn.service.ICatagory;
import com.xingkong.lyn.service.IProduct;
import com.xingkong.lyn.util.OtherUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.OneToMany;
import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 2017/6/14.
 */
@RestController
@CrossOrigin
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Value(value = "${file.upload.path}")
    private String filePath;
    @Resource
    private IProduct productService;
    @Resource
    private ICatagory catagoryService;

    @RequestMapping(value = "/web/product/list", method = RequestMethod.GET)
    public Object webProductList(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                             Pageable pageable, Long catagoryId){
        AjaxResults ajaxResults = new AjaxResults();
        if(null != catagoryId){
            Catagory catagory = catagoryService.getCatagory(catagoryId);
            ajaxResults.put("products", catagory.getProducts());
        }else{
            Page<Product> products = productService.getProductListByPageable(catagoryId, pageable);
            ajaxResults.put("products", products.getContent());
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/product/detail", method = RequestMethod.GET)
    public Object webProductDetail(Long id){
        AjaxResults ajaxResults = new AjaxResults();
        if(null == id){
            ajaxResults.setCode(0);
            return ajaxResults;
        }
        Product product = productService.getDetail(id);
        ajaxResults.put("product", product);
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
    @RequiresPermissions("catagory:view")
    public Object webManageCatagoryList(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                                 Pageable pageable){
        AjaxResults ajaxResults = new AjaxResults();
        Page<Catagory> catagories = catagoryService.getCatagoryTree(pageable);
        ajaxResults.put("catagories", catagories);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/catagory/add", method = RequestMethod.POST)
    @RequiresPermissions("catagory:add")
    @AdminLog(value = "新增类别")
    public Object webManageCatagoryAdd(@RequestBody Catagory catagory){
        AjaxResults ajaxResults = new AjaxResults();
        if(null != catagory.getId()){
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        }else{
            catagory.setCreateTime(new Date());
            catagoryService.addCatagory(catagory);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/catagory/update", method = RequestMethod.PUT)
    @RequiresPermissions("catagory:update")
    @AdminLog(value = "修改类别")
    public Object webManageCatagoryUpdate(@RequestBody Catagory catagory){
        AjaxResults ajaxResults = new AjaxResults();
        if(null == catagory.getId()){
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        }else{
            catagoryService.updateCatagory(catagory);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/catagory/delete", method = RequestMethod.DELETE)
    @RequiresPermissions("catagory:delete")
    @AdminLog(value = "删除类别")
    public Object webManageCatagoryDelete(String id){
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        catagoryService.deleteCatagory(ids);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/product/list", method = RequestMethod.GET)
    @RequiresPermissions("product:view")
    @AdminLog(value = "查看产品列表")
    public Object webManageProductList(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                                       Pageable pageable, Long catagoryId){
        AjaxResults ajaxResults = new AjaxResults();
        Page<Product> products = productService.getProductListByPageable(catagoryId, pageable);
        ajaxResults.put("products", products);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/product/detail", method = RequestMethod.GET)
    @RequiresPermissions("product:detail")
    public Object webManageProductDetail(Long id){
        AjaxResults ajaxResults = new AjaxResults();
        Product product = productService.getDetail(id);
        ajaxResults.put("product", product);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/product/add", method = RequestMethod.POST)
    @RequiresPermissions("product:add")
    @AdminLog(value = "网站:新增产品(服务)")
    public Object webManageProductAdd(@RequestBody Product product){
        AjaxResults ajaxResults = new AjaxResults();
        if(null != product.getId()){
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        }else {
            product.setCreateTime(new Date());
            productService.addProduct(product);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/product/update", method = RequestMethod.PUT)
    @RequiresPermissions("product:update")
    @AdminLog(value = "网站:更新产品(服务)")
    public Object webManageProductUpdate(@RequestBody Product product){
        AjaxResults ajaxResults = new AjaxResults();
        productService.updateProduct(product);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/product/delete", method = RequestMethod.DELETE)
    @RequiresPermissions("product:delete")
    @AdminLog(value = "网站:删除产品(服务)")
    public Object webManageProductDelete(String id){
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        productService.deleteProduct(ids);
        return ajaxResults;
    }

//    @RequestMapping(value = "/web/manage/product/image/upload", method = RequestMethod.POST)
//    public Object webManageProductImageUpload(@RequestParam("file")MultipartFile file){
//        AjaxResults ajaxResults = new AjaxResults();
//        if(!file.isEmpty()){
//            try{
//                BufferedOutputStream out = new BufferedOutputStream(
//                        new FileOutputStream(new File(filePath+File.separator+file.getOriginalFilename())));
//                out.write(file.getBytes());
//                out.flush();
//                out.close();
//                Image image = new Image();
//                image.setAddress(file.getOriginalFilename());
//                image.setCreateTime(new Date());
//                image.setImgName(file.getOriginalFilename());
//                image = productService.addImage(image);
//                ajaxResults.put("id", image.getId());
//                ajaxResults.put("name", image.getImgName());
//            }catch (FileNotFoundException e){
//                logger.error("上传图片失败", e);
//                ajaxResults.setCode(0);
//                ajaxResults.setMsg("上传图片失败:图片未能找到");
//                return ajaxResults;
//            }catch (IOException e){
//                logger.error("上传图片失败", e);
//                ajaxResults.setCode(0);
//                ajaxResults.setMsg("上传图片失败:图片传输异常");
//                return ajaxResults;
//            }
//            return ajaxResults;
//        }else{
//            ajaxResults.setCode(0);
//            ajaxResults.setMsg("上传图片失败:图片为空");
//            return ajaxResults;
//        }
//    }
}
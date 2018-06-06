package com.xingkong.lyn.model.web;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
@Entity
@Data
@Table(name = "web_category")
public class Category implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Long parentId;

    @Column(name = "name")
    private String categoryName;

    private String content;

    private String icon;

    private String iconName;

    private Date createTime;

    @ManyToMany(fetch=FetchType.LAZY)//级联保存、更新、删除、刷新;延迟加载
    @JoinTable(name = "web_category_product",joinColumns = {@JoinColumn(name = "category_id")},inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
//    @MapKey(name = "id")
    @JoinColumn(name = "parentId")
    private List<Category> subCategories;
}


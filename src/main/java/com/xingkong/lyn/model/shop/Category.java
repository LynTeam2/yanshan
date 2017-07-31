package com.xingkong.lyn.model.shop;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 2017/6/2.
 */
@Entity
@Data
@Table(name = "shop_catagory")
public class Category implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String icon;

    private String content;

    private Long parentId;

    private Date createTime;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "shop_category_product", joinColumns = {@JoinColumn(name = "category_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private List<Category> subCategories;
}

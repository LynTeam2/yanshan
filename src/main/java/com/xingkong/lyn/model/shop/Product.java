package com.xingkong.lyn.model.shop;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 2017/6/2.
 */
@Entity
@Data
@Table(name = "shop_product")
public class Product {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long businessId;

    private Date createTime;

    private String picture;

    private int sales;

    private int comment;

    private Byte status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "shop_category_product", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private List<Category> categories;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "shop_product_html", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "html_id")})
    private List<Html> htmls;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "shop_product_image", joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private List<Html> images;
}

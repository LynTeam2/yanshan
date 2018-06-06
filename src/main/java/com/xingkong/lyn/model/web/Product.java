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
@Table(name = "web_product")
public class Product implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name")
    private String proName;

    private String title;

    private Date createTime;

    private Byte homeFlag;

    private Byte recommendFlag;

    private String sequence;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "web_category_product",joinColumns = {@JoinColumn(name = "product_id")},inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private List<Category> categories;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "web_product_image",joinColumns = {@JoinColumn(name = "product_id")},inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private List<Image> images;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "web_product_html",joinColumns = {@JoinColumn(name = "product_id")},inverseJoinColumns = {@JoinColumn(name = "html_id")})
    private List<Html> htmls;
}


package com.xingkong.lyn.model.web;

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

    private String name;

    @Column(insertable = false, updatable = false)
    private Long catagoryId;

    private Date createTime;

    private String imageUrl;

    private String html;

    private String html2;

    private Byte home;

    @ManyToOne
    @JoinColumn(name = "catagoryId")
    private Catagory catagory;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "web_product_banner",joinColumns = {@JoinColumn(name = "product_id")},inverseJoinColumns = {@JoinColumn(name = "banner_id")})
    private List<Banner> banners;
}


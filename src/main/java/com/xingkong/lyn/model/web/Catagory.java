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
@Table(name = "web_catagory")
public class Catagory implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Long parentId;

    @Column(name = "name")
    private String catagoryName;

    private String content;

    private String icon;

    private Date createTime;

    @OneToMany(fetch=FetchType.LAZY)//级联保存、更新、删除、刷新;延迟加载
    @JoinColumn(name = "catagoryId")
    private List<Product> products;

    @OneToMany(fetch=FetchType.EAGER)
    @MapKey(name = "pk")
    @JoinColumn(name = "parentId")
    private List<Catagory> subCatagories;
}


package com.xingkong.lyn.model.web;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    private Long catagoryId;

    private Date createTime;

    private String picture;

    private String html;

    private Byte home;

//    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//    private Catagory catagory;
}

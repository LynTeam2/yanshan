package com.xingkong.lyn.model.shop;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

    private Long catagoryId;

    private Long businessId;

    private Date createTime;

    private String picture;

    private int sales;

    private int comment;

    private byte status;
}

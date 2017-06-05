package com.xingkong.lyn.model.shop;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lyn on 2017/6/5.
 */
@Entity
@Data
@Table(name = "shop_address")
public class Address implements Serializable{
    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String consignee;

    private String region;

    private String address;

    private String zipcode;

    private String telephone;

    private boolean main;

    private Date createTime;
}

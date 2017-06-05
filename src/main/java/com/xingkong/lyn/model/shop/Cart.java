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
@Table(name = "shop_cart")
public class Cart implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Long skuId;

    private Long userId;

    private Date createTime;
}

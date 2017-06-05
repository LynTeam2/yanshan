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
@Table(name = "shop_order")
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String orderNumber;

    private Byte status;

    private double amount;

    private Byte shippingStatus;

    private String shippingName;

    private double shippingFee;

    private String remark;

    private Long payId;

    private String payName;

    private Date createTime;
}

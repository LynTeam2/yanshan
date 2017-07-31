package com.xingkong.lyn.model.shop;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by lyn on 2017/7/31.
 */
@Entity
@Data
@Table(name = "shop_image")
public class Image implements Serializable{
    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String imgName;

    private String address;

    private String link;

    private String position;
}

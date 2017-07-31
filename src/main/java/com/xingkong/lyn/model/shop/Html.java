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
@Table(name = "shop_html")
public class Html implements Serializable{
    private static final long SerialVersionUID = 1l;

    @Id
    @GeneratedValue
    private Long id;

    private String html;

    private String position;
}

package com.xingkong.lyn.model.web;

import lombok.Data;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by lyn on 2017/6/14.
 */
@Entity
@Data
@Table(name = "web_info")
public class WebInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String name;

    private String introduction;

    private String address;

    private String phone;

    private String openTime;

    private String map;

    private String icp;
}

package com.xingkong.lyn.model.web;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by lyn on 2017/7/6.
 */
@MappedSuperclass
@Data
public abstract class BaseImage {
    @Id
    @GeneratedValue
    private Long id;

    private String link;

    private String position;

    private String address;

    private String name;

    private Date createTime;
}

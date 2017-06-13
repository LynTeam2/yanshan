package com.xingkong.lyn.model.web;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lyn on 2017/6/13.
 */
@Entity
@Data
@Table(name = "web_banner")
public class Banner implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String url;

    private String position;

    private String banner;

    private Date createTime;
}

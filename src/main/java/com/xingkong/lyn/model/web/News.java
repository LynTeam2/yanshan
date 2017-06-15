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
@Table(name = "web_news")
public class News implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    private String html;

//    @Temporal(TemporalType.DATE)
//    @Column(name = "news_time")
//    private Date newsTime;

    private Date createTime;
}

package com.xingkong.lyn.model.web;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @Temporal(TemporalType.DATE)
    @Column(name = "news_time")
    private Date newsTime;

    private Date createTime;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "web_news_html", joinColumns = {@JoinColumn(name = "news_id")},inverseJoinColumns = {@JoinColumn(name = "html_id")})
    private List<Html> htmls;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "web_news_image", joinColumns = {@JoinColumn(name = "news_id")},inverseJoinColumns = {@JoinColumn(name = "html_id")})
    private List<Image> images;
}

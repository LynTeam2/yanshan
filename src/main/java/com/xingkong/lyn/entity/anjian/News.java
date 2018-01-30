package com.xingkong.lyn.entity.anjian;

import com.alibaba.fastjson.annotation.JSONField;
import com.xingkong.lyn.model.web.Html;
import com.xingkong.lyn.model.web.Image;
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
@Table(name = "aj_news")
public class News implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    @Temporal(TemporalType.DATE)
    @Column(name = "news_time")
    @JSONField(format = "yyyy-MM-dd")
    private Date newsTime;

    private Date createTime;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "aj_news_html", joinColumns = {@JoinColumn(name = "news_id")},inverseJoinColumns = {@JoinColumn(name = "html_id")})
    private List<Html> htmls;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "aj_news_image", joinColumns = {@JoinColumn(name = "news_id")},inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private List<Image> images;
}

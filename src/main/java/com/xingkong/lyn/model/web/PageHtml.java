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
@Table(name = "web_page")
public class PageHtml implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String page;

    private Date createTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "web_page_html", joinColumns = {@JoinColumn(name = "page_id")},inverseJoinColumns = {@JoinColumn(name = "html_id")})
    private List<Html> htmls;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "web_page_image", joinColumns = {@JoinColumn(name = "page_id")},inverseJoinColumns = {@JoinColumn(name = "html_id")})
    private List<Image> images;

}

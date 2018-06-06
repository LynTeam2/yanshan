package com.xingkong.lyn.model.web;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lyn on 2017/8/30.
 */
@Data
@Entity
@Table(name = "web_video")
public class Video implements Serializable{
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String videoName;

    private String videoUrl;

    private String videoPreview;

    private Byte homeFlag = 0;// 0默认 1展示在首页

    private Date createTime;
}

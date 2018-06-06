package com.xingkong.lyn.model.web;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
@Data
@Entity
@Table(name = "web_course")
public class Course implements Serializable{
    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String courseName;

    private Byte courseStatus;

    private Byte courseType;

    private Date createTime;

    private String introduction;

    private String address;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy年MM月dd日")
    private Date startTime;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy年MM月dd日")
    private Date endTime;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "web_course_teacher",joinColumns = {@JoinColumn(name = "course_id")},inverseJoinColumns = {@JoinColumn(name = "teacher_id")})
    private List<Teacher> teachers;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "web_course_reservation",joinColumns = {@JoinColumn(name = "course_id")},inverseJoinColumns = {@JoinColumn(name = "reservation_id")})
    private List<Reservation> reservations;

    @JoinTable(name = "web_course_image", joinColumns = {@JoinColumn(name = "course_id")}, inverseJoinColumns = {@JoinColumn(name = "image_id")})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "web_course_html",joinColumns = {@JoinColumn(name = "course_id")},inverseJoinColumns = {@JoinColumn(name = "html_id")})
    private List<Html> htmls;
}

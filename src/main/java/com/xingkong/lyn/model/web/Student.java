package com.xingkong.lyn.model.web;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
@Entity
@Data
@Table(name = "web_student")
public class Student implements Serializable{
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String studentName;

    private String phone;

    private String introduction;

    private String age;

    private String address;

    private Date createTime;

    @JoinTable(name = "web_student_image", joinColumns = {@JoinColumn(name = "student_id")}, inverseJoinColumns = {@JoinColumn(name = "image_id")})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "web_student_html",joinColumns = {@JoinColumn(name = "student_id")},inverseJoinColumns = {@JoinColumn(name = "html_id")})
    private List<Html> htmls;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "web_student_course",joinColumns = {@JoinColumn(name = "student_id")},inverseJoinColumns = {@JoinColumn(name = "course_id")})
//    private List<Course> courses;
}

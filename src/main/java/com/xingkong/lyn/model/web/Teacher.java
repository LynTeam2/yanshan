package com.xingkong.lyn.model.web;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
@Data
@Entity
@Table(name = "web_teacher")
public class Teacher implements Serializable{
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String teacherName;

    private Byte teacherType;

    private String introduction;

    private String label;

    @JoinTable(name = "web_teacher_image", joinColumns = {@JoinColumn(name = "teacher_id")}, inverseJoinColumns = {@JoinColumn(name = "image_id")})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "web_teacher_html",joinColumns = {@JoinColumn(name = "teacher_id")},inverseJoinColumns = {@JoinColumn(name = "html_id")})
    private List<Html> htmls;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "web_course_teacher",joinColumns = {@JoinColumn(name = "teacher_id")},inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Course> courses;
}

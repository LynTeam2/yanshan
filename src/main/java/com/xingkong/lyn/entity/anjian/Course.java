package com.xingkong.lyn.entity.anjian;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "aj_course")
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String courseName;

    private String ajType;

    private byte courseType;

    private String content;

    private String icon;

    private String iconName;

    private String introduction;

    private boolean homePage;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aj_course_tf", joinColumns = {@JoinColumn(name = "course_id")}, inverseJoinColumns = {@JoinColumn(name = "question_id")})
    List<TrueFalse> tfList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aj_course_bf", joinColumns = {@JoinColumn(name = "course_id")}, inverseJoinColumns = {@JoinColumn(name = "question_id")})
    List<BlankFilling> bfList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aj_course_sc", joinColumns = {@JoinColumn(name = "course_id")}, inverseJoinColumns = {@JoinColumn(name = "question_id")})
    List<SimpleChoice> scList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aj_course_mc", joinColumns = {@JoinColumn(name = "course_id")}, inverseJoinColumns = {@JoinColumn(name = "question_id")})
    List<MultipleChoice> mcList;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy-MM-dd")
    private Date updateTime;
}

package com.xingkong.lyn.model.web;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lyn on 2017/8/30.
 */
@Entity
@Data
@Table(name = "web_reservation")
public class Reservation implements Serializable{
    private static final long SerialVersionUID = 1l;

    @Id
    @GeneratedValue
    private Long id;

    private String personName;

    private String personPhone;

    private String personAddress;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy年MM月dd日")
    private Date reserveTime;

    private Date createTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "web_course_reservation",joinColumns = {@JoinColumn(name = "reservation_id")},inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private Course course;
}

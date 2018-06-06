package com.xingkong.lyn.model.web;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lyn on 2017/7/24.
 */
@Entity
@Data
@Table(name = "web_staff")
public class Staff implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String staffName;

    private String job;

    private String introduction;

    private String photo;

    private String photoName;

    private String phone;

    private Date createTime;
}

package com.xingkong.lyn.model.web;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Date;

/**
 * Created by Bug Man on 2017-07-05.
 */
@Entity
@Data
@Table(name = "web_message")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String peopleName;
    private String companyName;
    private String tel;
    private String content;
    private Date createTime;
}
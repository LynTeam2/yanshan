package com.xingkong.lyn.model.web;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by lyn on 2017/7/8.
 */
@Entity
@Data
@Table(name = "web_html")
public class Html implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String html = "";

    private String position;
}

package com.xingkong.lyn.model.web;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by lyn on 2017/7/6.
 */
@Entity
@Data
@Table(name = "web_image")
@EqualsAndHashCode(callSuper = false)
public class Image extends BaseImage implements Serializable {
    private static final long serialVersionUID = 1L;
}
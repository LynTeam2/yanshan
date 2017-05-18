package com.xingkong.lyn.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysPermissionInit {
    private Long id;

    private String url;

    private String permissionInit;

    private Integer sort;

    private Date createtime;
}
package com.xingkong.lyn.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 2017/5/26.
 * 地址实体类
 */
@Entity
@Data
public class Address implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;

    private String provinceCode;

    private String cityCode;

    private String countyCode;

    private String address;

    private Byte main;

    private Date createTime;

    @ManyToMany(fetch= FetchType.LAZY)//实际调用查询才进行加载数据;
    @JoinTable(name = "SysUserAddress", joinColumns = { @JoinColumn(name = "addressId") }, inverseJoinColumns ={@JoinColumn(name = "userId") })
    private List<UserInfo> userInfos;// 一个地址有多个用户
}

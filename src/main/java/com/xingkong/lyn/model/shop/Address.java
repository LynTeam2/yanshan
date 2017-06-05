package com.xingkong.lyn.model.shop;

import com.xingkong.lyn.model.UserInfo;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 2017/6/5.
 */
@Entity
@Data
@Table(name = "shop_address")
public class Address implements Serializable{
    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String consignee;

    private String region;

    private String address;

    private String zipcode;

    private String telephone;

    private boolean main;

    private Date createTime;

    @ManyToMany(fetch= FetchType.LAZY)//实际调用查询才进行加载数据;
    @JoinTable(name = "SysUserAddress", joinColumns = { @JoinColumn(name = "addressId") }, inverseJoinColumns ={@JoinColumn(name = "userId") })
    private List<UserInfo> userInfos;// 一个地址有多个用户
}

package com.xingkong.lyn.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    private String provinceCode;

    private String cityCode;

    private String countyCode;

    private Byte main;

    private Date createTime;

    private String address;
}
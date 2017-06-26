package com.xingkong.lyn.model.manage;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lyn on 2017/6/20.
 */
@Entity
@Data
@Table(name = "sys_log")
public class SysLog {
    @Id
    @GeneratedValue
    private Long id;
    private Long operatorId;
    private String operator;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
}

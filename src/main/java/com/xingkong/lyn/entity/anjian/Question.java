package com.xingkong.lyn.entity.anjian;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class Question {
    @Id
    @GeneratedValue
    private Long id;

    private String question;

    private String difficulty;

    private String ajType;

    private String analysis;

    private String answer;

    @Temporal(TemporalType.TIMESTAMP)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}

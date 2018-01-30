package com.xingkong.lyn.entity.anjian;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "aj_course")
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String courseName;

    private String ajType;

    private byte courseType;

    private String content;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy-MM-dd")
    private Date updateTime;
}

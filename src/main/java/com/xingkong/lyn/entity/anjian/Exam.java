package com.xingkong.lyn.entity.anjian;

import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "aj_exam")
public class Exam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String examName;

    private String examType;

    @Transient
    private int standard;

    private String introduction;

    private int examDuration;

    private String role;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;

    @Transient
    List<TrueFalse> tfList;

    @Transient
    List<BlankFilling> bfList;

    @Transient
    List<SimpleChoice> scList;

    @Transient
    List<MultipleChoice> mcList;

    @Transient
    int examTfCount = 0;

    @Transient
    int examScCount = 0;

    @Transient
    int examMcCount = 0;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aj_exam_course", joinColumns = {@JoinColumn(name = "exam_id")}, inverseJoinColumns = {@JoinColumn(name = "course_id")})
    List<Course> courseList;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy-MM-dd")
    private Date updateTime;
}

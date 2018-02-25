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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aj_exam_tf", joinColumns = {@JoinColumn(name = "exam_id")}, inverseJoinColumns = {@JoinColumn(name = "question_id")})
    List<TrueFalse> tfList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aj_exam_bf", joinColumns = {@JoinColumn(name = "exam_id")}, inverseJoinColumns = {@JoinColumn(name = "question_id")})
    List<BlankFilling> bfList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aj_exam_sc", joinColumns = {@JoinColumn(name = "exam_id")}, inverseJoinColumns = {@JoinColumn(name = "question_id")})
    List<SimpleChoice> scList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aj_exam_mc", joinColumns = {@JoinColumn(name = "exam_id")}, inverseJoinColumns = {@JoinColumn(name = "question_id")})
    List<MultipleChoice> mcList;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy-MM-dd")
    private Date updateTime;
}

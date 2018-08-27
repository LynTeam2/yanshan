package com.xingkong.lyn.entity.anjian;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 2018/8/26.
 */
@Data
@Table(name = "aj_exam_history")
@Entity
public class ExamHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long unitId;

    private Long examId;

    private int makeupFlag;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private int examScore;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aj_exam_history_detail", joinColumns = {@JoinColumn(name = "history_id")}, inverseJoinColumns = {@JoinColumn(name = "detail_id")})
    private List<ExamDetail> examDetailList;
}

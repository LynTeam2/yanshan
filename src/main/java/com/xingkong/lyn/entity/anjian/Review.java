package com.xingkong.lyn.entity.anjian;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lyn on 2018/8/27.
 */
@Data
@Entity
@Table(name = "aj_review")
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String module;

    private String operate;

    @Column(columnDefinition = "TEXT")
    private String reviewContent;

    private Long operatorId;

    private String operatorName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    private Long reviewerId;

    private String reviewerName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date reviewTime;

    private int reviewResult = 0; // 0待审核 1审核通过 2审核不通过
}

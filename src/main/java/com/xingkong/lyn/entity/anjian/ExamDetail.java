package com.xingkong.lyn.entity.anjian;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lyn on 2018/8/26.
 */
@Data
@Table(name = "aj_exam_detail")
@Entity
public class ExamDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String uid;

    private Long questionId;

    private String questionType;

    private String ajType;

    private String answer;

    private int result;

    private Date createTime;
}

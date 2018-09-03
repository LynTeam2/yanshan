package com.xingkong.lyn.model.anjian;

import com.alibaba.fastjson.annotation.JSONField;
import com.xingkong.lyn.entity.anjian.ExamDetail;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 2018/8/31.
 */
@Data
public class ExamHistoryVo implements Serializable{
    private static final long serialVersionId = 1L;

    private Long examId;

    private Long userId;

    private Long unitId;

    private String examName;

    private int makeupFlag;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private int examScore;

    private List<ExamDetail> examDetailList;
}

package com.xingkong.lyn.entity.anjian;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lyn on 2018/5/3.
 */
@Data
@Entity
@Table(name = "aj_law")
public class Law {
    @GeneratedValue
    @Id
    private Long id;
    private String lawType;
    private String lawName;
    private String iconName;
    private String iconPath;
    private String fileName;
    private String filePath;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy-MM-dd")
    private Date updateTime;
}

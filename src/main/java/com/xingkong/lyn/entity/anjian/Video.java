package com.xingkong.lyn.entity.anjian;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "aj_video")
public class Video implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String videoName;

    private String videoUrl;

    private String videoPreview;

    private Date createTime;
}

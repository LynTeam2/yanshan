package com.xingkong.lyn.entity.anjian;

import com.xingkong.lyn.model.web.BaseImage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by lyn on 2017/6/13.
 */
@Entity
@Data
@Table(name = "aj_banner")
@EqualsAndHashCode(callSuper = false)
public class Banner extends BaseImage implements Serializable {
    private static final long serialVersionUID = 1L;
}

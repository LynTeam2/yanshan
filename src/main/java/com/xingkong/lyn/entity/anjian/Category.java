package com.xingkong.lyn.entity.anjian;

import com.xingkong.lyn.model.web.Product;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
@Entity
@Data
@Table(name = "aj_category")
public class Category implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Long parentId;

    private String categoryName;

    private String introduction;

    private String icon;

    private String iconName;

    private String jsonName;

    private Date createTime;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
//    @MapKey(name = "id")
    @JoinColumn(name = "parentId")
    private List<Category> subCategories;
}


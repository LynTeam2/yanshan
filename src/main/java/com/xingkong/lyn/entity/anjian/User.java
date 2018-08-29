package com.xingkong.lyn.entity.anjian;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "aj_user")
public class User implements Serializable {
    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private String realName;

    private String icon;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "aj_unit_user", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "unit_id")})
    private Unit unit;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "aj_user_course", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<CourseProcess> courseProcessList;

    private int beanCount;

    private String nickname;

    private String telephone;

    @Transient
    private String role;

    @Transient
    private String roleName;

    @Transient
    private String unitId;

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", realName=" + realName + ", nickName=" + nickname
                + ", telephone=" + telephone + ", beanCount=" + beanCount + "]";
    }
}

package com.xingkong.lyn.entity.anjian;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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

    private String password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "aj_unit_user", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "unit_id")})
    private Unit unit;
}

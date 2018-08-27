package com.xingkong.lyn.entity.anjian;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "aj_unit")
public class Unit implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue
    @Id
    private Long id;

    private String unitName;

    private String headerName;

    private String telephone;

    private String email;

    private String fax;

    private String address;

    private String provinceCode;

    private String cityCode;

    private String countyCode;

    private String province;

    private String city;

    private String county;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "aj_unit_user", joinColumns = {@JoinColumn(name = "unit_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> userList;

    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;
}

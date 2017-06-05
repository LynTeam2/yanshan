package com.xingkong.lyn.model;//package com.xingkong.lyn.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by lyn on 2017/5/17.
 * 用户信息
 */
@Entity
@Data
public class UserInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id@GeneratedValue
    private Long id;//用户id

    @Column(unique = true)
    private String username;//账号

    private String name;//名称

    private String password;//密码

    private String salt;//密码加密的盐

    private byte status;//用户状态，0：创建未认证 1：正常状态 2：用户被锁定

    @ManyToMany(fetch=FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SysRole> roles;// 一个用户具有多个角色

    @ManyToMany(fetch=FetchType.LAZY)//实际调用查询才进行加载数据;
    @JoinTable(name = "SysUserAddress", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns ={@JoinColumn(name = "addressId") })
    private List<Address> addresses;// 一个用户具有多个地址

    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }

    @Override
    public String toString() {
        return "UserInfo [uid=" + id + ", username=" + username + ", name=" + name + ", password=" + password
                + ", salt=" + salt + ", state=" + status + "]";
    }
}

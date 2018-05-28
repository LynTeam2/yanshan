package com.xingkong.lyn.model.manage;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lyn on 2017/7/14.
 */
@Data
public class LoginVo implements Serializable{
    private static final long serialVersionId = 1L;
    private String username;
    private String password;
    private String vcode;
    private Boolean rememberMe = false;
}

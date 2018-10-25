package com.xingkong.lyn.model.anjian;

import lombok.Data;

/**
 * Created by lyn on 2018/9/25.
 */
@Data
public class PasswordVo {
    private String oldPassword;
    private String newPassword;
    private String surePassword;
}

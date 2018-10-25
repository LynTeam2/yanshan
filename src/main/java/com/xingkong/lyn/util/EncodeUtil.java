package com.xingkong.lyn.util;

import com.xingkong.lyn.model.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by lyn on 2018/8/2.
 */
public class EncodeUtil {
    private final static String algorithmName = "md5";
    public static UserInfo encode(UserInfo userInfo) {
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;

        SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
        String encodePassword = hash.toHex();
        userInfo.setPassword(encodePassword);
        userInfo.setSalt(salt2);
        return userInfo;
    }

    public static boolean equal(UserInfo userInfo, String uncodePassword) {
        String username = userInfo.getUsername();
        String encodePassword = userInfo.getPassword();
        String salt2 = userInfo.getCredentialsSalt();
        String hex = new SimpleHash("md5", uncodePassword, username + salt2, 2).toHex();
        return StringUtils.equals(hex, encodePassword);
    }

    public static void main (String[] args) {
        String algorithmName = "md5";
        String username = "admi";
        String password = "123456";
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;

        SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
        String encodedPassword = hash.toHex();
        System.out.println(encodedPassword);
    }
}

package com.xingkong.lyn.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by lyn on 2018/7/17.
 */
public class PwdUtil {
    private static final char[] lowerLetterArr = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] upperLetterArr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final int pwdLength = 8;

    public static void main(String[] args) {
        StringBuffer pwd = new StringBuffer("");
        int specialIndex = RandomUtils.nextInt(0, 32);
        for (int i = 0; i < pwdLength; i++) {
            if (specialIndex == i) {
                pwd.append("_");
            } else if (i > 4 && needNumber(pwd.toString())) {
                pwd.append(RandomStringUtils.randomNumeric(1));
            } else if (i > 4 && needLower(pwd.toString())) {
                pwd.append(StringUtils.lowerCase(RandomStringUtils.randomAlphabetic(1)));
            } else if (i > 4 && needUpper(pwd.toString())) {
                pwd.append(StringUtils.upperCase(RandomStringUtils.randomAlphabetic(1)));
            } else {
                pwd.append(RandomStringUtils.randomAlphanumeric(1));
            }
        }
        System.out.println(pwd.toString());
    }

    private static boolean needNumber(String string) {
        if (string.replaceAll("[a-z]*[A-Z]*[_]*", "").length() == 0) {
            return true;
        }
        return false;
    }

    private static boolean needLower(String string) {
        if (string.replaceAll("[A-Z]*[0-9]*[_]*", "").length() == 0) {
            return true;
        }
        return false;
    }

    private static boolean needUpper(String string) {
        if (string.replaceAll("[a-z]*[0-9]*[_]*", "").length() == 0) {
            return true;
        }
        return false;
    }
}

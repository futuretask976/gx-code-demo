package com.gx.code.utils.regex;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpUtils {
    /**
     *
     * @param regexExp
     * @param input
     * @return
     */
    public boolean match(String regexExp, String input) {
        Pattern pattern = Pattern.compile(regexExp);
        Matcher matcher = pattern.matcher(input);
        boolean isMatch = (matcher.find()) ? true : false;
        return isMatch;
    }

    public static boolean isNumber(String str) {
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (!isNumber(arr[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumber(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidCode(String input, boolean required) {
        if (StringUtils.isBlank(input)) {
            if (!required) {
                return true;
            }
        } else {
            String pattern = "^[A-Za-z0-9_]{0,50}$";
            boolean isMatch = Pattern.matches(pattern, input);
            if (isMatch) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidName(String input, boolean required) {
        if (StringUtils.isBlank(input)) {
            if (!required) {
                return true;
            }
        } else {
            String pattern = "^[A-Za-z0-9_\\u4e00-\\u9fa5]{0,50}$";
            boolean isMatch = Pattern.matches(pattern, input);
            if (isMatch) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidText(String input, boolean required) {
        if (StringUtils.isBlank(input)) {
            if (!required) {
                return true;
            }
        } else {
            String pattern = "^[A-Za-z0-9_" +
                    "\\u4e00-\\u9fa5" + // 中文字符
                    "\\u3002" + // 。
                    "\\uff1b" + // ；
                    "\\uff0c" + // ，
                    "\\uff1a" + // ：
                    "\\u201c" + // “
                    "\\u201d" + // ”
                    "\\uff08" + // （
                    "\\uff09" + // ）
                    "\\u3001" + // 、
                    "\\uff1f" + // ？
                    "\\u300a" + // 《
                    "\\u300b" + // 》
                    "\\uFF01" + // ！
                    "]{0,200}$";
            boolean isMatch = Pattern.matches(pattern, input);
            if (isMatch) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
//        System.out.println(isValidStr(null, true));
//        System.out.println(isValidStr("", true));
//        System.out.println(isValidStr(null, false));
//        System.out.println(isValidStr("", false));
//        System.out.println(isValidStr("aAbce01234", false));
//        System.out.println(isValidStr("aAbce_01234", false));
//        System.out.println(isValidStr("aAbce-01234", false));
        System.out.println(isValidText("ab我要你你熬她，。！", true));
    }
}

package com.gx.code.utils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpUtils {
    /**
     *
     * @param regexExp
     * @param targetText
     * @return
     */
    public boolean match(String regexExp, String targetText) {
        Pattern pattern = Pattern.compile(regexExp);
        Matcher matcher = pattern.matcher(targetText);
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
}

package com.gx.code.utils.office;

import com.google.common.collect.Lists;
import com.gx.code.utils.bio.FileIOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

public class WordUtils {
    public static void main(String args[]) throws Exception {
        System.out.println("笔笔返福利：本次支付后可返%元".replace("%", "5.4"));
    }

    public static String escapePathParam(String pathInfo) {
        if (StringUtils.isBlank(pathInfo)
                || pathInfo.indexOf("?") <= 0) {
            return pathInfo;
        }

        int queryParamMarkIdx = pathInfo.indexOf("?");
        String path = pathInfo.substring(0, queryParamMarkIdx);
        String queryParam = pathInfo.substring(queryParamMarkIdx + 1);

        if (StringUtils.isNotBlank(queryParam)) {
            StringBuilder sb = new StringBuilder();
            String[] params = queryParam.split("&");
            for (String param : params) {
                String[] pair = param.split("=");
                if (pair == null || pair.length < 2
                        || StringUtils.isBlank(pair[0])
                        || StringUtils.isBlank(pair[1])) {
                    continue;
                }
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(pair[0]).append("=").append(pair[1]);
            }
            if (sb.length() > 0) {
                return path + "?" + sb.toString();
            }
        }

        return path;
    }

    public static void formatByFile(File inputFile) {
        if (inputFile == null || !inputFile.exists()) {
            return;
        }

        List<String> outputList = Lists.newArrayList();
        List<String> inputList = FileIOUtils.readFileToList(inputFile);
        for (String line : inputList) {
            outputList.add(formatByString(line));
        }

        for(String str : outputList) {
            System.out.println(str);
        }
    }

    public static String formatByString(String inputStr) {
        if (StringUtils.isBlank(inputStr)) {
            return "";
        }

        inputStr = inputStr.replace(",", "，")
                //.replace(".", "。")
                //.replace(":", "：")
                .replace(";", "；")
                //.replace("?", "？")
                .replace("[", "【")
                .replace("]", "】")
                .replace("(", "（")
                .replace(")", "）")
                .replace("》", ">")
                .replace("《", "<");

        inputStr = replaceDot(inputStr);

        char[] charArr = inputStr.toCharArray();
        StringBuffer outputBuffer = new StringBuffer();
        char preChar = 0;
        for (int i = 0; i < charArr.length; i++) {
            char cntChar = charArr[i];
            if (Character.isSpaceChar(cntChar)) {
                continue;
            }

            boolean needInsertSpace = false;
            if (i != 0) {
                if (isChineseCharacter(cntChar) &&
                        (isEnglishChar(preChar) || isNumber(preChar) | isEnglishSymbol(preChar))) {
                    needInsertSpace = true;
                } else if (isEnglishChar(cntChar) &&
                        (isChineseCharacter(preChar))) {
                    needInsertSpace = true;
                } else if (isNumber(cntChar) &&
                        (isChineseCharacter(preChar))) {
                    needInsertSpace = true;
                } else if (isEnglishSymbol(cntChar) &&
                        (isChineseCharacter(preChar))) {
                    needInsertSpace = true;
                }
            }

            if (needInsertSpace) {
                outputBuffer.append(" ");
            }
            outputBuffer.append(cntChar);

            preChar = cntChar;
        }

        return outputBuffer.toString();
    }


    public static boolean isChineseCharacter(char c) {
        if (!isChineseSymbol(c) && !isEnglishChar(c) && !isEnglishSymbol(c) && !isNumber(c) && !isSyntacSplitor(c)) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean isChineseSymbol(char c) {
        if (c == '（' || c == '）'
                || c == '【' || c == '】') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEnglishChar(char c) {
        if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEnglishSymbol(char c) {
        if (c == '@' ||c == '|' || c == '=' || c == '&' || c == '+' || c == '%' || c == '_' || c == '-' || c == '/' || c == '\\'
                || c == '(' || c == ')'
                || c == '[' || c == ']'
                | c == '<' || c == '>'
                || c == '.'
                || c == '#') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNumber(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSyntacSplitor(char c) {
        if (c == '、' || c == '，' || c == '。' || c == '：' || c == '；' || c == '？') {
            return true;
        } else {
            return false;
        }
    }


    public static String replaceDot(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }

        int idx = str.indexOf(".");
        if (idx == -1) {
            return str;
        }
        if (idx == str.length() -1) {
            str = str.substring(0, idx) + "。";
        } else {
            String beforeChar = str.substring(idx - 1, idx);
            String afterChar = str.substring(idx + 1, idx + 2);
            if (!isEnglishChar(beforeChar.charAt(0))
                    && !isEnglishSymbol(afterChar.charAt(0))) {
                str = str.substring(0, idx) + "。" + replaceDot(str.substring(idx + 1));
            } else {
                str = str.substring(0, idx) + "." + replaceDot(str.substring(idx + 1));
            }
        }
        return str;
    }
}

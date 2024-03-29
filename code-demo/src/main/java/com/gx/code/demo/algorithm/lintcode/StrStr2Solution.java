package com.gx.code.demo.algorithm.lintcode;

/**
 * https://www.lintcode.com/problem/594/?utm_source=%5B%27sc-lczhihu-thx1217%27%5D
 */
public class StrStr2Solution {
    public static void main(String args[]) {
        String src = "abcdef";
        String target = "bcd";
        System.out.println(strStr2(src, target));
    }

    /**
     * @param source: A source string
     * @param target: A target string
     * @return: An integer as index
     */
    public static int strStr2(String source, String target) {
        // write your code here
        char[] srcArr = source.toCharArray();
        char[] targetArr = target.toCharArray();

        int srcLeftIdx = 0;
        while (srcLeftIdx < srcArr.length) {
            if (srcArr[srcLeftIdx] == targetArr[0]) {
                int tmpSrcArrIdx = srcLeftIdx + 1;
                int tmpTargetLeftIdx = 1;
                while (tmpSrcArrIdx < srcArr.length && tmpTargetLeftIdx < targetArr.length) {
                    if (srcArr[tmpSrcArrIdx] == targetArr[tmpTargetLeftIdx]) {
                        tmpSrcArrIdx++;
                        tmpTargetLeftIdx++;
                    }
                }
                if (tmpTargetLeftIdx == targetArr.length) {
                    return srcLeftIdx;
                }
            }
            srcLeftIdx++;
        }
        return -1;
    }
}

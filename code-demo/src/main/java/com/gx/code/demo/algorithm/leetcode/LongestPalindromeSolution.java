package com.gx.code.demo.algorithm.leetcode;

public class LongestPalindromeSolution {
    public static void main(String args[]) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome("ac"));
        System.out.println(longestPalindrome("ccc"));
    }

    public static String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }

        char[] sArr = s.toCharArray();
        int[] pSizeArr = new int[sArr.length];

        for (int i = 0; i < sArr.length; i++) {
            for (int j = i; j < sArr.length; j++) {
                if (i == j) {
                    pSizeArr[i] = Math.max(1, pSizeArr[i]);
                    continue;
                }

                int middleIdx = calcMiddle(i, j);
                int pSize = calcPSize(sArr, i, j, middleIdx);
                pSizeArr[middleIdx] = Math.max(pSizeArr[middleIdx], pSize);
            }
            // System.out.println(pSizeArr);
        }

        int maxIdx = 0;
        int maxSize = 0;
        for (int i = pSizeArr.length - 1; i >= 0; i--) {
            if (pSizeArr[i] > maxSize) {
                maxSize = pSizeArr[i];
                maxIdx = i;
            }
        }

        if (maxIdx == 0 || maxSize == 0) {
            return "";
        }
        if (maxSize % 2 == 0) {
            return s.substring(maxIdx - maxSize / 2, maxIdx + maxSize / 2);
        } else {
            return s.substring(maxIdx - maxSize / 2, maxIdx + maxSize / 2 + 1);
        }
    }

    public static int calcPSize(char[] sArr, int leftIdx, int rightIdx, int middleIdx) {
        boolean qiSpace = false;
        if ((rightIdx - leftIdx) % 2 == 0) {
            qiSpace = true;
        }

        int pSize = 0;
        int tmpLeft = 0;
        int tmpRight = 0;
        if (qiSpace) {
            tmpLeft = middleIdx - 1;
            tmpRight = middleIdx + 1;
            pSize = 1;
        } else {
            tmpLeft = middleIdx - 1;
            tmpRight = middleIdx;
        }
        if (tmpLeft > tmpRight || tmpLeft < 0) {
            pSize = 1;
        } else {
            while (leftIdx <= tmpLeft && tmpRight <= rightIdx) {
                if (sArr[tmpLeft] == sArr[tmpRight]) {
                    pSize += 2;
                    tmpLeft--;
                    tmpRight++;
                } else {
                    break;
                }
            }
        }
        return pSize;
    }

    public static int calcMiddle(int left, int right) {
        int middleIdx = (right - left) / 2 + left;
        int diff = right - left;
        if (diff % 2 != 0) {
            // diff 是奇数
            middleIdx = middleIdx + 1;
        }
        return middleIdx;
    }
}

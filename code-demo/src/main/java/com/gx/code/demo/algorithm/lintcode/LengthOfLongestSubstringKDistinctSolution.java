package com.gx.code.demo.algorithm.lintcode;

import java.util.Locale;

public class LengthOfLongestSubstringKDistinctSolution {
    public static void main(String args[]) {
//        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 3));
//        System.out.println(lengthOfLongestSubstringKDistinct("WORLD", 4));
        System.out.println(lengthOfLongestSubstringKDistinct("eqgkcwGFvjjmxutystqdfhuMblWbylgjxsxgnoh", 16));
    }

    /**
     * @param s: A string
     * @param k: An integer
     * @return: An integer
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k > s.length()) {
            return 0;
        }

        // write your code here
        char[] inputStrArr = s.toCharArray();
        int[] preDiffCharCount = new int[inputStrArr.length];
        int[] charOccurs = new int[57];

        for (int i = 0; i < inputStrArr.length; i++) {
            char c = inputStrArr[i];
            int index = c - 'A';
            if (charOccurs[index] == 0) {
                charOccurs[index] = 1;
                if (i == 0) {
                    preDiffCharCount[i] = 1;
                } else {
                    preDiffCharCount[i] = preDiffCharCount[i - 1] + 1;
                }
            } else {
                preDiffCharCount[i] = preDiffCharCount[i - 1];
            }
        }

        for (int i = inputStrArr.length - 1; i >= 0; i--) {
            if (preDiffCharCount[i] < k) {
                return i + 1;
            }
        }
        return 0;
    }
}

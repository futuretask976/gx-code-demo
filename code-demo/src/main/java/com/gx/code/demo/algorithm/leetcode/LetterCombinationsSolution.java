package com.gx.code.demo.algorithm.leetcode;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/submissions/?envType=study-plan-v2&envId=top-interview-150
 */
public class LetterCombinationsSolution {
    public static void main(String args[]) {
        System.out.println(letterCombinations("23"));
    }

    static Map<String, List<String>> digitsMap = new HashMap()
    {
        {
            put("0", "");
            put("1", "");
            put("2", Arrays.asList("a", "b", "c"));
            put("3", Arrays.asList("d", "e", "f"));
            put("4", Arrays.asList("g", "h", "i"));
            put("5", Arrays.asList("j", "k", "l"));
            put("6", Arrays.asList("m", "n", "o"));
            put("7", Arrays.asList("p", "q", "r", "s"));
            put("8", Arrays.asList("t", "u", "v"));
            put("9", Arrays.asList("w", "x", "y", "z"));
        }
    };

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.trim().equals("")) {
            return new ArrayList<>();
        }

        char[] digitsArr = digits.toCharArray();
        List<String> result = doLetterCombinations(digitsArr, 0);
        return result;
    }

    public static List<String> doLetterCombinations(char[] digitsArr, int idx) {
        List<String> result = new ArrayList<>();

        List<String> currentList = digitsMap.get(String.valueOf(digitsArr[idx]));
        if (currentList != null && currentList.size() > 0) {
            if (idx == digitsArr.length - 1) {
                result.addAll(currentList);
            } else {
                List<String> nextCom = doLetterCombinations(digitsArr, idx + 1);

                if (nextCom == null || nextCom.size() == 0) {
                    result.addAll(currentList);
                } else {
                    for (int i = 0; i < currentList.size(); i++) {
                        for (int j = 0; j < nextCom.size(); j++) {
                            result.add(currentList.get(i) + nextCom.get(j));
                        }
                    }
                }
            }
        }
        return result;
    }
}

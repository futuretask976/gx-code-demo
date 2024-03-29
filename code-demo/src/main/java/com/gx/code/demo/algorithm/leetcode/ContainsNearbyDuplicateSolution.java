package com.gx.code.demo.algorithm.leetcode;

import com.google.common.collect.Lists;

import java.util.*;

public class ContainsNearbyDuplicateSolution {
    public static void main(String args[]) {
        System.out.println(containsNearbyDuplicate(new int[]{99, 99}, 2));
    }

    private static Map<Integer, List<Integer>> map = new HashMap<>();

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        map.clear();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                List<Integer> lastIdxList = map.get(nums[i]);
                for (Integer lastIdx : lastIdxList) {
                    int abcRtn = Math.abs(lastIdx - i);
                    if (abcRtn <= k) {
                        return true;
                    }
                }
                lastIdxList.add(i);
                map.put(nums[i], lastIdxList);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(nums[i], list);
            }
        }
        return false;
    }
}

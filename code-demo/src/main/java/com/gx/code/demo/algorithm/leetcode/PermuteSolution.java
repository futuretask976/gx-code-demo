package com.gx.code.demo.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PermuteSolution {
    public static void main(String args[]) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums, used, path, result);

        return result;
    }

    public static void dfs(int[] nums, boolean[] used, Deque<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                path.addLast(nums[i]);
                used[i] = true;
                dfs(nums, used, path, result);
                used[i] = false;
                path.removeLast();
            }
        }
    }

}

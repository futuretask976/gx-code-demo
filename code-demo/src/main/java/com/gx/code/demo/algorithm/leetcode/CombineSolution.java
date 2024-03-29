package com.gx.code.demo.algorithm.leetcode;

import java.util.*;

/**
 * https://leetcode.cn/problems/combinations/?envType=study-plan-v2&envId=top-interview-150
 */
public class CombineSolution {
    public static void main(String args[]) {
        System.out.println(combine(4, 2));
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n == 0 ||  k == 0) {
            return result;
        }

        Deque<Integer> path = new ArrayDeque<>();
        pickDigit(n, k, 0, path, result);

        return result;
    }

    private static void pickDigit(int n, int k, int start, Deque<Integer> path, List<List<Integer>> result) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < n; i++) {
            path.addLast(i + 1);
            pickDigit(n, k, i + 1, path, result);
            path.removeLast();
        }
    }
}

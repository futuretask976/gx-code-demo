package com.gx.code.demo.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class ClimbStairsSolution {
    public static void main(String args[]) {

    }

    static Map<Integer, Integer> cache = new HashMap<>();

    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            Integer cached = cache.get(n);
            if (cached != null) {
                return cached;
            } else {
                int result = climbStairs(n - 1) + climbStairs(n - 2);
                cache.put(n, result);
                return result;
            }
        }
    }
}

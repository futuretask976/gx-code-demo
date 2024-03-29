package com.gx.code.demo.algorithm.leetcode;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/triangle/solutions/329143/san-jiao-xing-zui-xiao-lu-jing-he-by-leetcode-solu/?envType=study-plan-v2&envId=top-interview-150
 */
public class MinimumTotalSolution {
    public static void main(String args[]) {
        List<List<Integer>> triangle = Lists.newArrayList();
        // triangle.add(Lists.newArrayList(2));
        // triangle.add(Lists.newArrayList(3,4));
        // triangle.add(Lists.newArrayList(6,5,7));
        // triangle.add(Lists.newArrayList(4,1,8,3));
        triangle.add(Lists.newArrayList(-10));

        System.out.println(minimumTotal(triangle));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < triangle.size(); i++) {
            List<Integer> resultLineList = new ArrayList<>();
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (i == 0 && j == 0) {
                    resultLineList.add(triangle.get(i).get(j));
                } else {
                    int sum = triangle.get(i).get(j);
                    if (j == 0) {
                        if (i == 0) {
                            sum += triangle.get(i - 1).get(j);
                        } else {
                            sum += result.get(i - 1).get(j);
                        }
                    } else if (j >= triangle.get(i - 1).size()) {
                        sum += result.get(i - 1).get(j - 1);
                    } else {
                        sum += Math.min(result.get(i - 1).get(j - 1), result.get(i - 1).get(j));
                    }
                    resultLineList.add(j, sum);
                }
            }
            result.add(i, resultLineList);
        }

        List<Integer> lastRow = result.get(triangle.size() - 1);
        int min = Integer.MAX_VALUE;
        for (Integer ite : lastRow) {
            min = Math.min(min, ite);
        }
        return min;
    }
}

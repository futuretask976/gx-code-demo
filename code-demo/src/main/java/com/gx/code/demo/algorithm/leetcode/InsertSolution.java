package com.gx.code.demo.algorithm.leetcode;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class InsertSolution {
    public static void main(String args[]) {
        // System.out.println(insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{4,8}));
        System.out.println(insert(new int[][]{{2,5},{6,7},{8,9}}, new int[]{0,1}));
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<IntervalScope> intervalScopeList = new ArrayList<>();

        boolean hasProcessed = false;
        boolean hasMerged = false;
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];

            // 当前区间在待插入区间的左边，直接将当前区间插入到结果列表
            if (interval[1] < newInterval[0]) {
                intervalScopeList.add(new IntervalScope(interval[0], interval[1]));
                continue;
            }
            // 当前区间在待插入区间的右边
            if (interval[0] > newInterval[1]) {
                if (!hasMerged && !hasProcessed) {
                    intervalScopeList.add(new IntervalScope(newInterval[0], newInterval[1]));
                    hasProcessed = true;
                }
                intervalScopeList.add(new IntervalScope(interval[0], interval[1]));
                continue;
            }

            // 需要进行融合
            // 先判断是否已经融合过
            if (hasMerged) {
                // 需要和结果列表最后一个节点判断
                IntervalScope lastIntervalScope = intervalScopeList.get(intervalScopeList.size() - 1);
                if (lastIntervalScope.left <= interval[0] && interval[1] <= lastIntervalScope.right) {
                    // 当前区间被完全吃掉
                } else {
                    // 结果列表最后一个节点的左、右边界拓宽
                    lastIntervalScope.left = Math.min(lastIntervalScope.left, interval[0]);
                    lastIntervalScope.right = Math.max(lastIntervalScope.right, interval[1]);
                }
            } else {
                // 直接和当前区间融合，再插入结果列表
                IntervalScope intervalScope = new IntervalScope(newInterval[0], newInterval[1]);
                if (intervalScope.left <= interval[0] && interval[1] <= intervalScope.right) {
                    // 当前区间被完全吃掉
                } else {
                    // 结果列表最后一个节点的左、右边界拓宽
                    intervalScope.left = Math.min(intervalScope.left, interval[0]);
                    intervalScope.right = Math.max(intervalScope.right, interval[1]);
                }
                intervalScopeList.add(intervalScope);
                hasMerged = true;
                hasProcessed = true;
            }
        }

        if (!hasProcessed) {
            IntervalScope intervalScope = new IntervalScope(newInterval[0], newInterval[1]);
            intervalScopeList.add(intervalScope);
        }

        if (intervalScopeList.isEmpty()) {
            return null;
        } else {
            int[][] newIntervals = new int[intervalScopeList.size()][2];
            for (int i = 0; i < intervalScopeList.size(); i++) {
                IntervalScope intervalScope = intervalScopeList.get(i);
                newIntervals[i] = new int[]{intervalScope.left, intervalScope.right};
            }
            return newIntervals;
        }
    }

    static class IntervalScope {
        public int left;
        public int right;

        public IntervalScope(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}

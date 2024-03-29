package com.gx.code.demo.algorithm.leetcode;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class FindMinArrowShotsSolution {
    public static void main(String args[]) {
//        System.out.println(findMinArrowShots(new int[][]{{10,16},{2,8},{1,6},{7,12}}));
//        System.out.println(findMinArrowShots(new int[][]{{1,2},{3,4},{5,6},{7,8}}));
//        System.out.println(findMinArrowShots(new int[][]{{1,2},{2,3},{3,4},{4,5}}));
        System.out.println(findMinArrowShots(new int[][]{{9,12},{1,10},{4,11},{8,12},{3,9},{6,9},{6,7}}));
//        System.out.println(findMinArrowShots(new int[][]{{-2147483648,2147483647}}));
    }

    public static int findMinArrowShots(int[][] points) {
        TreeSet<PointScope> leftSet = initLeftSet(points);
        Integer smallestRight = null;
        int cnt = 0;
        while (leftSet != null && !leftSet.isEmpty()) {
            smallestRight = findSmallestRight(leftSet, smallestRight);
            removeLeftSet(leftSet, smallestRight);
            cnt++;
        }
        return cnt;
    }

    private static int findSmallestRight(TreeSet<PointScope> leftBorderSet, Integer lastRight) {
        int smallestRight = leftBorderSet.first().right;
        Iterator<PointScope> ite = leftBorderSet.iterator();
        while (ite.hasNext()) {
            smallestRight = Math.min(smallestRight, ite.next().right);
        }
        return smallestRight;
    }

    private static TreeSet<PointScope> initLeftSet(int[][] points) {
        TreeSet<PointScope> treeSet = new TreeSet<>(new Comparator<PointScope>() {
            @Override
            public int compare(PointScope o1, PointScope o2) {
                if (o1.left < o2.left) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        for (int i = 0; i < points.length; i++) {
            treeSet.add(new PointScope(points[i][0], points[i][1]));
        }
        return treeSet;
    }

    private static void removeLeftSet(TreeSet<PointScope> leftBorderSet, Integer lastRight) {
        Iterator<PointScope> ite = leftBorderSet.iterator();
        while (ite.hasNext()) {
            if (ite.next().left <= lastRight) {
                ite.remove();
            } else {
                break;
            }
        }
    }

    static class PointScope {
        public int left;
        public int right;
        public PointScope(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}

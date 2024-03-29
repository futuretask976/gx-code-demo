package com.gx.code.demo.algorithm.leetcode;

import java.util.*;

public class SumOfDistancesInTreeSolution {
    public static void main(String args[]) {
//        System.out.println(sumOfDistancesInTree(6, new int[][]{{0,1},{0,2},{2,3},{2,4},{2,5}}));
//        System.out.println(sumOfDistancesInTree(2, new int[][]{{1,0}}));
        System.out.println(sumOfDistancesInTree(1, new int[][]{}));
    }

    public static int[] sumOfDistancesInTree(int n, int[][] edges) {
        int[][] pathMap = new int[n][n];
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            if (edge.length < 2) {
                continue;
            }
            pathMap[edge[0]][edge[1]] = 1;
            pathMap[edge[1]][edge[0]] = 1;
        }

        int[][] pathMapResult = new int[n][n];
        for (int i = 0; i < pathMap.length; i++) {
            for (int j = 0; j < pathMap.length; j++) {
                if (i == j) {
                    pathMap[i][j] = 0;
                    pathMapResult[i][j] = 0;
                    continue;
                }
                if (pathMap[i][j] != 0) {
                    pathMapResult[i][j] = 1;
                    continue;
                }
                if (pathMapResult[j][i] > 0) {
                    pathMapResult[i][j] = pathMapResult[j][i];
                }
                Deque<Integer> path = new ArrayDeque<>();
                path.addLast(i);
                boolean found = findPath(i, j, pathMap, path);
                if (found) {
                    pathMapResult[i][j] = path.size() - 1;
                }
                path.clear();
            }
        }

        int[] ans = new int[pathMapResult.length];
        for (int i = 0; i < pathMapResult.length; i++) {
            int sum = 0;
            for (int j = 0; j < pathMapResult[i].length; j++) {
                sum += pathMapResult[i][j];
            }
            ans[i] = sum;
        }
        return ans;
    }

    public static boolean findPath(int start, int end, int[][] pathMap, Deque<Integer> path) {
        if (pathMap[start][end] != 0) {
            path.addLast(end);
            return true;
        }

        int[] hasPath = pathMap[start];
        for (int i = 0; i < hasPath.length; i++) {
            if (hasPath[i] == 0) {
                continue;
            }
            if (path.contains(i)) {
                continue;
            }
            path.addLast(i);
            boolean found = findPath(i, end, pathMap, path);
            if (found) {
                return true;
            }
            path.removeLast();
        }
        return false;
    }
}

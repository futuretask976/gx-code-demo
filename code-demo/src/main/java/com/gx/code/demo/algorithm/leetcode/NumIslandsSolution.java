package com.gx.code.demo.algorithm.leetcode;

public class NumIslandsSolution {
    public static void main(String args[]) {
        System.out.println(numIslands(new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}}));
        System.out.println(numIslands(new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}}));
    }

    public static int numIslands(char[][] grid) {
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    cnt++;
                    traverse(grid, i, j);
                }
            }
        }
        return cnt;
    }

    private static void traverse(char[][] grid, int left, int right) {
        if (!isInAre(grid, left, right)) {
            return;
        }
        if (grid[left][right] == '0') {
            return;
        }
        if (grid[left][right] == '2') {
            return;
        }

        grid[left][right] = '2';
        traverse(grid, left - 1, right);
        traverse(grid, left + 1, right);
        traverse(grid, left, right - 1);
        traverse(grid, left, right + 1);
    }

    private static boolean isInAre(char[][] grid, int left, int right) {
        int leftHei = grid.length;
        int rightHei = grid[0].length;

        if (left >=0 && right >= 0 && left < leftHei && right < rightHei) {
            return true;
        } else {
            return false;
        }
    }
}

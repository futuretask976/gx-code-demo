package com.gx.code.demo.algorithm.leetcode;

/**
 * https://leetcode.cn/problems/unique-paths-ii/?envType=study-plan-v2&envId=top-interview-150
 */
public class UniquePathsWithObstaclesSolution {
    public static void main(String args[]) {
        int[][] obstacleGrid = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int columnLength = obstacleGrid.length;
        int rowLength = obstacleGrid[0].length;
        int[][] assGrid = new int[columnLength][rowLength];

        for (int i = 0; i < columnLength; i++) {
            int[] row = obstacleGrid[i];
            for (int j = 0; j < rowLength; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }

                if (i == 0 && j ==0) {
                    assGrid[i][j] = 1;
                } else {
                    if (i == 0 && j > 0) {
                        assGrid[i][j] = assGrid[i][j - 1];
                    } else if (i > 0 && j == 0) {
                        assGrid[i][j] = assGrid[i - 1][j];
                    } else {
                        int count = assGrid[i - 1][j] + assGrid[i][j - 1];
                        assGrid[i][j] = count;
                    }
                }
            }
        }
        return assGrid[columnLength - 1][rowLength - 1];
    }
}

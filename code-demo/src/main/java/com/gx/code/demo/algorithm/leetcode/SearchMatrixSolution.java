package com.gx.code.demo.algorithm.leetcode;

public class SearchMatrixSolution {
    public static void main(String args[]) {
//        System.out.println(findMid(0, 3));
//        System.out.println(findMid(1, 3));
//        System.out.println(findMid(2, 3));
//        System.out.println(findMid(0, 1));
//        System.out.println(findMid(0, 2));

//        System.out.println("----");

//        System.out.println(findMid(0, 4));
//        System.out.println(findMid(1, 4));
//        System.out.println(findMid(2, 4));

        System.out.println(findRow(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 2));
//        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 13));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int targetRow = findRow(matrix, target);
        if (targetRow == -1) {
            return false;
        }

        int targetColumn = findColumn(matrix, target, targetRow);
        if (targetColumn == -1) {
            return false;
        }

        return true;
    }

    public static int findRow(int[][] matrix, int target) {
        int low = 0;
        int high = matrix.length - 1;
        int mid = findMid(low, high);
        while (mid >= 0) {
            if (low >= high) {
                if (matrix[mid][0] < target) {
                    return mid;
                } else {
                    return -1;
                }
            }

            mid = findMid(low, high);
            if (matrix[mid][0] > target) {
                high = mid - 1;
            } else if (matrix[mid][0] < target) {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int findColumn(int[][] matrix, int target, int rowIdx) {
        int width = matrix[rowIdx].length;
        int midWidth = findMid(0, width);

        int left = 0;
        int right = midWidth;
        while (midWidth >= 0) {
            if (matrix[rowIdx][midWidth] == target
                    || midWidth == 0
                    || midWidth == width - 1) {
                return midWidth;
            }

            if (matrix[rowIdx][midWidth] > target) {
                right = midWidth;
                midWidth = findMid(left, right);
            } else if (matrix[rowIdx][midWidth] < target) {
                left = midWidth + 1;
                midWidth = findMid(left, right);
            }
        }
        return -1;
    }

    public static int findMid(int start, int end) {
        return (end - start) / 2 + start;
    }
}

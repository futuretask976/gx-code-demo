package com.gx.code.demo.algorithm.leetcode;

public class SolveSolution {
    public static void main(String args[]) {
//        solve(new char[][]{
//                {'X','X','X','X'},
//                {'X','O','O','X'},
//                {'X','X','O','X'},
//                {'X','O','X','X'}});
//        solve(new char[][]{
//                {'X'}});
//        solve(new char[][]{
//                {'O','O','O','O','X','X'},
//                {'O','O','O','O','O','O'},
//                {'O','X','O','X','O','O'},
//                {'O','X','O','O','X','O'},
//                {'O','X','O','X','O','O'},
//                {'O','X','O','O','O','O'}});
        solve(new char[][]{
                {'X','O','O','X','X','X','O','X','O','O'},
                {'X','O','X','X','X','X','X','X','X','X'},
                {'X','X','X','X','O','X','X','X','X','X'},
                {'X','O','X','X','X','O','X','X','X','O'},
                {'O','X','X','X','O','X','O','X','O','X'},
                {'X','X','O','X','X','O','O','X','X','X'},
                {'O','X','X','O','O','X','O','X','X','O'},
                {'O','X','X','X','X','X','O','X','X','X'},
                {'X','O','O','X','X','O','X','X','O','O'},
                {'X','X','X','O','O','X','O','X','X','O'}});
    }

    public static void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O') {
                    boolean hasOuter = traverseHasOuter(board, i, j);
                    if (!hasOuter) {
                        traverseOverrideAWithX(board, i, j);
                    } else {
                        traverseOverrideAWithB(board, i, j);
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                }
            }
        }
        return;
    }

    private static void traverseOverrideAWithX(char[][] grid, int left, int right) {
        if (!isInAre(grid, left, right)) {
            return;
        }
        if (grid[left][right] == 'X') {
            return;
        }
        if (grid[left][right] == 'O') {
            return;
        }

        grid[left][right] = 'X';
        traverseOverrideAWithX(grid, left - 1, right);
        traverseOverrideAWithX(grid, left + 1, right);
        traverseOverrideAWithX(grid, left, right - 1);
        traverseOverrideAWithX(grid, left, right + 1);
    }

    private static void traverseOverrideAWithB(char[][] grid, int left, int right) {
        if (!isInAre(grid, left, right)) {
            return;
        }
        if (grid[left][right] == 'X') {
            return;
        }
        if (grid[left][right] == 'O') {
            return;
        }
        if (grid[left][right] == 'B') {
            return;
        }

        grid[left][right] = 'B';
        traverseOverrideAWithB(grid, left - 1, right);
        traverseOverrideAWithB(grid, left + 1, right);
        traverseOverrideAWithB(grid, left, right - 1);
        traverseOverrideAWithB(grid, left, right + 1);
    }

    private static boolean traverseHasOuter(char[][] grid, int left, int right) {
        if (!isInAre(grid, left, right)) {
            return false;
        }
        if (grid[left][right] == 'X') {
            return false;
        }
        if (grid[left][right] == 'A') {
            return false;
        }

        grid[left][right] = 'A';
        boolean isBorder = false;
        if (isBorder(grid, left, right)) {
            isBorder = true;
        }

        boolean leftMinus = traverseHasOuter(grid, left - 1, right);
        boolean leftPlus = traverseHasOuter(grid, left + 1, right);
        boolean rightMinus = traverseHasOuter(grid, left, right - 1);
        boolean rightPlus = traverseHasOuter(grid, left, right + 1);

        if (leftMinus || leftPlus || rightMinus || rightPlus) {
            isBorder = true;
        }
        return isBorder;
    }

    private static boolean isBorder(char[][] grid, int left, int right) {
        int leftHei = grid.length;
        int rightHei = grid[0].length;

        if (left == 0 || left == leftHei - 1 || right == 0 || right == rightHei - 1) {
            return true;
        } else {
            return false;
        }
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

package com.gx.code.demo.algorithm.leetcode;

import com.alibaba.fastjson.support.odps.CodecCheck;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://leetcode.cn/problems/n-queens-ii/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class TotalNQueensSolution {
    public static void main(String args[]) {
        System.out.println(totalNQueens(1));
    }

    public static int totalNQueens(int n) {
        List<List<Pos>> solutions = new ArrayList<>();
        Deque<Pos> path = new ArrayDeque<>();

        findPath(n, 0, path, solutions);
        return solutions.size();
    }

    public static void findPath(int n, int startI, Deque<Pos> path, List<List<Pos>> solutions) {
        if (path.size() == n) {
            solutions.add(new ArrayList<>(path));
            return;
        }

        for (int i = startI; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Pos pos = new Pos(i, j);
                if (isValid(path, i, j)) {
                    path.addLast(pos);
                    findPath(n, i + 1, path, solutions);
                    path.removeLast();
                }
            }
        }
    }

    public static boolean isValid(Deque<Pos> path, int i, int j) {
        for (Pos pos : path) {
            if (pos.i == i || pos.j == j) {
                return false;
            }

            int rowDiff = i - pos.i;
            int leftJ = pos.j - rowDiff;
            int rightJ = pos.j + rowDiff;
            if (leftJ == j || rightJ == j) {
                return false;
            }
        }
        return true;
    }

    static class Pos {
        public int i;

        public int j;

        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}

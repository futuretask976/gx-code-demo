package com.gx.code.demo.algorithm.leetcode;

import com.gx.code.demo.algorithm.leetcode.model.TreeNode;

public class GetMinimumDifferenceSolution {
    public static void main(String args[]) {

    }

    private static int minDiff = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        minDiff = Integer.MAX_VALUE;
        dfs(root);
        return minDiff;
    }

    private static void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(node.left);
        int leftDiff = node.left == null ? minDiff : Math.abs(node.val - node.left.val);
        int rightDiff = node.right == null ? minDiff : Math.abs(node.val - node.right.val);
        minDiff = Math.min(minDiff, leftDiff);
        minDiff = Math.min(minDiff, rightDiff);
        dfs(node.right);
    }
}

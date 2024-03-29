package com.gx.code.demo.algorithm.leetcode;

import com.gx.code.demo.algorithm.leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestSolution {
    public static void main(String args[]) {

    }

    private static List<TreeNode> nodeDeque = new ArrayList<>();

    public static int kthSmallest(TreeNode root, int k) {
        nodeDeque.clear();
        dfs(root, k);
        if (nodeDeque.size() < k) {
            return -1;
        } else {
            return nodeDeque.get(k - 1).val;
        }
    }

    public static void dfs(TreeNode node, int k) {
        if (nodeDeque.size() >= k) {
            return;
        }
        if (node == null) {
            return;
        } else {
            dfs(node.left, k);
            nodeDeque.add(node);
            dfs(node.right, k);
        }
    }
}

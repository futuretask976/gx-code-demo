package com.gx.code.demo.algorithm.leetcode;

import com.gx.code.demo.algorithm.leetcode.model.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CloneGraphSolution {
    private static Map<Node, Node> cloneMap = new HashMap<>();

    public static Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        cloneMap.clear();

        Node cloned = doClone(node);
        return cloned;
    }

    private static Node doClone(Node node) {
        if (cloneMap.containsKey(node)) {
            return cloneMap.get(node);
        }

        Node cloned = new Node();
        cloned.val = node.val;
        cloned.neighbors = new ArrayList<>();
        cloneMap.put(node, cloned);
        for (Node nei : node.neighbors) {
            Node neiCloned = doClone(nei);
            cloned.neighbors.add(neiCloned);
        }

        return cloned;
    }
}

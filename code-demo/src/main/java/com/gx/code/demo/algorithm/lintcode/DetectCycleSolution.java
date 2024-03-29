package com.gx.code.demo.algorithm.lintcode;

import com.gx.code.demo.algorithm.leetcode.model.ListNode;

import java.util.HashSet;
import java.util.Set;

public class DetectCycleSolution {
    public static void main(String args[]) {

    }

    static Set<ListNode> cacheSet = new HashSet<>();

    /**
     * @param head: The first node of linked list.
     * @return: The node where the cycle begins. if there is no cycle, return null
     */
    public ListNode detectCycle(ListNode head) {
        // write your code here
        cacheSet.clear();

        ListNode left = head;
        while (left != null) {
            if (cacheSet.contains(left)) {
                return left;
            } else {
                left = left.next;
            }
        }
        return null;
    }
}

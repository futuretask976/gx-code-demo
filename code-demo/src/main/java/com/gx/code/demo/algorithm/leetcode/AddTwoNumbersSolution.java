package com.gx.code.demo.algorithm.leetcode;

import com.gx.code.demo.algorithm.leetcode.model.ListNode;

public class AddTwoNumbersSolution {
    public static void main(String args[]) {
        ListNode l1 = ListNode.convertArrToNode(new int[]{0});
        ListNode l2 = ListNode.convertArrToNode(new int[]{0});

        System.out.println(addTwoNumbers(l1, l2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int quota = 10;

        ListNode head = null, pre = null;
        boolean greaterTen = false;
        while (l1 != null || l2 != null) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;

            int sum = l1Val + l2Val + (greaterTen ? 1 : 0);
            ListNode newNode = null;
            if (sum < 10) {
                newNode = new ListNode(sum);
                greaterTen = false;
            } else {
                newNode = new ListNode(sum % quota);
                greaterTen = true;
            }

            if (pre == null) {
                head = newNode;
                pre = head;
            } else {
                pre.next = newNode;
                pre = newNode;
            }

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if (greaterTen) {
            ListNode newNode = new ListNode(1);
            if (pre == null) {
                pre = newNode;
            } else {
                pre.next = newNode;
            }
        }

        return head;
    }
}

package com.gx.code.demo.algorithm.leetcode.model;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int x, ListNode next) {
        val = x;
        next = next;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(val).append("->");

        ListNode nextNode = next;
        while (nextNode != null) {
            sb.append(nextNode.val).append("->");
            nextNode = nextNode.next;
        }
        return sb.toString();
    }

    public static ListNode convertArrToNode(int[] arr) {
        ListNode headNode = null;
        ListNode prevNode = null;
        for (int a : arr) {
            ListNode currNode = new ListNode();
            currNode.val = a;
            if (headNode == null) {
                headNode = currNode;
            }
            if (prevNode != null) {
                prevNode.next = currNode;
            }
            prevNode = currNode;
        }
        System.out.println("headNode : " + headNode);
        return headNode;
    }
}
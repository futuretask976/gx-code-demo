package com.gx.code.demo.algorithm.leetcode;

public class FindMinSolution {
    public static void main(String args[]) {
        System.out.println(findMin(new int[]{3,4,5,1,2}));
//        System.out.println(findMin(new int[]{4,5,6,7,0,1,2}));
//        System.out.println(findMin(new int[]{11,13,15,17}));
//        System.out.println(findMin(new int[]{2, 1}));
    }

    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}

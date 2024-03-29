package com.gx.code.demo.algorithm.lintcode;

public class TwoSumSolution {
    public static void main(String args[]) {
        int[] nums = new int[]{-111,-100,-98,-11,-6,-5,-4,-3,-2,-1};
        int target = -111;
        twoSum(nums, target);
    }

    /**
     * @param nums: an array of Integer
     * @param target: target = nums[index1] + nums[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public static int[] twoSum(int[] nums, int target) {
        // write your code here
        int leftIdx = 0;
        int rightIdx = 1;

        int[] result = new int[2];
        while (leftIdx < rightIdx && rightIdx < nums.length) {
            int sum = nums[leftIdx] + nums[rightIdx];
            if (sum == target) {
                result[0] = leftIdx + 1;
                result[1] = rightIdx + 1;
                break;
            } else if (sum < target && rightIdx != nums.length - 1) {
                rightIdx++;
            } else {
                leftIdx++;
                rightIdx = leftIdx + 1;
            }
        }

        return result;
    }
}

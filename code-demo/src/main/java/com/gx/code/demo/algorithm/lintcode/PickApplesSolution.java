package com.gx.code.demo.algorithm.lintcode;

/**
 * 原题：https://www.lintcode.com/problem/1850/?utm_source=sc-zhihuzl-sy
 */
public class PickApplesSolution {
    public static void main(String args[]) {

    }

    /**
     * @param A: a list of integer
     * @param K: a integer
     * @param L: a integer
     * @return: return the maximum number of apples that they can collect.
     */
    public int PickApples(int[] A, int K, int L) {
        int n = A.length;
        if (n < K + L) {
            return - 1;
        }
        int[] prefixSum = new int[n];
        //计算前缀和
        prefixSum[0] = A[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = A[i] + prefixSum[i - 1];
        }

        // prefixK 代表前 i 个数中，长度为 K 的子区间和的最大值
        int[] prefixK = new int[n];
        int[] prefixL = new int[n];

        // postfixK 代表后面 [i, n - 1] 中，长度为 K 的子区间和的最大值
        int[] postfixK = new int[n];
        int[] postfixL = new int[n];

        // 计算前缀值
        for (int i = 0; i < n; i++) {
            if (i == K - 1) {
                prefixK[i] = rangeSum(prefixSum, i - K + 1, i);
            }
            else if (i > K - 1) {
                prefixK[i] = Math.max(rangeSum(prefixSum, i - K + 1, i), prefixK[i - 1]);
            }
            if (i == L - 1) {
                prefixL[i] = rangeSum(prefixSum, i - L + 1, i);
            }
            else if (i > L - 1) {
                prefixL[i] = Math.max(rangeSum(prefixSum, i - L + 1, i), prefixL[i - 1]);
            }
        }

        // 计算后缀值
        for (int i = n - 1; i >= 0; i--) {
            if (i + K - 1 == n - 1) {
                postfixK[i] = rangeSum(prefixSum, i, i + K - 1);
            }
            else if (i + K - 1 < n - 1) {
                postfixK[i] = Math.max(rangeSum(prefixSum, i, i + K - 1), postfixK[i + 1]);
            }
            if (i + L - 1 == n - 1) {
                postfixL[i] = rangeSum(prefixSum, i, i + L - 1);
            }
            else if (i + L - 1 < n - 1) {
                postfixL[i] = Math.max(rangeSum(prefixSum, i, i + L - 1), postfixL[i + 1]);
            }
        }

        int result = 0;
        // 枚举分界点，计算答案
        for (int i = 0; i < n - 1; i++) {
            result = Math.max(result, prefixK[i] + postfixL[i + 1]);
            result = Math.max(result, prefixL[i] + postfixK[i + 1]);
        }
        return result;
    }

    private static int rangeSum(int[] prefixSum, int l, int r) {
        if (l == 0) {
            return prefixSum[r];
        }
        return prefixSum[r] - prefixSum[l - 1];
    }
}

package com.gx.code.demo.algorithm.leetcode;

import java.sql.Array;
import java.util.*;

public class CoinChangeSolution {
    public static void main(String args[]) {
         System.out.println(coinChange(new int[]{1,2,5}, 11));
         System.out.println(coinChange(new int[]{3}, 2));
         System.out.println(coinChange(new int[]{2}, 3));
         System.out.println(coinChange(new int[]{1}, 0));
         System.out.println(coinChange(new int[]{186,419,83,408}, 6249));
    }

    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int[] count = new int[amount];
        int rtn = calc(coins, amount, count);
        return rtn;
    }

    public static int calc(int[] coins, int remained, int[] count) {
        if (remained < 0) {
            return -1;
        }
        if (remained == 0) {
            return 0;
        }
        if (count[remained - 1] != 0) {
            return count[remained - 1];
        }

        int min = Integer.MAX_VALUE;
        for (int i = coins.length - 1; i >= 0; i--) {
            int tmpRtn = calc(coins, remained - coins[i], count);
            if (tmpRtn >= 0 && tmpRtn < min) {
                min = tmpRtn + 1;
            }
        }
        count[remained - 1] = min == Integer.MAX_VALUE ? -1 : min;
        return count[remained - 1];
    }
}

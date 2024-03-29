package com.gx.code.demo.algorithm.leetcode;

public class AddBinarySolution {
    public static void main(String args[]) {
        // System.out.println(addBinary("111", "11"));
        // System.out.println(addBinary("11", "1"));
        System.out.println(addBinary("1010", "1011"));
    }

    public static String addBinary(String a, String b) {
        char[] aArr = a.toCharArray();
        char[] bArr = b.toCharArray();

        int aIdx = aArr.length - 1;
        int bIdx = bArr.length - 1;

        String result = "";
        boolean yichu = false;
        int last = 0;
        while (aIdx >= 0 || bIdx >= 0) {
            last = yichu ? 1 : 0;
            yichu = false;

            int aDigit = 0;
            if (aIdx >= 0) {
                aDigit = aArr[aIdx] == '1' ? 1 : 0;
            }
            int bDigit = 0;
            if (bIdx >= 0) {
                bDigit = bArr[bIdx] == '1' ? 1 : 0;;
            }

            int tmp = 0;
            if (aDigit == 0 && bDigit == 0) {
                tmp = 0;
            } else if (aDigit == 1 && bDigit == 0) {
                tmp = 1;
            } else if (aDigit == 1 && bDigit == 1) {
                tmp = 0;
                yichu = true;
            } else if (aDigit == 0 && bDigit == 1) {
                tmp = 1;
            }

            if (last == 1) {
                if (tmp == 1) {
                    tmp = 0;
                } else {
                    tmp = 1;
                }
            }

            result = tmp + result;
            aIdx--;
            bIdx--;
        }

        if (last == 1) {
            result = last + result;
        }
        if (yichu) {
            result = "1" + result;
        }

        return result;
    }
}

package com.gx.code.demo.algorithm;

/**
 * 参考：https://blog.csdn.net/seagal890/article/details/93395814
 */
public class StringArrange {
    public static void main(String[] args){
        String str = "abcd";
        char[] array = str.toCharArray();

        StringArrange test = new StringArrange();
        test.recursionArrange(array, 0, array.length - 1);
    }

    /*
     * 参数array:给定字符串的字符数组
     * 参数start:开始遍历字符与其后面各个字符将要进行交换的位置
     * 参数end:字符串数组的最后一位
     * 函数功能：输出字符串数字的各个字符全排列
     */
    public void recursionArrange(char[] array, int start, int end) {
        if (end <= 1)
            return;
        if (start == end) {
            for (int i = 0; i < array.length; i++)
                System.out.print(array[i]);
            System.out.println();
        } else {
            for (int i = start; i <= end; i++) {
                swap(array, i, start);
                recursionArrange(array, start + 1, end);
                swap(array, i, start);
            }
        }
    }

    // 交换数组m位置和n位置上的值
    private void swap(char[] array, int m, int n) {
        char temp = array[m];
        array[m] = array[n];
        array[n] = temp;
    }
}

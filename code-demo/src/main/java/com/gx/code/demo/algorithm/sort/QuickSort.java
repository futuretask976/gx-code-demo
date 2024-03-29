package com.gx.code.demo.algorithm.sort;

public class QuickSort {
    private static QuickSort quickSort = new QuickSort();

    public static void main(String args[]) {
        int[] inputArr = new int[]{9, 8, 1, 2, 5, 7, 8};
        quickSort.quickSort(inputArr, 0, inputArr.length - 1);
        for (int i = 0; i < inputArr.length; i++) {
            System.out.print(inputArr[i] + ", ");
        }
    }

    public void quickSort(int[] arr, int low, int high) {
        if (arr == null) {
            return;
        }
        if (low > high) {
            return;
        }

        int temp = arr[low];
        int i = low, j = high;
        while (i < j) {
            while(temp <= arr[j] && i < j) {
                j--;
            }
            while (temp >= arr[i] && i < j) {
                i++;
            }
            if (i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }

        arr[low] = arr[i];
        arr[i] = temp;

        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);
    }
}

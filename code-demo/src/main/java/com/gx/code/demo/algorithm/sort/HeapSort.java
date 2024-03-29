package com.gx.code.demo.algorithm.sort;

public class HeapSort {
    private static HeapSort quickSort = new HeapSort();

    public static void main(String args[]) {
//        int[][] inputArr = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}};
//        quickSort.heapSort(inputArr);
//        for (int i = 0; i < inputArr.length; i++) {
//            System.out.print(inputArr[i] + ", ");
//        }
//        Set<Integer> columnExistMark = new HashSet<Integer>();
    }

    /**
     * 堆排序
     * 父推子: 2n+1(左子树) 2n+2(右子树)
     * 子推父: (n-1)/2
     * 上面的 n 为当前节点的索引
     */
    public static void heapSort(int[] arr) {
        // 构造初始堆，从第一个非叶子节点开始调整，左右孩子节点中较大的交换到父节点中
        for (int head = (arr.length) / 2 - 1; head >= 0; head--) {
            heapAdjust(arr, arr.length, head);
        }
        // 排序，将最大的节点放在堆尾，然后从根节点重新调整
        for (int i = arr.length - 1; i >= 1; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapAdjust(arr, i, 0);
        }
    }


    private static void heapAdjust(int[] list, int len, int head) {
        int k = head, temp = list[head], index = 2 * k + 1;
        while (index < len) {
            if (index + 1 < len) {
                if (list[index] < list[index + 1]) {
                    index = index + 1;
                }
            }
            if (list[index] > temp) {
                list[k] = list[index];
                // 以当前节点为父节点
                k = index;
                // 定位到 k 节点的左子节点
                index = 2 * k + 1;
            } else {
                break;
            }
        }
        list[k] = temp;
    }
}

package com.bjpowernode.sort.BubbleSort;

/**
 * 冒泡排序算法
 * 排序原理：
 * 1.比较相邻的元素。如果前一个元素比后一个元素大，就交换这两个元素的位置
 * 2.对每一对相邻元素做同样的工作，从开始第一对元素到结尾的最后一对元素。最终最后位置的元素就是最大值
 * 3.优化：如果我们发现在某趟排序过程中，没有发生交换，表明已经是按顺序排列的，可以直接退出
 * 时间复杂度O(n2)
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3, 8, -1, 10, 2};

        int temp = 0;
        boolean flag = false;//标识
        //一次排序结束排出一个最大值，共 arr.length个数，需要进行 arr.length - 1次就能排完
        for (int i = 0; i < arr.length - 1; i++) {
            //比较交换过程，需要注意的是，上一次排完的数，这次就不参与进来了，所以比较次数是越来越少的
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                return;
            } else {
                flag = false;//重置flag，使其适用于下一次排序过程
            }

        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }

    }
}

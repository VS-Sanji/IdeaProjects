package com.bjpowernode.sort.SelectSort;

/**
 * 选择排序
 * 排序原理：
 * 1.每一次遍历的过程中，都假定剩余第一个索引处的元素是最小值，和其他索引处的值依次进行比较，如果当前索引处的值大于其他某个索引处的值，则假定其他某个索引处的值为最小值，最后可以找到最小值所在的索引
 * 2.交换第一个索引处和最小值所在索引处的值
 * 时间复杂度O(n2)
 */
public class SelectSort {
    public static void main(String[] args) {
        int arr[] = {2, 4, 1, 3, 5, 2};

        int minIndex;//设置一个记录最小值下标的变量
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            int temp;
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }
    }
}

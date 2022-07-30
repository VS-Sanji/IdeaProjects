package cn.itcast.algorithm.test;

import cn.itcast.algorithm.sort.Bubble;

import java.util.Arrays;

public class BubbleTest {
    public static void main(String[] args) {
        Integer[] arr = {2, 5, 2, 3, 5, 6, 1};
        Bubble.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

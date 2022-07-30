package cn.itcast.algorithm.test;

import cn.itcast.algorithm.sort.Select;

import java.util.Arrays;

public class SelectTest {
    public static void main(String[] args) {
        Integer[] a = {4, 6, 7, 1, 3, 2, 9};
        Select.sort(a);

        System.out.println(Arrays.toString(a));
    }
}

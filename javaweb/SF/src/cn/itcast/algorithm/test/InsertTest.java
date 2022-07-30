package cn.itcast.algorithm.test;

import cn.itcast.algorithm.sort.Insert;

import java.util.Arrays;

public class InsertTest {
    public static void main(String[] args) {
        Integer[] a = {6, 4, 3, 2, 2, 1};
        Insert.sort(a);
        System.out.println(Arrays.toString(a));
    }

}

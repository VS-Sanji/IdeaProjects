package com.bjpowernode.Recursion;

/**
 * 思路分析：
 * 1.第一个皇后先放在第一行第一列
 * 2.第二个皇后放在第二行第一列，然后判断是否ok，如果不ok，继续放在第二行第二列、第三列、依次把所有列都放完
 * 3.继续第三个皇后，还是第一列、第二列...直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解
 * 4.当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后放到第一列的所有正确解都拿到
 * 5.然后回头继续第一个皇后放第二列，后面继续循环1，2，3，4的步骤
 */
public class Queen8 {
    //定义一个max表示有多少个皇后
    int max = 8;

    //定义一个count，用来计数
    int count = 0;

    //定义数组array，保存皇后放置位置的结果，比如，arr = {0， 4， 7， 5， 2， 6， 1， 3}
    //0表示第一个皇后的摆放位置为 第0行第0列； 4表示第二个皇后的摆放位置为 第1行第3列 以下标来计
    int[] array = new int[8];

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println(queen8.count);

    }

    //编写一个方法，放置第n个皇后
    private void check(int n) {
        if (n == max) { //n能够递到8，表明前面 0 - 7 下标的全部都放好了，即找到了一个正确解
            print();
            return;
        }
        for (int i = 0; i < max; i++) { //这个for循环保证了每一行的所有位置都进行尝试，不管成功还是失败都能往下执行
            array[n] = i; //先把当前这个皇后，放到该行的第i列;
            if (judge(n)) { //不冲突，则放下一个，往下递
                check(n + 1);
            }
            //第i个冲突，则回溯，让i++，继续执行判断
        }
    }


    //查看当我们放置第n个皇后（下标），就去检测该皇后是否和前面已经摆放的皇后是否冲突
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //说明
            //1.array[i] == array[n] , 表示 判断第n个皇后是否和前面的 n-1 个皇后是否在同一列
            //2.Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i个皇后是否在同一斜线上
            //例如：n = 1 是放在第2列 即 array[1] = 1; n = 0 如果是放在第一列，那么根据函数关系 y=x，横坐标差值等于纵坐标差值则表明是在同一斜线上，Math.abs()是用来取绝对值的，因为可能存在 y=-x的情况
            //3.判断是否在同一行，没有必要，因为 n 每次都在递增
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个print方法，如果成功可以进行打印
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}

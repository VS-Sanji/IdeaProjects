package cn.itbaizhan;

import java.util.concurrent.atomic.AtomicInteger;

public class TestFor {
    public static void main(String[] args) {
        int a = 0;         //初始化
        while (a <= 3) {    //条件判断
            System.out.println("我爱你！！！");  //循环体
            a++;  //迭代因子
        }

        for (int b = 0; b <= 3; b++) {
            System.out.println("我爱你！！！");
        }

        //死循环1
        /*
        while (true){
            System.out.println("詹辉尼玛死了！！！");
        }

         */
        //死循环2
        //for (; ; ) {
        //    System.out.println("詹辉尼玛死了？？？");
        //}


        for (int i = 100; i <= 100 && i > 0; i--) {
            System.out.println(i);
        }
    }
}

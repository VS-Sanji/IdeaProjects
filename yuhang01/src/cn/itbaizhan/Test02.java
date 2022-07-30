package cn.itbaizhan;

import com.sun.corba.se.impl.interceptors.PICurrent;

public class Test02 {
    public static void main(String[] args) {

        double r = Math.random() * 4;
        double area = Math.PI * r * r;
        double circle = 2 * Math.PI * r;

        System.out.println("半径：" + r);
        System.out.println("面积：" + area);
        System.out.println("周长：" + circle);

        if (circle >= area) {
            System.out.println("周长的数值大于等于面积");
        } else {
            System.out.println("面积的数值大于周长");
        }
    }
}
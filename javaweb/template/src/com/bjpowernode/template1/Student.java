package com.bjpowernode.template1;

public class Student {

    /**
     * 这个方法描述学生的一天
     */
    public void day() {
        //和Teacher的算法相同
        qiChuang();
        xiShu();
        chiZaoCan();
        doSome();
        chiWanCan();
        shuiJiao();
    }

    public void qiChuang() {
        System.out.println("起床");
    }

    public void xiShu() {
        System.out.println("洗漱");
    }

    public void chiZaoCan() {
        System.out.println("吃早餐");
    }

    public void doSome() {
        System.out.println("上学");
    }

    public void chiWanCan() {
        System.out.println("吃晚餐");
    }

    public void shuiJiao() {
        System.out.println("睡觉");
    }

}

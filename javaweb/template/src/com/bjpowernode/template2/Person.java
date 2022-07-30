package com.bjpowernode.template2;

/**
 * Teacher和Student都是Person
 * 1。Person就是模板方法设计模式中模板类
 * 2.day()方法就是模板方法设计模式中的模板方法
 */
public abstract class Person {
    /**
     * 模板方法
     * 添加final之后，这个方法无法被覆盖，这样核心的算法也可以得到保护
     * 模板方法定义核心的算法骨架，具体的实现步骤可以延迟到子类当中去实现
     * 核心算法一方面是得到了保护，不能被改变。另外一方面就是算法得到了重复使用
     * 另外代码也得到了复用，因为算法中某些步骤的代码是固定的，这些固定的代码不会随着子类的变化而变化，这一部分代码就可以写到模板类中
     */
    public final void day() {
        qiChuang();
        xiShu();
        chiZaoCan();
        doSome();
        chiWanCan();
        shuiJiao();
    }

    //其中某些步骤，不会随着子类的变化而变化，这些代码可以写到父类中，得到代码复用
    public void qiChuang() {
        System.out.println("起床");
    }

    public void xiShu() {
        System.out.println("洗漱");
    }

    public void chiZaoCan() {
        System.out.println("吃早餐");
    }

    //这一步是要做，但是具体怎么做，子类说了算
    public abstract void doSome();

    public void chiWanCan() {
        System.out.println("吃晚餐");
    }

    public void shuiJiao() {
        System.out.println("睡觉");
    }


}

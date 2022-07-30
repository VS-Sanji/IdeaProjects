package com.bjpowernode.javaweb.servlet.adapter;

/**
 * 对于Customer来说，可能m2方法是最主要的方法
 */
public class Customer implements MyInterface {
    @Override
    public void m1() {

    }

    //可能对于Customer来说，m2方法是最主要的方法
    @Override
    public void m2() {
        System.out.println("Customer's m2 execute!!");
    }

    @Override
    public void m3() {

    }

    @Override
    public void m4() {

    }

    @Override
    public void m5() {

    }

    @Override
    public void core() {

    }
}

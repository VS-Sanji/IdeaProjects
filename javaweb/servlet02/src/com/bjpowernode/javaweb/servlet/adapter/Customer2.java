package com.bjpowernode.javaweb.servlet.adapter;

/**
 * 对于Customer2来说，可能m2方法是最主要的方法
 */
public class Customer2 implements MyInterface {
    @Override
    public void m1() {

    }

    //对于Customer2来说，m2是最主要的方法
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

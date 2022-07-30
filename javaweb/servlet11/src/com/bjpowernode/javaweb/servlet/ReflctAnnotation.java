package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.annotation.WebServlet;

public class ReflctAnnotation {
    public static void main(String[] args) throws Exception {

        //使用反射机制将类上面的注解进行解析
        //获取类Class对象
        Class<?> aClass = Class.forName("com.bjpowernode.javaweb.servlet.WelcomeServlet");

        //获取这个类上面的注解
        //首先判断这个类上面有没有注解对象，如果有，就获取注解对象
        if (aClass.isAnnotationPresent(WebServlet.class)) {
            //获取注解对象
            WebServlet webServletAnnotation = aClass.getAnnotation(WebServlet.class);
            //获取注解的value属性值
            String[] value = webServletAnnotation.value();
            for (int i = 0; i < value.length; i++) {
                System.out.println(value[i]);
            }
        }

    }
}

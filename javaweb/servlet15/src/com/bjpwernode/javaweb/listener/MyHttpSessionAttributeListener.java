package com.bjpwernode.javaweb.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    //添加
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("session data add");
    }

    @Override
    //删除
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("session data remove");
    }

    @Override
    //替换/修改
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("session data replace");
    }
}

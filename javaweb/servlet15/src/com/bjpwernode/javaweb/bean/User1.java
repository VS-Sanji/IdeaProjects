package com.bjpwernode.javaweb.bean;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

//这个监听器不需要添加注解即可使用

/**
 * 普通的java类，但是它实现了HttpSessionBindingListener监听器接口
 * 这个监听器的功能主要监听的是 User1 这个特定的类，这个类型的数据一旦往session中存放就会触发
 */
public class User1 implements HttpSessionBindingListener {
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("绑定数据");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("数据解绑");
    }


    private String username;
    private String password;

    public User1(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User1() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

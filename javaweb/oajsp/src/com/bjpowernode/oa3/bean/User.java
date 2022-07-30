package com.bjpowernode.oa3.bean;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

import java.util.Objects;

public class User implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        //用户登录了
        //User类型对象往session域中存放了，则会触发这个方法
        //所以统计已登录的在线人数可以在这个方法里面写，进行更新
        //获取ServletContext对象，在线人数数据会放到ServletContext域中
        ServletContext application = event.getSession().getServletContext();
        //获取在线人数
        Object onelinecount = application.getAttribute("onelinecount");
        if (onelinecount == null) {
            application.setAttribute("onlinecount", 1);
        } else {
            int count = (Integer) onelinecount;
            count++;
            application.setAttribute("onlinecount", count);
        }


    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        //用户退出了
        //User类型对象从session域中删除了
        ServletContext application = event.getSession().getServletContext();
        Integer onlinecount = (Integer) application.getAttribute("onlinecount");
        onlinecount--;
        application.setAttribute("onlinecount", onlinecount);

    }

    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}

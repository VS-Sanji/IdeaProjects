package com.bjpowernode.javaweb.servlet.servlet;

import jakarta.servlet.*;

import java.io.IOException;

public class AServlet implements Servlet {

    //无参构造方法
    public AServlet() {
        System.out.println("a构造方法执行！！");

    }


    //init方法在创建完对象之后马上执行，且只执行一次
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("a的init方法执行了！！");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("a的service方法执行了");
    }

    //destroy方法执行的时候，对象还没有被销毁
    //destroy方法在服务器关闭的时候执行
    @Override
    public void destroy() {
        System.out.println("a的destroy方法执行了！！");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }
}

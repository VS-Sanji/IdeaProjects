package com.bjpowernode.javaweb.servlet.servlet;

import jakarta.servlet.*;

import java.io.IOException;

public class BServlet implements Servlet {

    //无参构造方法
    public BServlet() {
        System.out.println("b构造方法执行！！");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

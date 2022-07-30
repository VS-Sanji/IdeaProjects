package com.bjpowernode.javaweb.servlet.servlet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

/**
 * 这个是内置的GenericServlet，跟我们所分析的是类似的，所以以后我们不需要自己写适配器了
 */
public class UserServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    /**
     * 可以直接重写init方法了
     */
    @Override
    public void init() throws ServletException {
        System.out.println("重写了init方法，是事先写好的");
    }
}

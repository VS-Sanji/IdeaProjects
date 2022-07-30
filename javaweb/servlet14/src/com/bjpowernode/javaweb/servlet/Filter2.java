package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;

public class Filter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //在请求的时候添加过滤规则
        System.out.println("Filter2");

        //执行下一个过滤器，如果下一个不是过滤器了，则执行目标程序Servlet
        //向下走。没有它是不行的，要想执行到目标程序，需要加这一行代码
        chain.doFilter(request, response);

        //在响应的时候添加过滤规则
        System.out.println("Filter2");
    }


    @Override
    public void destroy() {
    }
}

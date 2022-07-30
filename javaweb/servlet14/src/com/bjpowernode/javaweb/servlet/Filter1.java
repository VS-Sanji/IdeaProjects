package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter("/abc")
//@WebFilter("/a.do")
//这两个请求路径都走这个过滤器
//@WebFilter({"/a.do","/b.do"})

//以下这个路径属于模糊匹配中的扩展匹配。以*开始，注意这个路径不要以/开始
//@WebFilter("*.do")

//属于前缀匹配
//@WebFilter("/dept/*")

//匹配所有路径
//@WebFilter("/*")
public class Filter1 implements Filter {

    //无参构造
    public Filter1() {
        System.out.println("无参构造执行");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init方法执行");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //在请求的时候添加过滤规则
        System.out.println("doFilter方法执行了");

        //执行下一个过滤器，如果下一个不是过滤器了，则执行目标程序Servlet
        //向下走。没有它是不行的，要想执行到目标程序，需要加这一行代码
        chain.doFilter(request, response);

        //在响应的时候添加过滤规则
        System.out.println("doFilter方法执行结束");
    }

    @Override
    public void destroy() {
        System.out.println("destroy方法执行");
    }
}

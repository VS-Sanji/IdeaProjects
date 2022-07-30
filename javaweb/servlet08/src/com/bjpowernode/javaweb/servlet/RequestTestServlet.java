package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class RequestTestServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(request);//org.apache.catalina.connector.RequestFacade@bbb67dc

        //获取客户端的IP地址
        String remoteAddr = request.getRemoteAddr();
        out.print(remoteAddr);//org.apache.catalina.connector.RequestFacade@62f7667d127.0.0.1
        System.out.println(remoteAddr);//127.0.0.1

        //获取应用的根路径，用的较多，可以动态的获取应用根路径
        String contextPath = request.getContextPath();
        System.out.println("应用的根路径" + contextPath);//应用的根路径/servlet08

        //获取请求方式
        String method = request.getMethod();
        System.out.println(method);//GET

        //获取请求的URI
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);// /servlet08/request

        //获取servlet path
        String servletPath = request.getServletPath();
        System.out.println(servletPath);// /request

    }
}

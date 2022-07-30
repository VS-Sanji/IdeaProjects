package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从请求域当中取出绑定的数据
        Object sysTime = request.getAttribute("sysTime");

        //输出到控制台
        System.out.println(sysTime);//null直接取是取不到的
        //可以使用转发机制
    }
}

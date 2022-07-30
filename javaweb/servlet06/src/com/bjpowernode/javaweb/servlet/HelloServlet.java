package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
    //通过无参数构造方法会创建对象
//    public Helloservlet() {
//    }

    //没有提供inti方法，那么必然会执行父类HttpServlet的init方法
    //HttpServlet类中没有init方法，会继续执行父类GenericServlet类中的init方法

    //没有提供service方法，那么必然会执行父类HttpServlet的service方法

    //当前端发送的请求是get请求的时候，我这里重写doGet方法
    //如果重写doGet方法，而没有重写doPost方法，且发送的又是post请求的时候，就给前端报405错误
    //这是因为子类没有重写doPost方法，那么前端发送一个post请求过来的时候就会去执行父类的doPost方法，父类的doPost方法就给前端报错
    //这样就达到了一个目的，即后端要求一个get请求，前端必须发送的是get请求才响应
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.print("<h1>doGet</h1>");
    }

    //当前端发送的请求是post请求的时候，我这里重写doPost方法
    //如果重写doPost方法，而没有重写doGet方法，且发送的又是get请求的时候，就给前端报405错误
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.print("<h1>doPost</h1>");
    }
}

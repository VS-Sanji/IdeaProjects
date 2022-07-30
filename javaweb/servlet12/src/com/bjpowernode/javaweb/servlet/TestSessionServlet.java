package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/session")
public class TestSessionServlet extends HttpServlet {
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request和session都是服务器端的java对象，都在JVM中
        //request对象代表了一次请求。（一次请求对应一个request对象。两次请求就会对应两个不同的request对象）
        //session对象代表了一次会话。（一次会话对应一个session对象）
        //获取session对象
        //从服务器中获取当前的session对象。如果没有获取到任何session对象，则新建
        HttpSession session = request.getSession();
        //从服务器中获取当前session对象，如果获取不到session，则不会新建，返回一个null
        //HttpSession session = request.getSession(false);

        //向会话域中存数据
        //session.setAttribute();
        //从会话域中取数据
        //session.getAttribute();

        //输出到浏览器上
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("会话对象:" + session);//会话对象:org.apache.catalina.session.StandardSessionFacade@4cfb0b68
        //Cookie JSESSIONID=8F492DC72C13B843B813526E2DDF075F
    }

}

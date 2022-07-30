package com.bjpwernode.javaweb.servlet;

import com.bjpwernode.javaweb.bean.User1;
import com.bjpwernode.javaweb.bean.User2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/session/bind")
public class HttpSessionBindingListenerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();

        //准备两个对象
        User1 user1 = new User1("zhangsan", "zhangsan");
        User2 user2 = new User2("lisi", "lisi");

        //将user1存到session域中
        session.setAttribute("user1", user1);
        //从session中移除user1
        session.removeAttribute("user1");


        //将user2存到session域中
        session.setAttribute("user2", user2);
    }
}

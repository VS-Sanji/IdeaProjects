package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

public class ConfigTestServlet2 extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //获取ServletConfig对象
        ServletConfig config = this.getServletConfig();
        //输出该对象
        out.print("ServletConfig对象是：" + config);
        /**
         * ServletConfig对象是：org.apache.catalina.core.StandardWrapperFacade@4c37652c
         * 与ConfigTestServlet中输出的是同一个对象
         */
    }
}

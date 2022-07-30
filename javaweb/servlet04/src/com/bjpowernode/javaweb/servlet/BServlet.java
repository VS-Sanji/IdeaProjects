package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

public class BServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //获取ServletContext对象
        ServletContext servletContext1 = this.getServletContext();
        out.print(servletContext1);//org.apache.catalina.core.ApplicationContextFacade@6e5f2360

    }
}

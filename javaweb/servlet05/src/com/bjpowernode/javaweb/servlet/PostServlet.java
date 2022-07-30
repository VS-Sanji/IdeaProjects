package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class PostServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        //响应一些东西到浏览器上
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //out.println() 是输出信息到浏览器，最终源代码中换行。（不是网页中换行，网页中换行必须使用<br>标签）
        out.println("<!DOCTYPE>");
        out.println("<html>");
        out.println("	<head>");
        out.println("		<title>my first htmlpage!</title>");
        out.println("	</head>");
        out.println("	<body>");
        out.println("		<h1>MY FIRST HTMLPAGE!!!</h1>");
        out.println("	</body>");
        out.println("</html>");

    }
}

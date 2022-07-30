package com.bjpowernode.oa3.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 什么情况下不能拦截？
 * 目前写的路径是：/* 表示拦截所有请求
 * <p>
 * 用户访问index.jsp 不能拦截
 * 用户登录 不能拦截
 * 用户已经登录了，不能拦截
 * WelcomeServlet 验证是否十天免登录也不能拦截
 */

public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //强转
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();
        //验证用户是否登录，登录才执行service方法中的逻辑代码
        HttpSession session = request.getSession(false);
        //在访问欢迎页面（登录页面）的时候，JSP的九大内置对象已经新建了session对象，所以session可能不是null，但是加上后面的用户名判断就保证了session不是null也能正确处理
        //因为只有登录成功的才能往session域中存用户数据，有用户数据就说明登录了
    /*    if ("/index.jsp".equals(servletPath) || "/user/login".equals(servletPath)
                || "/user/exit".equals(servletPath) || "/welcome".equals(servletPath)
                || (session != null && session.getAttribute("username") != null)) {
            chain.doFilter(request,response);*/

        if ("/index.jsp".equals(servletPath) || "/user/login".equals(servletPath)
                || "/user/exit".equals(servletPath) || "/welcome".equals(servletPath)
                || (session != null && session.getAttribute("user") != null)) {
            chain.doFilter(request, response);
        } else {
            //未登录，跳转登录页面
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }


    }
}

package com.bjpowernode.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class StudentServlet implements Servlet {

    //无参构造方法
    //不建议程序员手动添加有参构造方法，而没有添加有参构造方法，则有可能导致对象无法被实例化
    public StudentServlet() {

    }

    //init方法被翻译为初始化
    //init方法只执行一次
    //在StudentServlet对象第一次被创建之后执行
    //init方法通常是完成初始化操作的
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    //service方法，是处理用户请求的核心方法
    //只要用户发送一次请求，service方法就执行一次，发送一百次请求则执行一百次
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        //设置响应的内容类型
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode", "root", "123456");
            //获取预编译的数据库操作对象
            String sql = "select id, username, password from t_user";
            ps = conn.prepareStatement(sql);
            //执行sql语句
            rs = ps.executeQuery();
            //处理查询结果集
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("username");
                int password = rs.getInt("password");

                out.print(id + name + password + "<br>");


            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //destroy方法也是只执行一次
    //Tomcat服务器在销毁StudentServlet对象之前会调用一次destroy方法
    //destruy方法在执行的时候，对象的内存还没有被销毁，即将被销毁
    //destroy方法中可以编写销毁前的准备
    //比如说，服务器关闭的时候，对象开启了一些资源，这些资源可能是流，可能是数据库连接
    //那么，关闭服务器的时候，要关闭这些流、关闭数据库连接，那么这些关闭资源的代码就可以写到destroy方法中
    @Override
    public void destroy() {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }


}

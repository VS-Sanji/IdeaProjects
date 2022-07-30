package com.bjpowernode.oa.web.action;

import com.bjpowernode.oa.utils.DButil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("    <head>");
        out.print("        <meta charset='utf-8'>");
        out.print("        <title>详情页面</title>");
        out.print("    </head>");
        out.print("    <body>");

        //要访问部门详情，首先要获取部门编号
        //  /oa/dept/detail?deptno=1
        //这个部门编号得看超链接中写的是什么，通过name获取value
        String deptno = request.getParameter("deptno");

        //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = DButil.getConnection();

            String sql = "select dname, loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            if (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("                    部门编号：" + deptno + "<br>");
                out.print("                    部门名称：" + dname + "<br>");
                out.print("                    部门位置：" + loc + "<br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.print("        <input type='button' value='后退' onclick='window.history.back()' />");
        out.print("    </body>");
        out.print("</html>");
    }
}

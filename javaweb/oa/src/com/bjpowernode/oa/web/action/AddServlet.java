package com.bjpowernode.oa.web.action;

import com.bjpowernode.oa.utils.DButil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取部门信息
        request.setCharacterEncoding("UTF-8");
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        //连接数据库执行insert语句
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DButil.getConnection();

            String sql = "insert into dept(deptno, dname, loc) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            ps.setString(2, dname);
            ps.setString(3, loc);

            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, null);
        }
        if (count == 1) {
            //保存成功跳转到列表页面
            //但是这里跳转到DeptListServlet会有405错误，这是因为前端发送过来是个post请求，这里转发也是post请求，可是在DeptListServlet中只有doGet方法，
            // 为了避免405错误，可以在DeptListServlet中将doPost请求也写上，在doPost请求中调用doGet请求
//            request.getRequestDispatcher("/dept/list").forward(request,response);

            //使用重定向解决这个问题
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            //保存失败
            request.getRequestDispatcher("/error.html").forward(request, response);

        }

    }
}

package com.bjpowernode.oa.web.action;

import com.bjpowernode.oa.utils.DButil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String deptno = request.getParameter("deptno");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = DButil.getConnection();
            //获取预编译的数据库操作对象
            String sql = "delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            //执行sql语句
            int count = ps.executeUpdate();
            //判断删除成功还是删除失败
            if (count == 1) {
                //删除成功，仍然跳转到部门页面列表
                //部门列表页面的显示需要执行另一个Servlet，可以通过转发来实现
                request.getRequestDispatcher("/dept/list").forward(request, response);
            } else {
                request.getRequestDispatcher("/error.html").forward(request, response);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, rs);
        }
    }
}

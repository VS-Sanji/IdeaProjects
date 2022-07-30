package com.bjpowernode.oa.web.action;

import com.bjpowernode.oa.utils.DButil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String deptno = request.getParameter("deptno");
        String contextPath = request.getContextPath();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>修改页面</title>");
        out.print("	</head>");
        out.print("	<body>");
        out.print("		<form action='" + contextPath + "/dept/modify' method='post'>");
        /*以上是固定死的*/


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DButil.getConnection();

            String sql = "select dname, loc as location from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            if (rs.next()) {
                String dname = rs.getString("dname");
                String location = rs.getString("location");//如果sql语句中进行了起别名操作，这里想拿数据就得用别名来获取
                out.print("                部门编号：<input type='text' name='deptno' value='" + deptno + "' readonly/><br>");
                out.print("                部门名称：<input type='text' name='dname' value='" + dname + "'/><br>");
                out.print("                部门位置：<input type='text' name='loc' value='" + location + "'/><br>");
            }
            /*以下也是固定死的*/
            out.print("			<input type='submit' value='修改'/>");
            out.print("		</form>");
            out.print("	</body>");
            out.print("</html>");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, rs);
        }


    }
}

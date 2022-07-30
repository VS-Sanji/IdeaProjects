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

public class DeptListServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request,response);
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置响应的字符集，防止中文乱码问题
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //获取项目名，根路径
        String contextPath = request.getContextPath();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("    <head>");
        out.print("        <meta charset='utf-8'>");
        out.print("        <title>部门列表页面</title>");

        out.print("<script type='text/javascript'>");
        out.print("        function del(dno){");
        out.print("            if (window.confirm('亲，删了不可恢复哦')){");
        out.print("                document.location.href = '/oa/dept/delete?deptno=' + dno");
        out.print("            }");
        out.print("        }");
        out.print("</script>");

        out.print("    </head>");
        out.print("    <body>");
        out.print("        <h1 align='center'>部门列表</h1>");
        out.print("        <hr >");
        out.print("        <table border='1px' align='center' width='50%'>");
        out.print("            <tr>");
        out.print("                <th>序号</th>");
        out.print("                <th>部门编号</th>");
        out.print("                <th>部门名称</th>");
        out.print("                <th>操作</th>");
        out.print("            </tr>");


        //连接数据库，查询所有的部门
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = DButil.getConnection();
            //获取预编译的数据库操作对象
            String sql = "select deptno, dname, loc from dept";
            ps = conn.prepareStatement(sql);
            //执行sql语句
            rs = ps.executeQuery();
            //处理查询结果集
            while (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("            <tr>");
                out.print("                <td>" + deptno + "</td>");
                out.print("                <td>" + dname + "</td>");
                out.print("                <td>" + loc + "</td>");
                out.print("                <td>");
                /*javascript:void(0),这段代码目的是保持超链接的样子，但是不进行跳转*/
                out.print("                    <a href='javascript:void(0)' onclick='del(" + deptno + ")'>删除</a>");
                out.print("                    <a href='" + contextPath + "/dept/edit?deptno=" + deptno + "' >修改</a>");
                //我们在路径中 要加上项目名 ，所以想到调用 request对象的 getContextPath()方法来获取项目名，即根路径
                //同时我们要知道，用户查询的是某一个部门的详情，所以部门编号也需要是动态的
                //这里又跳转到下一个页面，显然我们需要去配置一个新的<servlet>，编写一个新的servlet类，来响应这个请求
                out.print("                    <a href='" + contextPath + "/dept/detail?deptno=" + deptno + "'>详情</a>");
                out.print("                </td>");
                out.print("            </tr>");


            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, rs);
        }


        out.print("        </table>");
        out.print("        <hr >");
        out.print("        <a href='" + contextPath + "/add.html'>新增部门</a>");
        out.print("    </body>");
        out.print("</html>");


    }
}

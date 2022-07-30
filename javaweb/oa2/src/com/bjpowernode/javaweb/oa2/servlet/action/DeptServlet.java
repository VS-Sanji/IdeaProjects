package com.bjpowernode.javaweb.oa2.servlet.action;

import com.bjpowernode.javaweb.oa2.servlet.utils.DButil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 解决类爆炸问题
 */

@WebServlet({"/dept/list", "/dept/add", "/dept/edit", "/dept/detail", "/dept/modify", "/dept/delete"})
//模糊匹配
//@WebServlet("/dept/*")
public class DeptServlet extends HttpServlet {
    //模板方法
    //重写service方法 (并没有重写doGet或者doPost方法）


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取servlet path
        String servletPath = request.getServletPath();

        if ("/dept/list".equals(servletPath)) {
            doList(request, response);
        } else if ("/dept/add".equals(servletPath)) {
            doAdd(request, response);
        } else if ("/dept/edit".equals(servletPath)) {
            doEdit(request, response);
        } else if ("/dept/detail".equals(servletPath)) {
            doDetail(request, response);
        } else if ("/dept/modify".equals(servletPath)) {
            doModify(request, response);
        } else if ("/dept/delete".equals(servletPath)) {
            doDel(request, response);
        }
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    private void doModify(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DButil.getConnection();
            String sql = "update dept set dname = ?, loc = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3, deptno);
            int count = ps.executeUpdate();
            if (count == 1) {
                request.getRequestDispatcher("/dept/list").forward(request, response);
            } else {
                request.getRequestDispatcher("/error.html").forward(request, response);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {

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

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        out.print("                document.location.href = '/oa2/dept/delete?deptno=' + dno");
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

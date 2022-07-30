package com.bjpowernode.oa3.action;

import com.bjpowernode.oa3.utils.DButil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


/**
 * 解决类爆炸问题
 */

@WebServlet({"/dept/list", "/dept/add", "/dept/edit", "/dept/detail", "/dept/modify", "/dept/delete"})
//模糊匹配
//@WebServlet("/dept/*")
public class DeptServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证用户是否登录，登录才执行service方法中的逻辑代码

        HttpSession session = request.getSession(false);

        //在访问欢迎页面（登录页面）的时候，JSP的九大内置对象已经新建了session对象，所以session可能不是null，但是加上后面的用户名判断就保证了session不是null也能正确处理
        //因为只有登录成功的才能往session域中存用户数据，有用户数据就说明登录了

        if (session != null && session.getAttribute("username") != null) {
            String servletPath = request.getServletPath();
            if ("/dept/list".equals(servletPath)) {
                doList(request, response);
            } else if ("/dept/add".equals(servletPath)) {
                doAdd(request, response);
            } else if ("/dept/detail".equals(servletPath)) {
                doDetail(request, response);
            } else if ("/dept/modify".equals(servletPath)) {
                doModify(request, response);
            } else if ("/dept/delete".equals(servletPath)) {
                doDel(request, response);
            } else if ("/dept/edit".equals(servletPath)) {
                doEdit(request, response);
            }

        } else {
            //未登录，跳转登录页面
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }


    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //点击修改，先查询数据库数据，再又jsp响应到浏览器上
        String deptno = request.getParameter("deptno");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnection();
            String sql = "select dname, loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            if (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                request.setAttribute("deptno", deptno);
                request.setAttribute("dname", dname);
                request.setAttribute("loc", loc);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, rs);
        }
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String deptno = request.getParameter("deptno");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {

            conn = DButil.getConnection();
            String sql = "delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, null);
        }
        if (count == 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }

    }

    private void doModify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //首先拿到修改页面发送过来的数据
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        //连接数据库修改数据
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DButil.getConnection();
            String sql = "update dept set dname = ?, loc = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3, deptno);
            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, null);
        }
        if (count == 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }

    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取部门详情，需要连接数据库获取信息
        //首先需要拿到前端传过来的部门编号
        String deptno = request.getParameter("deptno");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnection();
            String sql = "select dname, loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            if (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                request.setAttribute("deptno", deptno);
                request.setAttribute("dname", dname);
                request.setAttribute("loc", loc);
                request.getRequestDispatcher("/detail.jsp").forward(request, response);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //连接数据库，添加数据
        //获取数据
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DButil.getConnection();
            String sql = "insert into dept(deptno, dname, loc) values(?, ?, ?)";
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
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }

    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建一个数组，用来存放Dept对象
        List<Dept> depts = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnection();
            String sql = "select deptno, dname, loc from dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                Dept dept = new Dept(deptno, dname, loc);
                depts.add(dept);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, rs);
        }
        //将数组集合存储到请求域中，以便转发给JSP进行获取和展示
        request.setAttribute("depts", depts);
        //将请求进行转发，将数据共享给JSP
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }
}

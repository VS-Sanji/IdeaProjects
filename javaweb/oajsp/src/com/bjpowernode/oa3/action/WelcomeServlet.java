package com.bjpowernode.oa3.action;

import com.bjpowernode.oa3.bean.User;
import com.bjpowernode.oa3.utils.DButil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //访问该服务器先来到欢迎页对应的Servlet执行判断，是否是选择了十天内免登录功能，是则直接进入到列表页面；否则进入登录界面

        //不管浏览器有没有携带cookie，先拿到cookie（可能为null）如果不是null，数组长度一定大于0
        Cookie[] cookies = request.getCookies();


        String username = null;
        String password = null;
        //判断是否为空
        if (cookies != null) {
            //不是空则遍历
            for (Cookie cookie : cookies) {
                //获取name,仍然需要判断这个cookie是不是验证登录所需要的 保存了 用户名和密码的 cookie，因为有可能有其他的cookie一同被发送过来
                String name = cookie.getName();
                if ("username".equals(name)) {
                    username = cookie.getValue();
                } else if ("password".equals(name)) {
                    password = cookie.getValue();
                }
            }
        }

        //要在这里使用username和password变量
        //布尔标记
        boolean success = false;
        if (username != null && password != null) {
            //连接数据库验证用户名和密码
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                conn = DButil.getConnection();
                String sql = "select * from t_user where username = ? and password = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    success = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DButil.close(conn, ps, rs);
            }
            if (success) {
                //获取session对象（这里的要求是：必须获取到session，没有session也要新建一个session对象,所以参数不能给false）
                HttpSession session = request.getSession();
                //获取session对象之后，将用户信息存到会话域中，以此来表示以登录的用户
                //session.setAttribute("username", username);

                //将用户信息封装到对象中
                User user = new User(username, password);
                session.setAttribute("user", user);

                //正确，表示登录成功，直接跳转到列表页面
                response.sendRedirect(request.getContextPath() + "/dept/list");
            } else {
                //错误，表示登录失败，则跳转到登录界面，让用户登录
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } else {
            //空，表示登录失败，则跳转到登录界面，让用户登录
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}

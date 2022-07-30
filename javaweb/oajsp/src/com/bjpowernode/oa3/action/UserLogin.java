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

@WebServlet({"/user/login", "/user/exit"})
public class UserLogin extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求路径，判断执行什么servlet业务
        String servletPath = request.getServletPath();
        if ("/user/login".equals(servletPath)) {
            doLogin(request, response);
        } else if ("/user/exit".equals(servletPath)) {
            doExit(request, response);
        }
    }

    private void doExit(HttpServletRequest request, HttpServletResponse response) {
        //安全退出，销毁session和cookie，下次访问需要重新登录
        //获取session对象，销毁session
        HttpSession session = request.getSession(false);
        if (session != null) {
            //删除user对象
            session.removeAttribute("user");

            //手动销毁session对象
            session.invalidate();
        }

        //退出系统将所有的cookie全部销毁
        //获取cookies
        Cookie[] cookies = request.getCookies();
        //循环遍历，销毁cookie
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                //拿到cookie的name
                String name = cookie.getName();
                //核对是否是保存了用户名和密码的cookie
                if ("username".equals(name) || "password".equals(name)) {
                    //设置cookie的有效期为0，表示删除该cookie
                    cookie.setMaxAge(0);
                    //设置cookie的路径（这个要注意，想要之前的cookie失效，这个路径需要和当时设置的相同，因为等会重新响应给浏览器才能达到覆盖的效果）
                    cookie.setPath(request.getContextPath());
                    //响应cookie给浏览器，覆盖之前的cookie，且现在的cookie有效期为0，即达成删除cookie的效果
                    response.addCookie(cookie);

                }
            }
        }
    }


    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //布尔标记
        boolean success = false;

        //需要在这个Servlet中验证用户名和密码是否正确
        //获取登录信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //连接数据库进行验证
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
            //这里还可以用布尔标记的写法
            if (rs.next()) {
                //能执行到这说明查到了对应的用户，可以登录
                success = true;
            }
//            if (rs.next()) {
//                //能执行到这说明查到了对应的用户，可以登录，跳转到部门列表页面
//                response.sendRedirect(request.getContextPath() + "/dept/list");
//            }else {
//                //执行到这说明没有查到对应的用户，登录失败，跳转到失败页面
//                response.sendRedirect(request.getContextPath() + "/login.jsp");
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, rs);
        }

        //登录成功或者失败
        if (success) {
            //获取session对象（这里的要求是：必须获取到session，没有session也要新建一个session对象,所以参数不能给false）
            HttpSession session = request.getSession();
            //获取session对象之后，将用户信息存到会话域中，以此来表示以登录的用户
            //session.setAttribute("username",username);

            //将用户信息封装到对象中，不直接把用户名，密码存到session域中
            User user = new User(username, password);
            session.setAttribute("user", user);

            //登录成功了，并且用户确实选择了十天内免登录功能，可以创建cookie对象
            String f = request.getParameter("f");
            if ("1".equals(f)) {
                //判断前端发送过来的f的值是否是1，是1则表示选择了 十天内免登录功能 （这一块由前端代码的 name 和 value 确定的）
                //创建Cookie对象,存储登录名和密码
                Cookie cookie = new Cookie("username", username);
                Cookie cookie2 = new Cookie("password", password);//真实开发会加密

                //设置cookie的有效时间
                cookie.setMaxAge(60 * 60 * 24 * 10);
                cookie2.setMaxAge(60 * 60 * 24 * 10);

                //设置cookie的路径（只要访问这个应用，浏览器就一定要携带这两个cookie）
                cookie.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());

                //响应给浏览器
                response.addCookie(cookie);
                response.addCookie(cookie2);
            }


            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/loginError.jsp");
        }

    }
}

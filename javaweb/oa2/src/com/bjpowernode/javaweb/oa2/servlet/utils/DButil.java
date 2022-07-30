package com.bjpowernode.javaweb.oa2.servlet.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * JDBC工具类
 */
public class DButil {
    //静态变量：在类加载时执行
    //并且是有顺序的，自上而下的顺序
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.jdbc");
    //根据属性文件的key获取value
    private static String driver = resourceBundle.getString("driver");
    private static String url = resourceBundle.getString("url");
    private static String user = resourceBundle.getString("user");
    private static String password = resourceBundle.getString("password");


    static {
        //注册驱动 只需要注册一次，所以放在静态代码块中，DBUtil类加载的时候执行
        try {
            //"com.mysql.jdbc.Driver"是连接数据库的驱动，不适合直接写死，因为以后可能还会连接Oracle数据库
            //如果连接oracle数据库的时候，还需要修改java代码，显然违背了OCP原则
            //OCP开闭原则：对扩展开放，对修改关闭。（什么是符合OCP呢？在进行功能扩展的时候，不需要修改java源代码）
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
        //获取链接
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * 释放资源
     *
     * @param conn 连接对象
     * @param ps   数据库操作对象
     * @param rs   结果集对象
     */
    public static void close(Connection conn, Statement ps, ResultSet rs) {
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

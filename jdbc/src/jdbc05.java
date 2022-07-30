import java.sql.*;
import java.util.Scanner;

public class jdbc05 {
    public static void main(String[] args) {
        //演示利用sql注入进行升序降序
        //用户在控制台输入desc表示降序，asc表示升序
        Scanner sc = new Scanner(System.in);
        System.out.println("输入desc或者asc，desc表示降序，asc表示升序");
        String keywords = sc.nextLine();
        /*
        //PreparedStatement
        Connection conn  = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode", "root", "123456");
            //获取预编译的数据库操作对象
            String sql = "select ename form emp order by ename ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,keywords);
            //执行sql语句
            rs = ps.executeQuery();
            //处理查询结果集
            while (rs.next()) {
                System.out.println(rs.getString("ename"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
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
         */

        //Statement
        Connection conn  = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode", "root", "123456");
            //获取数据库操作对象
            stmt = conn.createStatement();
            //执行sql语句
            String sql = "select ename from emp order by ename " + keywords;
            rs = stmt.executeQuery(sql);
            //处理查询结果集
            while (rs.next()) {
                System.out.println(rs.getString("ename"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
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
}

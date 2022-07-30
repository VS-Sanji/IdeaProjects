package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 测试工具类
 * 实现模糊查询
 */
public class jdbcTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //注册驱动，在加载工具类时完成了
            //获取连接
            DBUtil.getConnection();
            //获取预编译的数据连接对象
            String sql = "select ename from emp where ename like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"A");
            //执行sql语句
            rs = ps.executeQuery();
            //处理查询结果集合
            while (rs.next()) {
                System.out.println(rs.getString("ename"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            DBUtil.close(conn,ps,rs);
        }
    }
}

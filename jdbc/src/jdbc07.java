import java.sql.*;

public class jdbc07 {
    /**
     * JDBC事物机制
     *      1.JDBC中的事务是自动提交的，什么是自动提交？
     *          只要执行任意一条DML语句，则自动提交一次，这是JDBC默认的事务行为。
     *          但是在实际的业务当中，通常都是N条DML语句共同联合才能完成的，必须保证这些DML语句在同一个事务中同时成功或者同时失败
     *      2.重点三行代码
     *          conn.setAutoCommit(false)
     *          conn.commit();
     *          conn.rollback();
     */
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode", "root", "123456");
            //关闭事务自动提交，启用手动提交
            conn.setAutoCommit(false);

            String sql = "update t_act set balance = ? where id = ?";
            ps = conn.prepareStatement(sql);

            //模拟转账，111账户修改为10000，222修改为10000才正确，两个语句要同时成功才行,而jdbc是自动提交事务的，执行一条提交一条，这样不安全，我们可以手动提交
            //给？传值
            ps.setDouble(1,10000);
            ps.setInt(2,111);
            int count = ps.executeUpdate();

            //给？传值
            ps.setDouble(1,10000);
            ps.setInt(2,222);
            count += ps.executeUpdate();

            //执行sql
            System.out.println(count == 2 ? "转账成功" : "转账失败");

            //程序能够执行到这里说明以上程序没有异常，事务结束，手动提交数据
            conn.commit();

        } catch (Exception e) {
            //事务失败，则回滚事务
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
    }
}

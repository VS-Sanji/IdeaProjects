import java.sql.*;

public class jdbc06 {
    /**
     * PreparedStatement完成insert delete update
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
            //获取预编译的数据库操作对象
            /*增
            String sql = "insert into t_user values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,4);
            ps.setString(2,"yuhang");
            ps.setInt(3,123456);
            */
            /*删
            String sql = "delete from t_user where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,2);
             */
            //改
            String sql = "update t_user set username = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"yuhan");
            ps.setInt(2,2);
            //执行sql
            int count = ps.executeUpdate();
            System.out.println(count);

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



    }
}

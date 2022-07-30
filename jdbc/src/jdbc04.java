import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 1.解决SQL注入问题
 *      只要用户提供的信息不参与SQL语句的编译过程，问题就解决了
 *      即使用户提供的信息中含有SQL语句的关键字，但是没有参与编译，不起作用
 *      要想用户信息不参与SQL语句的编译，那么必须使用java.sql.PreparedStatement
 *      PreparedStatement接口继承了java.sql.Statement
 *      PreparedStatement是属于预编译的数据库操作对象
 *      PreparedStatement的原理是：预先对sql语句的框架进行编译，然后再给SQL语句传”值“
 * 2.测试结果
 *      用户名：fals
 *      密码：fals' or '1' = '1
 *      登录失败
 * 3.解决SQL注入的关键是什么
 *      用户提供的信息中即使含有sql语句的关键字，但是这些关键字并没有参与编译，不起作用
 * 4.对比一下Statement和PreparedStatement
 *      - Statement存在sql注入问题，PreparedStatement解决了sql注入问题
 *      - Statement是编译一次执行一次。PreparedStatement是编译一次，可执行n次。后者效率高一些
 *      - PreparedStatement会在编译阶段做类型的安全检查
 *
 *      综上所述，PreparedStatement使用较多，只有极少数的情况下需要使用Statement
 * 5.什么情况下必须使用Statement呢
 *      业务方面要求必须支持sql注入的时候
 *      Statement支持sql注入，凡是业务方面需求是需要进行sql语句拼接的，必须使用Statement
 */
public class jdbc04 {
    public static void main(String[] args) {
        //1.首先考虑初始化一个用户界面，用来接收用户输入信息。考虑写一个方法，这个方法应该有返回值。返回值为用户提交的用户名和密码，这个返回值可以用容器来存储，可以考虑使用Map集合
        Map<String,String> userInfo = initUI();
        //2.验证用户名和密码，写一个验证方法，方法中进行数据库连接查询和比对,返回boolea类型数据，true表示登录成功，false表示登录失败
        boolean loginResult = userLogin(userInfo);
        System.out.println(loginResult ? "登录成功" : "登录失败");
    }

    /**
     * 用户登录
     * @param userInfo 用户登录信息
     * @return true表示登录成功 false表示登录失败
     */
    private static boolean userLogin(Map<String, String> userInfo) {
        //打布尔标记，一开始可以设置为false,后续如果查到了数据，表明有这一个用户，则可改为true，表示登录成功
        boolean loginSuccess = false;
        //编写jdbc代码，进行数据库连接和验证比对
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //第一：注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //第二：获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode", "root", "123456" );

            //第三：获取预编译的数据库操作对象
            //sql语句的框子。其中一个？表示一个占位符，一个？将来接收一个”值“。注意：占位符不能使用单引号括起来
            String sql = "select * from t_user where username = ? and password = ? ";
            //程序执行到此，会发送sql语句框子给DBMS,然后DBMS进行sql语句的预先编译
            ps = conn.prepareStatement(sql);
            //给占位符传值（第1个？下标是1，第2个？下标是2，JDBC中所有下标从1开始）
            ps.setString(1,userInfo.get("username"));//如果是数字，可以调用setInt方法进行传值
            ps.setString(2,userInfo.get("password"));

            //第四：执行sql语句
            rs = ps.executeQuery();

            //第五：处理查询结果集
            if (rs.next()) {
                //登录成功
                loginSuccess = true;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //第六：关闭资源
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

        return loginSuccess;
    }


    /**
     * 初始化用户界面
     * @return Map集合，存储用户登录信息
     */
    private static Map<String,String> initUI() {
        Scanner s = new Scanner(System.in);
        System.out.println("用户名");
        String username = s.nextLine();
        System.out.println("密码");
        String password = s.nextLine();
        Map<String,String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("username", username);
        userLoginInfo.put("password", password);
        return userLoginInfo;
    }
}

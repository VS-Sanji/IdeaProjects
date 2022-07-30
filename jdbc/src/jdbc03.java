import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class jdbc03 {
    /**
     * 登录代码
     * 业务描述：程序运行的时候提供一个输入的入口，可以让用户输入用户名和密码，输完之后提交信息，java程序收集信息
     * java程序连接数据库验证用户名和密码是否合法，合法则登录成功，不合法则登录失败
     *
     * 当前程序存在一个漏洞：
     *      用户名：fdal
     *      密码：fdal‘ or ’1‘ = ‘1
     *      登录成功
     *      这种现象称为SQL注入。（黑客经常使用）
     * 该现象出现的根本原因：用户输入的信息中含有sql语句的关键字，并且这些关键字参与了sql语句的编译过程，导致sql语句的原意被扭曲，进而达到SQL注入
     */
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
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //第一：注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //第二：获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode", "root", "123456" );
            //第三：获取数据库操作对象
            stmt = conn.createStatement();
            //第四：执行sql语句
            String sql = "select * from t_user where username = '"+userInfo.get("username")+"' and password = '"+userInfo.get("password")+"'";
            //以上正好完成了sql语句的拼接，以下代码的含义是发送sql语句给DBMS，DBMS进行sql编译
            //就可能将用户提供的非法信息编译进去，导致原来sql语句的含义被扭曲了
            rs = stmt.executeQuery(sql);
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

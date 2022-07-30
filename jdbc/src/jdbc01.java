import java.sql.*;
/**
 * 1.JDBC (Java DataBase Connectivity) java程序连接数据库
 *   在java语言中编写sql语句，对mysql数据库中的数据进行CRUD操作
 *
 * 2.JDBC是SUN公司制定的一套接口，java程序员与数据库厂商们都遵循这一套协议进行程序的编写，达到解耦合的目的
 *   Java程序们只需要面向JDBC接口编程就可以了
 *
 * 3.java中有关JDBC的类库在 java.sql.*;中
 *
 * 4.JDBC本质上是一堆接口，是SUN公司制定的，它降低了程序的耦合度，提高了扩展力，对于java程序员来说，不需要关心是哪个品牌的数据库，只需要面向接口编程就行了
 *      JDBC整个程序结构中有三波人：
 *          第一波：SUN公司，负责制定JDBC接口，这些接口都已经写好了，在java.sql.*;中
 *
 *          第二波：java.sql.*;下面的所有接口都要有实现类，这些实现类是数据库厂家编写的，我们连接的是mysql数据库，mysql数据库的实现类在
 *          mysql-connector-java-5.1.23-bin.jar 中，jar包中很多 .class字节码文件，这是mysql数据库厂家写的接口实现
 *
 *          注意：如果连接的是oracle数据库，需要从网上下载oracle的jar包
 *
 *          mysql-connector-java-5.1.23-bin.jar 这个jar包有个专业术语叫做 :mysql的驱动
 *          如果是oracle的jar，叫做oracle的驱动
 *
 *          第三波：我们java程序员，面向JDBC接口写代码就行
 *
 * 5.JDBC开发之前的准备工作
 *          mysql的驱动jar包，需要配置到classpath中。mysql-connector-java-5.1.23-bin.jar是字节码文件，java虚拟机的类加载器会去加载class文件。
 *          classpath没有配置的情况下，默认从当前路径下加载calss。
 *          classpath如果配置死了，例如：classpath：classpath=D:\abc，则表示固定只从D:\abc目录下找class文件
 *
 *          classpath = .;D:\BigData\JBDC\02-JDBC\resource\MySql Connector Java 5.1.23\MySql Connector Java 5.1.23\MySql-Connector-Java-5.1.23-bin.jar（不一定是这个路径，看你放在哪）
 *          . 代表当前路径
 *          以上的classpath代表：类加载器从当前路径下加载class，如果当前路径下没找到，则去D:\BigData\JBDC\02-JDBC\resource\MySql Connector Java 5.1.23\MySql-Connector-Java-5.1.23-bin.jar
 *          如果使用的是IDEA工具，则不需要在系统环境变量中这么配置，IDEA有自己的配置方法
 *          另外，jar包不需要解压，java虚拟机的类加载器有能力找到这个class文件
 *
 * 6.JDBC编程六步
 *      1.注册驱动
 *          （通知java程序我们即将要连接的是哪个品牌的数据库）
 *      2.获取数据库连接
 *          （java进程和mysql进程，两个进程之间的通道打开了）
 *          （java程序可能在北京，mysql进程在上海）
 *      3.获取数据库操作对象
 *          这个对象很重要，用这个对象来执行sql语句
 *      4.执行sql语句
 *          执行CRUD操作
 *      5.处理查询结果集
 *          如果第四步是select语句，才有这一步
 *      6.释放资源
 *          关闭所有的资源
 */

public class jdbc01 {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1.注册驱动
            //com.mysql.jdbc.Driver是mysql数据库厂家写的，实现了java.sql.Driver接口
            //如果是oracle数据库的化，类名就不一样了：oracle.jdbc.driver.OracleDriver
            java.sql.Driver driver = new com.mysql.jdbc.Driver();
            //java.sql.Driver 是接口，这里构成了多态
            //Driver driver = new oracle.jdbc.driver.OracleDriver()

            /**
             * 注册驱动实际常用的方法 - 类加载注册驱动
             *      通过反射机制，使用类加载的动作完成驱动的注册
             *      mysql厂家写的类：
             *      class com.mysql.jdbc.Driver{
             *          static {
             *              try {
             *                  DriverManager.registerDriver(new Driver());
             *              } catch (SQLException var1) {
             *                  throw new RuntimeException("Can't register driver!");
             *              }
             *          }
             *      }
             *      含有这么一个静态代码块，我们可以利用反射机制调用类加载动作进而执行静态代码块中的代码完成驱动的注册
             */
            Class.forName("com.mysql.jdbc.Driver");
            //oracle数据库：Class.forName("oracle.jdbc.driver.OracleDriver");

            //2.获取数据库连接
            /**
             * URL是什么？
             *      统一资源定位符
             * 任何一个URL都包括：
             *      协议+IP地址+端口号port+资源名
             *  协议：
             *      是一个提前定好的数据传输格式，通信协议有很多：http、https....
             *      在传输数据之前，提前商量好数据传输的格式。这样对方接收到数据之后，就会按照这个格式去解析，拿到有价值的数据。
             *  IP地址：
             *      网络中定位某台计算机的
             *  PORT端口号：
             *      定位这台计算机上某个服务的
             *  资源名：
             *      这个服务下的某个资源
             * jdbc:mysql://              这是java程序和mysql通信的协议
             * localhost                  这是本机IP地址，本机IP地址还可以写成：127.0.0.1
             * 3306                       mysql数据库端口号
             * bjpowernode                mysql数据库的名称
             *
             * 如果是oracle数据库的话：
             *      oracle:jdbc:thin:@localhost:1521:bjpowernode
             *      oracle:jdbc:thin:@  这是oracle和java的通信协议
             *      localhost           这是本机IP地址
             *      1521                oracle默认端口
             *      bjpowernode         oracle中数据库的名字
             * localhost 和 127.0.0.1 都是本机IP地址，记住！！！
             */
            String url = "jdbc:mysql://localhost:3306/bjpowernode";
            String user = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url,user,password);

            //输出 连接对象 的内存地址
            //com.mysql.jdbc.JDBC4Connection@2aaf7cc2
            System.out.println(conn);

            //3.获取数据库操作对象
            stmt = conn.createStatement();
            //通过一个连接对象Connection可以获取多个Statement对象
            Statement stmt2 = conn.createStatement();

            //4.执行sql语句 (java中的sql不需要以分号;结尾)
            //insert delete update
            //增
            String insert = "insert into dept(deptno,dname,loc) values(40,'销售部','北京')";
            //Statement接口中的executeUpdate方法专门来执行DML语句的
            //该方法的返回值表示：影响了数据库表中的总记录条数！ 删了一条返回1，增加两条返回2，更新三条返回3
            int count = stmt.executeUpdate(insert);
            System.out.println(count);//1

            //删
            String delete = "delete from dept where deptno = 30";

            //改
            String update = "update dept set dname = '人事部',loc = '天津', where deptno = 20)";

            //DQL语句
            String select = "select empno,ename,sal from emp order by sal desc";
            //执行查询语句的方法是：executeQuery
            //ResultSet就是查询结果集对象，查询的结果都在这个对象当中。
            rs = stmt.executeQuery(select);

            /**
             * JDBC中所有的下标都是从1开始的
             */
            //5.处理查询结果集 (得是查询语句才有这一步)
            //ResultSet对象中保存着查询语句的结果集，我们可以遍历结果集
            //boolean has = rs.next();方法的作用：将光标向前移动一位，指向的位置有数据则返回true，没有数据则返回false
            boolean has = rs.next();
            if (has) {
                //条件成立表示光标指向的行有数据
                //取当前行的第1个值
                String empno = rs.getString(1);//注意：getString()这个方法不管底层数据库表中是什么类型，统一都以字符串形式返回
                //取当前行的第2个值
                String ename = rs.getString(2);
                //取当前行的第3个值
                String sal = rs.getString(3);
            }

            //取出全部的数据，以循环的方式
            while (rs.next()) {
                //根据下标来取值，并且都是以字符串String类型取出的
                /*
                String empno = rs.getString(1);
                String ename = rs.getString(2);
                String sal = rs.getString(3);
                */

                //根据下标来取值，以特定类型取出
                /*
                int empno = rs.getInt(1);
                String ename = rs.getString(2);
                double sal = rs.getDouble(3);
                System.out.println(empno + "," + ename + "," + (sal + 1000*2));//sal以double类型取出，可以参与运算
                */

                //实际常用的形式
                //表示以所给字符指定的列取出数据
                int empno = rs.getInt("empno");//empno并不是字段的名字，而是查询结果的列名称 （看查询语句中给这个列起什么名，起了别名就要用别名）
                String ename = rs.getString("ename");
                double sal = rs.getDouble("sal");
                System.out.println(empno + "," + ename + "," + (sal + 1000*2));//sal以double类型取出，可以参与运算

            }


        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            //先释放ResultSet,再释放Statement，最后释放Connection
            //分别进行try...catch...处理
            if (rs != null) {
                try {
                    rs.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}


/**----------------------------------------------------------------------------------------------------------------------------------------------*/

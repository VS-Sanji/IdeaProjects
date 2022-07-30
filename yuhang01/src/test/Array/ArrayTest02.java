package test.Array;
/**
 * main方法中的”String[] args“有什么用
 * 分析一下，谁负责调用main方法：JVM
 * JVM调用main方法的时候，会自动传一个String数组进来
 */
public class ArrayTest02 {
    //这个方法程序员负责写出来，JVM负责调用，JVM调用的时候一定会传一个String数组进来
    public static void main(String[] args) {
        //JVM默认传进来的这个数组的长度为0
        //通过测试可知：args不是null
        System.out.println("JVM默认传进来的这个数组的长度为:"+args.length);//JVM默认传进来的这个数组的长度为:0

        //以下代码表示对含义：数组对象创建了，但是数组中没有任何数据
        String[] strs = new String[0];
        String[] strings={};  //静态初始化，里面没东西
        printLength(strs);

        //这个数组什么时候会有值呢，其实这个数组是留给用户的，用户可以在控制台上输入参数，这个参数自动会被转换为“String[] args”
        //例如这样运行程序：java ArrayTest02 abc def xyz
        //那么这个时候JVM会自动将“abc def xyz”通过空格为界限进行分离，分离完成之后，自动放到“String[] args”数组中
        //所以main方法上面的String[] args 数组主要是用来接收用户输入的参数的
        //把abc def xyz 转换成字符串数组：{"abc","def","xyz"}
        //使用情形：有个系统，要使用必须输入用户名和密码，然后可以在程序内设置判断语句，一致才能使用  IDEA在  运行-->编辑配置-->程序参数  中输入参数

        if (args.length != 2) {
            System.out.println("使用系统请输入正确的用户名和密码");
            return;
        }
        //程序执行到此处说明用户提供了用户名和密码
        //接下来就可以进行比对判断
        //读取用户名
        String username = args[0];
        //读取密码
        String password = args[1];

        //假设用户名是：yuhang，密码是：123，则登录成功，其他都为失败，进行比对，需要调用String的equals方法
        /**if (username.equals("yuhang") && password.equals("123")){  一般不把username写前面，避免在username为null时出现空指针异常，可以把它写后面
            System.out.println("登录成功");
        }
         */
        if ("yuhang".equals(username) && "123".equals(password)){
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }

            for (int i = 0; i < args.length; i++) {
                System.out.println(args[i]);
        }
    }

    public static void printLength(String[] strs){
        System.out.println(strs.length);
    }
}

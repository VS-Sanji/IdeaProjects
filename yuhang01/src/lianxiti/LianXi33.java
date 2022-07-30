package lianxiti;

import java.util.Scanner;

/**
 *
 * 编写程序模拟用户注册：
 * 1、程序开始执行时，提示用户输入“用户名”和“密码”信息。
 * 2、输入信息之后，后台java程序模拟用户注册。
 * 3、注册时用户名要求长度在[6-14]之间，小于或者大于都表示异常。
 *
 * 注意：
 * 	完成注册的方法放到一个单独的类中。
 * 	异常类自定义即可。
 *
 * 	class UserService {
 * 		public void register(String username,String password){
 * 			//这个方法中完成注册！
 *      }
 *  }
 *
 * 	编写main方法，在main方法中接收用户输入的信息，在main方法
 * 	中调用UserService的register方法完成注册。
 */
public class LianXi33 {
    public static void main(String[] args) {
        System.out.println("请输入用户名");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println("请输入密码");
        String s1 = scanner.nextLine();
        UserService us = new UserService();
        try {
            us.register(s,s1);
        } catch (UsernameException e) {
            e.printStackTrace();
        }
        System.out.println("hello world ");
    }
}
class UserService {
    //名字
    private String name;
    //密码
    private String password;
    public void register(String username, String password) throws UsernameException {
        /**
         * 引用 == null; 这个条件最好放到最前面，以防他放在后面的时候，发生空指针异常
         * if (username == null || username.length() < 6 || username.length() > 14)
         *
         * 而username == null; 又不如写成 null == username;
         * "abc".equals(username) 比 username.equals("abc") 好,逻辑上防止空指针异常
         */
        if (username == null || username.length() < 6 || username.length() > 14){
                throw new UsernameException("用户名要求长度在[6-14]之间");
        }
        this.name = username;
        this.password = password;
        System.out.println("注册成功");


    }
}
class UsernameException extends Exception {
    //无参构造方法
    public UsernameException() {
    }

    //有参构造方法
    public UsernameException(String s) {
        super(s);
    }
}

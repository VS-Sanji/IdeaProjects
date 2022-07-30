package lianxiti;



/*
编写程序，模拟用户登录功能，程序开始运行时先在DOS命令窗口中初始化登录页面，提醒用户输入用户名和密码，
当用户输入用户名为admin，密码为123的时候登录成功，打印欢迎信息，当用户输入的用户名和密码不正确打印错误
提示信息并退出系统。对于以上的程序大家尽可能定义相关方法来完成，不要将所有代码都放到main方法当中。
 */
public class LianXi6 {
    /*public static void main(String[] args) {
        //初始化界面
        initUI();
    }
    public static void login(String username, String password) {
        if("admin".equals(username) && "123".equals(password)){
            System.out.println("登录成功，欢迎"+username+"回来！");
        }else{
            System.out.println("对不起，用户名或者密码错误！");
        }
    }
    public static void initUI() {
        java.util.Scanner s = new java.util.Scanner(System.in);
        System.out.println("欢迎使用本系统，请登录！");
        System.out.print("用户名：");
        String username = s.next();
        System.out.print("密码：");
        String password = s.next();
        //登录
        login(username, password);
    }
     */

    public static void main(String[] args) {
        //初始化界面
        initUI();
    }

    public static void initUI() {
        java.util.Scanner s=new java.util.Scanner(System.in);
        System.out.println("欢迎使用本系统，请登录！");
        System.out.print("用户名：");
        int username = s.nextInt();
        System.out.print("密码：");
        int password = s.nextInt();
        //登录
        login(username, password);
    }
    public static void login(int username, int  password) {
        if(username==4396 && password==123){
            System.out.println("登录成功，欢迎"+username+"回来！");
        }else{
            System.out.println("对不起，用户名或者密码错误！");
        }
    }
}

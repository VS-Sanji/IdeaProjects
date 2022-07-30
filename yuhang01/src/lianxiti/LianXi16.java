package lianxiti;
//定义一个用户类
//要处理的信息有用户ID、用户密码、email地址。
//属性私有化，对外提供公开的set和get方法
//提供无参数构造方法
//提供有参数构造方法
//提供一个showInfo()方法，通过这个方法展示用户信息。
//编写测试程序创建对象，调用showInfo()方法。
public class LianXi16 {
    public static void main(String[] args) {
        User user=new User(7,7723,"2631331895@qq.com");
        user.showInfo();

    }
}

class User{
    //用户名
    private int Id;
    //密码
    private int password;
    //邮箱
    private String emailAddress;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    //无参数构造方法
    public User() {
    }

    //有参数构造方法
    public User(int id, int password, String emailAddress) {
        Id = id;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;


    }

    public void showInfo(){
        System.out.println("用户名是："+getId()+"\t"+"用户密码是："+getPassword()+"\t"+"邮箱是："+getEmailAddress());
    }




}
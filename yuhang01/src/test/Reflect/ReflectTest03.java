package test.Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 可变长度参数
 *      int... args 这就是可变长度参数
 *      语法是：类型... (一定是三个点)
 *
 *      1.可变长度参数个数是：0~N个
 *      2.可变长度参数在参数列表中必须在最后一个位置上，而且只能有一个是可变长度参数
 */
public class ReflectTest03 {
    public static void main(String[] args) {
        //任意个数参数
        m();
        m(13);
        m(23,52,23,5);

        //类型不符
        //m("java");

        m4("as", "sdf");

        String[] str = {"a", "b" , "c"};
        //m4方法可以传一个数组进去，说明String... strings是个数组
        m4(str);

        m4(new String[]{"我是", "中国人"});


        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        /**
         * 通过反射机制去调用对象的方法
         *      要素1.对象
         *      要素2.方法名
         *      要素3.实参列表
         *      要素4.返回值
         */
        //不使用反射机制
        UserService userService = new UserService();
        String result = userService.login("admin","123");
        System.out.println(result);

        //使用反射机制
        try {
            //首先获取这个类
            Class userServiceClass = Class.forName("test.UserService");
            //创建对象
            Object obj = userServiceClass.newInstance();
            //获取Method
            //也需要四要素,对象userServiceClass,方法名login，实参列表String.class,String.class
            Method loginMethod = userServiceClass.getDeclaredMethod("login", String.class, String.class);
//            Method loginMethod = userServiceClass.getDeclaredMethod("login",int.class);   同名方法通过实参列表来区分

            //拿到方法后，调用方法。调用方法也有四要素
            //反射机制中最重要的一个方法，必须记住
            /*
            四要素：
                1.loginMethod方法
                2.obj对象
                3.”admin“ ”123“ 实参
                4.returnValue 返回值
             */
            Object returnValue = loginMethod.invoke(obj,"admin","123");
            System.out.println(returnValue);      //登录成功

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        /**
         * 使用反射机制创建对象
         */
        //不使用反射机制如何创建对象
        UserService us = new UserService();
        UserService us1 = new UserService("yuhang");
        UserService us2 = new UserService("yuhang","123123");

        //使用反射机制创建对象
        try {
            //先获取class
            Class usClass = Class.forName("test.UserService");
            //实例化对象 调用的是无参构造
            Object obj = usClass.newInstance();

            //调用有参数的构造方法
            //首先：先获取这个有参数的构造方法
            Constructor constructor = usClass.getDeclaredConstructor(String.class);
            //然后：调用构造方法new对象
            Object obj1 = constructor.newInstance("zhansan");

            Constructor constructor1 = usClass.getDeclaredConstructor(String.class, String.class);
            Object obj2 = constructor1.newInstance("zhangsan","123123123");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        /**
         * 使用反射机制获取一个类的父类，以及这个类实现的所有接口
         */
        //以String为例
        //先拿到class
        Class stringClass = null;
        try {
            stringClass = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //获取父类
        Class superClass = stringClass.getSuperclass();
        System.out.println(superClass.getName());            //java.lang.Object

        //获取接口
        Class[] interfaces = stringClass.getInterfaces();
        for (Class in : interfaces){
            System.out.println(in.getName());
        }
        //java.io.Serializable
        // java.lang.Comparable
        //java.lang.CharSequence
    }




    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static void m(int... args){
        System.out.println("m方法执行了");
    }

    //public static void m2(int... args,String... args1){}; 编译报错，可变长度参数在一个方法中只能有一个

    //public static void m2(int a, short... b, long c){};  编译报错，可变长度参数只能出现在形参列表的最后一个位置上

    //必须在最后，只能有一个
    public static void m3(int a, String... strings){
    };

    public static void m4(String... strings){
        //strings有length属性，说明strings是一个数组
        //可以将可变长度参数当成一个数组来看
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
            
        }
    }
}


//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
class UserService {
    private String name;



    private String password;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public UserService(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String login(String name, String password){
        if ("admin".equals(name) && "123".equals(password)){
            return "登录成功";
        }
        return "登录失败";
    }

    //有可能还有同名的方法
    public void login(int i){

    }

}
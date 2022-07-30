package test.Static;

/**
    关于实例语句块
        每一次调用构造方法之前会执行一次。
        实例语句块执行顺序也是自上而下。
*/

public class StaticTest02 {

    //静态语句块
    static{
        System.out.println("A");
    }

    //实例语句块
    {
        System.out.println("1");
    }


    {
        System.out.println("2");
    }


    {
        System.out.println("3");
    }

    //构造方法
    StaticTest02(){
        System.out.println("StaticTest02无参数的构造执行！");
    }

    //入口
    public static void main(String[] args){

        //调用构造方法
        new StaticTest02();

        new StaticTest02();

        new StaticTest02();
    }
}

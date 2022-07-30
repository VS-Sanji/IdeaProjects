package lianxiti;
//在程序中经常要对时间进行操作但是并没有时间类型的数据。
//那么我们可以自己实现一个时间类来满足程序中的需要。
//定义名为MyTime的类其中应有三个整型成员时hour分minute秒second
//为了保证数据的安全性这三个成员变量应声明为私有。
//为MyTime类定义构造方法以方便创建对象时初始化成员变量。
//再定义diaplay方法用于将时间信息打印出来。
//为MyTime类添加以下方法
//	addSecond(int sec)
//	addMinute(int min)
//	addHour(int hou)
//	subSecond(int sec)
//	subMinute(int min)
//	subHour(int hou)
//分别对时、分、秒进行加减运算。
public class LianXi22 {
    public static void main(String[] args) {
    }
}


//class MyTime{
//    //时
//    private int hour;
//    //分
//    private int minute;
//    //秒
//    private int second;
//
//    public MyTime() {
//    }
//
//    public MyTime(int hour, int minute, int second) {
//        this.hour = hour;
//        this.minute = minute;
//        this.second = second;
//    }
//
//    public void addSecond(int sec){
//        this.second+=sec;
//    }
//    public void addMinute(int min){
//        this.minute+=min;
//    }
//    public void addHour(int hou){
//        this.hour+=hou;
//    }
//    public void subSecond(int sec){
//        this.second+=sec;
//    }
//    public void subMinute(int min){
//        this.minute+=min;
//    }
//    public void subHour(int hou){
//        this.hour+=hou;
//    }
//
//    public void display(){
//        System.out.println();
//    }
//}
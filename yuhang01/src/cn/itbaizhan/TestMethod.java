package cn.itbaizhan;

import java.util.Scanner;

public class TestMethod {
    //定义一个方法输出各种版本我爱你
    public static void loveU(int num){  // num 为形式参数  具体是多少看后面调用时所取的值  void表示不需要输出 需要返时可以用别的；具体是什么看返回值的类型
        System.out.println("我爱你！");
        System.out.println("520!");
        System.out.println("521!");
        System.out.println("I love you!"+"===="+num+"年");
    }

    //定义一个方法，求随机输入三个数的和并且输出
    public static int add(int a,int b,int c){  //各变量之间用逗号隔开
        int sum=a+b+c;
        return sum;

    }

    //定义一个方法，处理迟到的惩罚
    /*
    1.输入参数：员工名称，月薪
    2.处理逻辑
    2.1迟到1-10分钟，警告
    2.2迟到11-20分钟，罚款100
    2.3迟到21-30分钟，罚款200
    2.4迟到30分钟以上，扣除半日工资
    2.5迟到一小时以上，按旷工计算，扣除三天工资
    3.输出罚款金额和处理意见
     */
    public static void late(){
        double fakuan=0 ;                         //罚款
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入您的姓名！");
        String name=sc.nextLine();                //名字是字符串，要用String
        System.out.println("请输入您的月薪！");
        double salary=sc.nextInt();               //月薪是数，可用double或者int
        System.out.println("请输入迟到时间(分钟)：");
        int lateTime=sc.nextInt();
        if (lateTime<=10){
            System.out.println("警告！！！");
        }else if (10<lateTime&&lateTime<=20){
            System.out.println("罚款100!");
        }else if (21<lateTime&&lateTime<=30){
            System.out.println("罚款200！");
        }else if (30<lateTime&&lateTime<=60){
            fakuan=salary/30/2;
            System.out.println("扣除半日工资！"+fakuan+"元");
        }else{
            fakuan=salary/30*3;
            System.out.println("旷工，扣除三天工资！"+fakuan+"元");
        }
        System.out.println("姓名:"+name+"\t"+"月薪:"+salary+"\t"+"迟到时间:"+lateTime+"分钟"+"\t"+"罚款："+fakuan);
    }


//调用方法
    public static void main(String[] args) {  //main方法  是程序的入口
        //测试打印各版本我爱你的方法
        int i;
        int j=0;
        for (i=1;i<=100;i++){
            loveU(10000);   //10000即为实际参数  对应调用时给的值
            j++;
        }
        System.out.println(j);

        //测试求和方法
        int x=1+add(5204,5661, (int) 4488464.0);
        System.out.println(x);

        //测试迟到处理方法
        late();
    }
}

package cn.itbaizhan;

import com.sun.deploy.security.SandboxSecurity;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;

import static java.util.Scanner.*;

public class ZuoYe {
    public static void main(String[] args) {
        System.out.println("我的年薪计算器");
        System.out.println("exit退出程序"+"next计算下一个年薪");

        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("请输入您的月薪：");
            int monthSalary = scanner.nextInt();
            System.out.println("请输入一年多少个月薪资：");
            int months=scanner.nextInt();
            int yearsSalary = monthSalary * months;
            System.out.println("年薪是：" + yearsSalary);

            if (yearsSalary>100000&&yearsSalary<200000){
                System.out.println("恭喜你超越了90%的国人！");
            }else if (yearsSalary>=200000){
                System.out.println("恭喜你超越了98%的国人！");
            }else{
                System.out.println("有钱人！");
            }

        }


    }
}

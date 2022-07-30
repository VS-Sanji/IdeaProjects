package daimati;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        for (int x=1;x<=5;x++){
            int a=0;
            int n;
            System.out.println("输入1~1000内的正整数n：");
            Scanner sc=new Scanner(System.in);
            n=sc.nextInt();
            /*
            if (n==1){
                System.out.println(a);
            }
             */

            while (n!=1) {
                if (n % 2 == 0) {
                    n = n / 2;
                } else {
                    n = (3 * n + 1) / 2;
                }
                a++;
            }
            System.out.println(a);
        }

    }
}

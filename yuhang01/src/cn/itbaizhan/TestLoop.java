package cn.itbaizhan;

public class TestLoop {
    public static void main(String[] args) {
        //嵌套循环通常用来处理 二维结构
        for (int j=1;j<=5;j++) {
            for (int i = 1; i <= 5; i++) {
                System.out.print(j+"\t");
            }
            System.out.println();
        }

        //打印1*5=5 2*5=10 3*5=15 4*5=20 5*5=25
        for (int k=9 ;k>=1;k--) {
            for (int j = 9; j >= k; j--) {
                System.out.print(j + "*" + k + "=" + (j * k) + "\t");
            }
            System.out.println();
        }

        //打印1~1000内能被5整除的数，每行显示五个
        int a;
        int b=0;
        for (a=1;a<=1000;a++){
            if (a%5==0){
                System.out.print(a+"\t");
                b++;
            }
            if (b%5==0){
                System.out.println();
            }
        }
        //用while写
        int c=1;
        int d=0;
        while (c%5==0){
            System.out.print(c+"\t");
            d++;
            if (d%5==0){
                System.out.println();
            }
            c++;
        }

        for (int y=1;y<=5;y++){
            for (int x=1;x<=5;x++){
                System.out.print("*");
            }
            System.out.println();
        }

        int e;  //行变量
        int f;  //列变量
        for (e=1;e<=5;e++){   //行变量要循环做五次
            for (f=1;f<=5;f++){  //列变量做五次循环
                if ((e+f)%2==0){
                    System.out.print("*"+"\t");
                }else{
                    System.out.print("#"+"\t");
                }
            }
            System.out.println();
        }
    }
}


package cn.itbaizhan;

public class TestBreakContinue {
    public static void main(String[] args) {
        int a;
        int b=0;
        for (;;){
            a=(int)(Math.random()*100);//后面打个括号 即要先算后面的随机数再化成整型
            b++;
            if (a==88){
                break;   //循环中捕捉到88即停止循环，进入下一步
            }
        }
        System.out.println("b="+b+"\t"+"gameover!!!");


        int c;
        int d=0;
        for (c=100;c<=150;c++){
            if (c%3==0){
                continue;  //循环种满足判断条件，继续循环过程，不进入下一步
            }else{
                System.out.print(c+"\t");
                d++;
            }
            if (d%5==0) {
                System.out.println();
            }
        }
    }
}
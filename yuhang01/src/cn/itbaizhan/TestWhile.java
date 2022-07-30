package cn.itbaizhan;

public class TestWhile {
    public static void main(String[] args) {

        int a = 1;
        while (a <= 3) {
            System.out.println("I love you!" + a);
            a++;
        }

        //1+2+3+4+...+100
        int i = 0;
        int sum = 0;
        while (i <= 100) {
            sum += i;
            i++;
        }
        System.out.println("sum=" + sum);


        int x = 0;
        int sum1 = 0;
        while (x <= 100) {
            sum1 += x;
            x += 2;
        }
        System.out.println("sum1=" + sum1);


        int y = 1;
        int sum2 = 0;
        while (y <= 100) {
            sum2 += y;
            y += 2;
        }
        System.out.println("sum2=" + sum2);


        int z = 1;
        int sum4 = 0;
        int sum5 = 0;
        int sum6 = 0;
        while (z <= 100) {
            if (z % 2 == 0) {
                sum5 += z;
            } else {
                sum6 += z;
            }
            sum4 += z;
            z++;
        }
        System.out.println("sum4=" + sum4);
        System.out.println("sum5=" + sum5);
        System.out.println("sum6=" + sum6);

        int b=0;
        while (b<=120) {
            if (b%5==0){
                System.out.println(b);
            }
            b++;
        }

        int c=0;
        while (c<=130){
            System.out.print(c+"\t");
            if ((c+1)%7==0){
                System.out.print("\n");
            }
            c++;

        }
    }
}

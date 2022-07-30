package lianxiti;
//打印2维图形
public class LianXi3 {
    public static void main(String[] args) {
        for (int i=1;i<=5;i++){
            for (int j=1;j<=5-i;j++){
                System.out.print(" ");
            }
            for (int k=1;k<=i*2-1;k++){
                System.out.print("*");
            }
            System.out.println();
        }
        for (int l=4;l>=1;l--){
            for (int m=1;m<=5-l;m++){
                System.out.print(" ");
            }
            for (int n=l*2-1;n>=1;n--){
                System.out.print("*");
            }
            System.out.println();
        }

    }

}


package cn.itbaizhan;

public class  TestDiGui {
    //测试递归               递归思想=自己调用自己
    public static int a(int n){                      //写个打印的方法  叫做  a()
        if (n==1){
            return 1;
        }else{
            return n*a(n-1);
        }
    }
    public static void main(String[] args) {     //调用
        System.out.println(a(6));
    }
}

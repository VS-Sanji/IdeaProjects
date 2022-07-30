package cn.itbaizhan;
//测试重载（overload）
public class TestOverload {
    //方法一
    public static int add(){
        return 0;
    }

    //方法二
    public static int add(int a,int b){
        return a+b;
    }

    //方法三
    public static int add(int a,int b,int c){
        return a+b+c;
    }

    //方法四
    public static double add(double a, int b, double c){
        return a*b*c;
    }

/*
      重载的方法，实际是完全不同的方法，只是名称相同而已!
      构成方法重载的条件：
      1.不同的含义：形参类型、形参个数、形参顺序不同
      2.只有返回值不同不构成方法的重载
 */
    public static void main(String[] args) {
        add();
        System.out.println(add());
        System.out.println(add(1,5,6)+
        add(12,56)+
        add(23.0,5,269594.064564));
    }
}

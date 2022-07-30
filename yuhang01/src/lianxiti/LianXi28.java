package lianxiti;
//利用接口做参数，写个计算器，能完成加减乘除运算。
//（1）定义一个接口Compute含有一个方法int computer(int n, int m)。
//（2）设计四个类分别实现此接口，完成加、减、乘、除运算。
//（3）设计一个类UseCompute，类中含有方法：public void useCom(Compute com, int one, int two)，此方法能够用传递过来的对象调用computer方法完成运算，并输出运算的结果。
//（4）设计一个主类Test，调用UseCompute中的方法useCom来完成加减乘除运算。
public class LianXi28 {
    public static void main(String[] args) {
        UseComputer useComputer1=new UseComputer();
        useComputer1.useCom(new A1(),34,36);
        UseComputer useComputer2=new UseComputer();
        useComputer2.useCom(new A2(),6,7);
        UseComputer useComputer3=new UseComputer();
        useComputer3.useCom(new A3(),65,6);
        UseComputer useComputer4=new UseComputer();
        useComputer4.useCom(new A4(),656345,455);
    }
}

interface Compute{                        //接口  模块
    int computer(int n,int m);
}

class A1 implements Compute{               //实现者  模块
    @Override
    public int computer(int n, int m) {
        return n+m;
    }
}

class A2 implements Compute{
    @Override
    public int computer(int n, int m) {
        return n-m;
    }
}

class A3 implements Compute{
    @Override
    public int computer(int n, int m) {
        return n*m;
    }
}

class A4 implements Compute{
    @Override
    public int computer(int n, int m) {
        return n/m;
    }
}

class UseComputer{                                           //调用者  模块
    public void useCom(Compute com,int one,int two){         //Computer com   传一个子类对象的引用给com变成父类引用，形成多态
        int three=com.computer(one,two);                     //父类引用调用computer（）方法，编译能过，运行时则调用子类重写过的方法完成不同的需求
        System.out.println(three);
    }
}
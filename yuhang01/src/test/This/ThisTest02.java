package test.This;

/**
    this能用在成员方法中。

    this就是当前对象.
*/
public class ThisTest02{

    public static void main(String[] args){

        //创建对象
        Employee e = new Employee(7369,"SMITH");

        //工作
        e.work();


        //创建对象
        Employee e1 = new Employee(7370,"FORD");

        //工作
        e1.work();


        e.m1();

    }

}


class Employee{

    //员工编号
    int empno;

    //员工名
    String ename;

    //Constructor
    Employee(){}

    Employee(int _empno,String _ename){
        empno = _empno;
        ename = _ename;
    }

    //提供一个员工工作的方法.
    //this用在成员方法中，谁去调用这个成员方法，this就代表谁。
    //this指的就是当前对象。
    public void work(){
        System.out.println(this.ename + "在工作");
        //System.out.println(ename + "在工作"); //this. 可以省略
    }

    //成员方法
    public void m1(){
        this.m2();
        m2();
    }

    //成员方法
    public void m2(){
        System.out.println("TESTING");
    }

}

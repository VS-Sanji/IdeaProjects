package test.This;

/**
    this不能用在静态方法中.
        静态方法的执行根本不需要java对象的存在。直接使用  类名.  的方式访问。
        而this代表的是当前对象。所以静态方法中根本没有this
*/
public class ThisTest04{

    String str;

    //入口
    public static void main(String[] args){

        Person.m1();
        //str是一个成员变量，必须由  引用.   访问
        //System.out.println(str);


        ThisTest04 tt = new ThisTest04();
        System.out.println(tt.str); //null

    }

}

class Person{

    //Field
    String name;

    //Constructor
    Person(){}

    Person(String name){
        this.name = name;
    }

    //静态方法
    public static void m1(){

        //System.out.println(this.name);

        //如果要想访问name只能：
        Person p1 = new Person("刘德华");
        System.out.println(p1.name);
    }

}

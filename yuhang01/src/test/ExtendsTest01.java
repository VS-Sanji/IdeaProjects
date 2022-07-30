package test;

public class ExtendsTest01 {
    /**
    引入类继承最基本的作用是：代码重用。

    语法：
        [修饰符列表] class 子类名 extends 父类名{
            类体;
        }

    java语言中，类和类之间只支持单继承。但是可以间接继承，如：A extends  B;   B  extends   C ;  则A间接继承C

    一个类如果没有显示的继承其他类，则该类默认继承Object.
    Object是SUN提供的java中的根类。
*/

/**
    java语言中子类继承父类,能继承除了 构造方法 和被 private修饰的 都能继承。
    在子类中无法直接访问父类中的私有的数据。但是可以间接访问.
    例如儿子继承了他爸的保险柜，只不过没有密码打不开保险柜。可暴力访问继承自父类的私有成员

    注意：构造方法无法被子类继承。
*/
    public class Sub extends SuperClass{

        public void m3(){
            System.out.println("m3....");
        }
    }
}


 class SuperClass{

    public void m1(){
        System.out.println("m1...");
    }

    public void m2(){
        System.out.println("m2...");
    }

}

/**
    Customer没有显示继承其他类，默认继承SUN提供的java.lang.Object;
    所有Customer类中也有toString方法.
*/
 class Customer{

    public static void main(String[] args){

        Customer c = new Customer();
        String s = c.toString();

        System.out.println(s); //Customer@de6ced

    }
}
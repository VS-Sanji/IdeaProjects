package test.Interface;
/**接口的基础语法。
 1、接口是一种“引用数据类型”。
 2、接口是完全抽象的。
 3、接口怎么定义：[修饰符列表] interface 接口名{}
 4、接口支持多继承。
 5、接口中只有常量+抽象方法。
 6、接口中所有的元素都是public修饰的
 7、接口中抽象方法的public abstract可以省略。
 8、接口中常量的public static final可以省略。
 9、接口中方法不能有方法体。
 10、一个非抽象的类，实现接口的时候，必须将接口中所有方法加以实现。
 11、一个类可以实现多个接口。
 12、extends和implements可以共存，extends在前，implements在后。
 13、使用接口，写代码的时候，可以使用多态（父类型引用指向子类型对象）。
 */
public class InterfaceTest1 {
    public static void main(String[] args) {
//        Interface1 I1=new A1();
//        I1.method1();           //编译时Interface1中有method1（）方法，所以能够调用
//        I1.method2();           //编译时Interface1中没有method1（）方法，所以不能能够调用，需要转型
//        Interface2 I2=(Interface2)I1;//这里可以发现，Interface1与Interface2没有继承关系，但是也能转，这是不同于类的，类与类互转必须要有继承关系
//        I2.method2();                //但是与类中的情形相似，仍然有可能出现ClassCastEecption异常，所以最好还是要用instanceof运算符来加以判断

        B1 b=new D1();
        C1 c=(C1)b; //  Exception in thread "main" java.lang.ClassCastException,在转型时b指向的是D1，要转成C1，两者没有继承关系，出现异常
//        if (b instanceof C1) {     加instanceof判断
//            C1 c=(C1)b;
//        }

    }
}


interface B1{

}

interface C1{

}

class D1 implements B1{

}


interface Interface1{      //前面可以加访问权限修饰符，但这里已经有public class了，所以就不加了
    int a = 2;     //在接口中定义的变量为常量,所以需要我们手动初始化，且是公开的，等用于public static final int a=2;   public static final可以省略
    void method1();  //在接口中定义的方法只能是抽象方法，并且都是public abstract修饰的，而且可以省略；方法不能有方法体
}

interface Interface2{
    void method2();
}

interface Interface3 extends Interface1,Interface2{   //接口和接口支持多继承。
    void method3();
}


class A1 implements Interface1,Interface2,Interface3{  //类和接口支持多实现
    @Override
    public void method1() {
        System.out.println("实现方法一");              //类实现多个接口要将每个接口的抽象方法全部实现
    }

    @Override
    public void method2() {
        System.out.println("实现方法二");              //类实现多个接口要将每个接口的抽象方法全部实现
    }

    @Override
    public void method3() {
        System.out.println("实现方法三");              //类实现多个接口要将每个接口的抽象方法全部实现
    }


}


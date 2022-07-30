package test;

/**
 * final关键字
 * 	1.1、final修饰的类无法继承。
 * 	1.2、final修饰的方法无法覆盖。
 * 	1.3、final修饰的变量只能赋一次值。
 * 	1.4、final修饰的引用一旦指向某个对象，则不能再重新指向其它对象，但该引用指向的对象内部的数据是可以修改的。
 * 	1.5、final修饰的实例变量必须手动初始化，不能采用系统默认值。
 * 	1.6、final修饰的实例变量一般和static联合使用，称为常量。
 * 			public static final double PI = 3.1415926;
 */
public class FinalTest {
    final int a=7;//由于final修饰的变量只能赋值一次，而调用构造方法创建对象的时候会初始化变量，赋一个默认值，但这往往不符合我们的需求，所以需要我们手动赋值
//    a=8;      final修饰的只能被赋值一次，就算是相同的值也不行

    public static final double PI = 3.1415926;; //final修饰的实例变量一般和static联合使用，称为常量。
    public static void main(String[] args) {
        final Father father=new Father();  //建一个Father对象，赋给被final修饰的引用father
        //Father father=new Father();    于是这里就不能在将其他的对象赋给引用father了
    }
}


final class Father{
    int b=0;
}

/**final修饰的类无法被继承*/
/*
class son extends Father{
}*/

class Fish{
    public final void swing(){
        System.out.println("鱼儿在游泳！！！");
    }
}

/**父类中的swing方法被final修饰，所以无法被覆盖*/
/*class JiYu extends Fish{
    public final void swing(){
        System.out.println("鱼儿在游泳！！！");
    }
}*/


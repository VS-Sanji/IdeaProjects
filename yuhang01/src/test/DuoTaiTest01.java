package test;
/**多态是方法的多态，不同的对象执行同一种方法，产生不一样的结果，有多种状态，构成方法的多态
 * 通过继承来使得不同的对象都能拥有同一种方法，所以多态的形成需要以继承为前提（继承的最重要的作用之一也体现在这，不仅仅是为了代码的复用）
 * 继承后通过方法的重写来实现不一样的结果，所以多态的形成需要进行方法的重写   （继承的最重要的作用之二）
 * 最后通过父类型引用指向子类型对象来访问其中的数据，就在代码层面构成了多态机制
 * */


/**若没有多态机制，当一个父类被许多个子类继承，每增加一个子类想要对重写过的方法进行测试，都需要开创一个新的测试类，大大增加了代码量，耦合度高，不利于程序的维护。
 * 此时引入多态机制，通过java允许的父类引用指向子类对象这一特点，可以解决上述问题 ，降低耦合度，建立一个测试类就可以了。
 * 实现多态依靠三点：继承  方法重写   父类引用指向子类对象
 * 编译看左边   运行看右边
 * Animal a1 = new Cat();    a1.move();
 * 通过继承我们才能够实现对方法的重写，而方法的重写是实现多态效果的必要条件，所以多态的形成也需要以继承为前提
 * 继承后通过方法的重写来实现不一样的结果，在运行阶段真正执行时去调用子类中重写过的方法，达到不同效果，所以多态的形成需要进行方法的重写
 * 多态显然是离不开方法覆盖机制的，多态就是因为编译阶段绑定父类当中的方法（静态绑定）让编译通过，程序运行阶段自动调用子类对象上的方法（动态绑定）。
 * 如果子类对象上的方法没有进行重写，调用的方法还是父类的方法，这个时候创建子类对象就没有意义了，自然多态也就没有意义了，
 * 只有子类将方法重写之后调用到子类对象上的方法产生不同效果时，多态就形成了。
 * 实际上方法覆盖机制和多态机制是捆绑的，谁也离不开谁，多态离不开方法覆盖，方法覆盖离开了多态也就没有意义了。（覆盖就是为了达到不同对象不同效果（多态），所以两者密不可分）
 *
 * 静态方法不谈覆盖：其实静态方法可以覆盖，但是在调用的时候，即使创建了对象赋给父类引用，由于方法是静态的，与对象无关，所以执行时还是会去调用父类中的方法，就达不到多态的效果
 * 达不到多态的效果，那重写也没什么必要，也就没有意义
 * */
public class DuoTaiTest01 {
/**
    关于java语言中向上转型和向下转型
        1.向上转型(upcasting) :  子--->父
        2.向下转型(downcasting) :  父--->子

    注意：无论是向上转型还是向下转型，两个类之间必须要有继承关系。
*/


    public static void main(String[] args){

        //向上转型又被称作：自动类型转换.
        //父类型的引用指向子类型对象.
        //程序分两个阶段：编译阶段，运行阶段。
        //程序编译阶段只知道a1是一个Animal类型。
        //程序在运行的时候堆中的实际对象是Cat类型。
    /**Animal a1 = new Cat();
*/
        //程序在编译阶段a1被编译器看做Animal类型.
        //所以程序在编译阶段a1引用绑定的是Animal类中的eat方法.(静态绑定)
        //程序在运行的时候堆中的对象实际上是Cat类型，而Cat已经重写了eat方法。
        //所以程序在运行阶段对象的绑定的方法是Cat中的eat方法.(动态绑定)
    /**a1.eat(); //猫吃鱼
*/

        //向下转型：强制类型转换
    /**Animal a2 = new Cat(); //向上转型.
*/
        //要执行move方法,怎么做？
        //只能强制类型转换,需要加强制类型转换符
    /**Cat c1 = (Cat)a2;

        c1.move();
*/

        //判断以下程序运行的时候会出什么问题？
        //Animal a3 = new Dog(); //向上转型.

        //强制类型转换
        //Cat c2 = (Cat)a3; // java.lang.ClassCastException


        //在做强制类型转换的时候程序是存在风险的！
        //为了避免ClassCastException的发生，java引入了 instanceof
    /**
        用法：
            1. instanceof运算符的运算结果是 boolean类型
            2. (引用 instanceof 类型) --> true/false

            例如：(a instanceof Cat) 如果结果是true表示：a引用指向堆中的java对象是Cat类型.

        Animal a3 = new Dog();

        System.out.println(a3 instanceof Cat); //false

        //推荐：在做向下转型的时候要使用instanceof运算符判断，避免ClassCastException
        if(a3 instanceof Cat){
            Cat c2 = (Cat)a3;
        }



    }
     */

}

/*
 class Cat extends Animal{

     Cat(String name, int age) {
         super(name, age);
     }

     //重写
    public void eat(){    public static void main(String[] args) {
        Animal a=new Cat();
        a.method();
    }

}
class Animal{

}


class Cat extends Animal{
    public void method(){
        System.out.println("抓老鼠");
    }

}
        System.out.println("猫吃鱼");
    }

    //Cat特有的方法.
    public void move(){
        System.out.println("猫走猫步！");
    }
}


 class Dog extends Animal{

     Dog(String name, int age) {
         super(name, age);
     }

     //重写
    public void eat(){
        System.out.println("狗啃骨头！");
    }
}

 class Animal{

    //成员
    public void eat(){
        System.out.println("动物在吃！");
    }

}

 */

/**
     public static void main(String[] args) {
         Animal a=new Cat();
         a.method();  //这里编译是无法通过的，因为编译时认为a是Animal类，而Animal中没有method方法，所以编译错误 而运行阶段是看对象
     }

 }
class Animal{

}


class Cat extends Animal {
    public void method(){
        System.out.println("抓老鼠");
    }

}
 */
}
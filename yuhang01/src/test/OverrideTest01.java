package test;

public class OverrideTest01 {
/**
    关于方法的覆盖：

    1.什么时候方法要进行重写？
        如果父类中的方法已经无法满足当前子类的业务需求，需要将父类中的方法进行重新写一遍。

    2.子类如果重写父类中的方法之后，子类对象一定调用的是重写之后的方法。

    3.发生方法覆盖的条件：
        第一：发生在具有继承关系的两个类之间
        第二：必须具有相同的方法名，相同的返回值类型，相同的参数列表.
        第三：重写的方法不能比被重写的方法拥有更低的访问权限。

        第四：重写的方法不能比被重写的方法抛出更宽泛的异常。(异常机制再讲。)

        第五：私有的方法不能被覆盖。(多态之后讲)

        第六：构造方法无法被覆盖。因为构造方法无法被继承。

        第七：静态的方法不存在覆盖。(多态之后讲)

        第八：覆盖指的是成员方法，和成员变量无关。

    4.继承最基本的作用：代码重用。 继承最重要的作用：方法可以重写。
*/


    public static void main(String[] args){

        //创建子类对象
        Cat1 c = new Cat1();

        //调用方法
        c.move();


        Animal1 a = new Animal1();
        a.move();

    }



}

//父类:动物
class Animal1{

    //成员方法
    public void move(){
        System.out.println("动物在移动！");
    }

}

//子类:猫
class Cat1 extends Animal1{

    //Cat的move方法应该输出：猫在走猫步！
    //Animal中的move方法已经无法满足Cat了。
    //所以要在Cat类中对Animal类中的move方法进行重新定义(方法的覆盖，方法的重写，Overwrite,Override)
    public void move(){
        System.out.println("猫在走猫步！");
    }

}

/**
    私有的方法无法被覆盖
*/

//父类
class A{

    //私有方法
    private void m1(){
        System.out.println("A'm1 method invoke!");
    }

    //入口
    public static void main(String[] args){

        //多态
        A a = new B();

        a.m1(); //A'm1 method invoke!
    }
}


//子类
class B extends A{

    //尝试重写父类私有的方法
    public void m1(){
        System.out.println("B'm1 method invoke!");
    }

}
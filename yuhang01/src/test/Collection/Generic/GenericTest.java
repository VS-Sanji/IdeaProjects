package test.Collection.Generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * JDK5.0 之后，推出的新特性：泛型
 * 泛型这种语法机制，只在程序编译阶段起作用，只是给编译器参考的，（运行阶段泛型没用）
 * 使用了泛型的好处：
 *      第一：集合中可存储的元素是统一的，即只能够存储指定类型的元素
 *      第二：从集合中取出的元素类型是泛型指定的类型，不是Object类型，不需要进行大量的向下转型
 * 泛型的缺点：
 *      集合中存储的元素缺乏多样性
 *
 *
 * 泛型支持自定义
 */
public class GenericTest {
    public static void main(String[] args) {
        //不使用泛型机制
        List myList = new ArrayList();

        //准备对象
        MaleMan mm = new MaleMan();
        FemaleMan fm = new FemaleMan();
        String s = new String("1123");

        //将集合对象添加到集合中
        myList.add(mm);
        myList.add(fm);
        myList.add(s);

        //遍历集合,取出男人就跑步，女人就练瑜伽
        Iterator it = myList.iterator();
        while (it.hasNext()) {
            //没有引入泛型机制，这里取出的就只能是Object类型
            Object obj = it.next();
            if (obj instanceof Human) {
                if (obj instanceof MaleMan) {
                    MaleMan maleMan = (MaleMan)obj;
                    maleMan.run();
                }else {
                    FemaleMan femaleMan = (FemaleMan) obj;
                    femaleMan.yujia();
                }
            }
        }

        /**
         * 使用泛型机制
         * 使用了泛型 List<Human> 之后，表示集合中只允许存储Human类型的数据
         * 达到了一个指定类型的目的，这样集合中所有元素的类型都是统一的
         */
        List<Human> list = new ArrayList<Human>();
        //list.add("123");   报错
        //这里指定了集合中只能存储Human，那么其他的类型，比如说 String 之类的，存储就会报错
        //这样使用泛型之后，集合中元素统一了

        MaleMan mm1 = new MaleMan();
        FemaleMan fm1 = new FemaleMan();
        String s1 = new String("1234");

        list.add(mm1);
        list.add(fm1);
        //list.add(s1); 存不进去

        //这个迭代器迭代的就是Human了，可以将取出的元素直接赋给父类引用了
        Iterator<Human> it1 = list.iterator();
        while (it1.hasNext()) {
            //取出的元素直接赋给父类引用
            Human human = it1.next();
            //这里可以直接调用父类human的方法，但是想要调用子类的方法还是需要转型的，主要是省略从 Object向下转 的过程
            human.exercise();
            if (human instanceof MaleMan) {
                MaleMan maleMan = (MaleMan) human;
                maleMan.run();
            }else {
                FemaleMan femaleMan = (FemaleMan) human;
                femaleMan.yujia();
            }

        }

        /**
         * JDK8 之后，引入了：自动类型推断机制（又称钻石表达式）
         */
        //ArrayList<这里的类型会进行自动推断>()，我们不用手动写了，List<Human>中有
        //ArrayList<>()，自动类型推断（钻石表达式）
        List<Human> list1 = new ArrayList<>();


        //new对象的时候指定泛型是：String类型
        Generic<String> generic = new Generic<>();

        //会报错，类型不匹配
        //generic.doSome(1023);

        generic.doSome("yuhang");//yuhang
    }

}

/**
 * 泛型类就是把泛型定义在类上，用户使用该类的时候，才把类型明确下来....这样的话，用户明确了什么类型，该类就代表着什么类型...用户在使用的时候就不用担心强转的问题，运行时转换异常的问题了。
 * 在类上定义的泛型，在类的方法中也可以使用！
 *
 *
 * 前面已经介绍了泛型类了，在类上定义的泛型，在方法中也可以使用.....
 * 现在呢，我们可能就仅仅在某一个方法上需要使用泛型....
 * 外界仅仅是关心该方法，不关心类其他的属性...
 * 这样的话，我们在整个类上定义泛型，未免就有些大题小作了。
 * 定义泛型方法....泛型是先定义后使用的
 * //定义泛型方法..
 * public <T> void show(T t) {
 *         System.out.println(t);
 *
 * }

 */
//这里的 T ，是个标识符，表明该类支持使用泛型机制，里面的 doSome 方法也的是 T 类型，比如这里T是String ，那么doSome方法中只能传String类型
class Generic<T> {
    public void doSome(T o) {
        System.out.println(o);
    }
}


class Human {
    public void exercise(){
        System.out.println("运动");
    }
}

class MaleMan extends Human {
    public void run(){
        System.out.println("男人在跑步");
    }
}

class FemaleMan extends Human {
    public void yujia(){
        System.out.println("女人在练习瑜伽");
    }
}

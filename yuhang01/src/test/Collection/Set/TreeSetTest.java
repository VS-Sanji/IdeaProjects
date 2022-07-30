package test.Collection.Set;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * TreeSet集合存储元素的特点
 *      无序不可重复的，但是存储的元素可以 自动 按照大小顺序排序，称为 可排序集合
 *      TreeSet集合底层实际上是一个TreeMap，而TreeMap集合底层是个二叉树
 *      放到TreeSet集合中的元素，等同于放到TreeMap集合key部分了
 */
public class TreeSetTest {
    public static void main(String[] args) {
        Set<String> strs = new TreeSet<>();

        strs.add("a");
        strs.add("d");
        strs.add("g");
        strs.add("y");
        strs.add("w");
        strs.add("j");
        //这个存不进去  重复了
        strs.add("a");

        for (String s : strs){
            System.out.println(s);
            //a
            //d
            //g
            //j
            //w
            //y
            //这个结果是从大到小排序过的，这里就按照字典顺序排列的
        }


        /**
         * 对于自定义的类型来说，想要达到排序的目的
         * 方法一：放在TreeSet集合中的元素需要实现java.lang.Comparable接口，并且实现compareTo方法。
         * 方法二:在构造TreeSet或者TreeMap集合的时候给他传一个比较器对象
         *
         * Comparable和Comparator怎么选择呢
         *      当比较规则不会轻易发生改变的时候，或者说当规则只有1个的时候，建议实现Comparable接口
         *      当规则有多个，且常需要在多个比较规则之间进行切换，建议使用Comparator接口
         */
        //一
        Monkey m1 = new Monkey(12);
        Monkey m2 = new Monkey(24);
        Monkey m3 = new Monkey(3);
        Monkey m4= new Monkey(4);

        //创建TreeSet集合
        TreeSet<Monkey> monkeysTreeSet = new TreeSet<>();

        //往集合中添加元素
        monkeysTreeSet.add(m1);
        monkeysTreeSet.add(m2);
        monkeysTreeSet.add(m3);
        monkeysTreeSet.add(m4);

        //遍历集合
        for (Monkey m : monkeysTreeSet){
            System.out.println(m);//没有实现Comparable接口时，出现java.lang.ClassCastException异常      test.Monkey cannot be cast to java.lang.Comparable
            //Monkey的age：3
            //Monkey的age：4
            //Monkey的age：12
            //Monkey的age：24
            //实现接口后，按照升序排列了
        }

        //二
        //TreeSet<Dog> dogs = new TreeSet<>();  这样不行，没有通过构造方法传一个比较器进去
        //注意：这里是一个匿名内部类，不是直接new接口，接口是不能直接new对象的，这里的new Comparator<Dog>() {...} 可以说是一个整合写法，可以分解成：
        // 首先new 了一个实现了Comparator接口的匿名内部类，然后new了一个该类的实例对象，最后还向上转型为它实现的接口类型
        //等价于如下形式：
        //              new TreeSet<>(new XXX)    这里XXX指代一个具体的类
        //      class XXX implements Comparator<Dog> {
        //          实现的抽象方法
        //      }
        TreeSet<Dog> dogs = new TreeSet<>(new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {
                return o1.age - o2.age;
            }
        });


    }
}


//方法一：放在TreeSet集合中的元素需要实现java.lang.Comparable接口，并且实现conpareTo方法。
//方法二:在构造TreeSet或者TreeMap集合的时候给他传一个比较器对象

class Monkey implements Comparable<Monkey>{
    private int age;

    public Monkey() {
    }

    public Monkey(int age) {
        this.age = age;
    }

    /**
     * 需要在这个方法中编写比较的逻辑，或者说比较的规则，说明按照什么进行比较
     * 将来会调用集合的compareTo方法，k.conpareTo(t.key)进行比较，拿着参数k和集合中的每一个key进行比较，返回值可能是 0< / >0 / =0
     * 比较规则由程序员定，比如升序、降序
     */
    @Override
    public int compareTo(Monkey o) {
        //升序
        return this.age - o.age;
        //降序
        //return o.age - this.age;
    }

    @Override
    public String toString() {
        return "Monkey的age：" + this.age;
    }
}

class Dog {
    int age;

    public Dog() {
    }

    public Dog(int age) {
        this.age = age;
    }
}
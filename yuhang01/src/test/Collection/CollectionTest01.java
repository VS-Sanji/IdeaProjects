package test.Collection;
/**
 * 1、集合概述
 *
 * 	1.1、什么是集合？有什么用？
 *
 * 		数组其实就是一个集合。集合实际上就是一个容器。可以来容纳其它类型的数据。
 *
 * 		集合为什么说在开发中使用较多？
 * 			集合是一个容器，是一个载体，可以一次容纳多个对象。
 * 			在实际开发中，假设连接数据库，数据库当中有10条记录，
 * 			那么假设把这10条记录查询出来，在java程序中会将10条
 * 			数据封装成10个java对象，然后将10个java对象放到某一个
 * 			集合当中，将集合传到前端，然后遍历集合，将一个数据一个
 * 			数据展现出来。
 *
 * 	1.2、集合不能直接存储基本数据类型，另外集合也不能直接存储java对象，
 * 	集合当中存储的都是java对象的内存地址。（或者说集合中存储的是引用。）
 * 		list.add(100); //自动装箱Integer
 * 		注意：
 * 			集合在java中本身是一个容器，是一个对象。
 * 			集合中任何时候存储的都是“引用”。
 *
 * 	1.3、在java中每一个不同的集合，底层会对应不同的数据结构。往不同的集合中
 * 	存储元素，等于将数据放到了不同的数据结构当中。什么是数据结构？数据存储的
 * 	结构就是数据结构。不同的数据结构，数据存储方式不同。例如：
 * 		数组、二叉树、链表、哈希表...
 * 		以上这些都是常见的数据结构。
 *
 * 		你往集合c1中放数据，可能是放到数组上了。
 * 		你往集合c2中放数据，可能是放到二叉树上了。
 * 		.....
 * 		你使用不同的集合等同于使用了不同的数据结构。
 *
 * 		你在java集合这一章节，你需要掌握的不是精通数据结构。java中已经将数据结构
 * 		实现了，已经写好了这些常用的集合类，你只需要掌握怎么用？在什么情况下选择
 * 		哪一种合适的集合去使用即可。
 *
 * 		new ArrayList(); 创建一个集合，底层是数组。
 * 		new LinkedList(); 创建一个集合对象，底层是链表。
 * 		new TreeSet(); 创建一个集合对象，底层是二叉树。
 * 		.....
 *
 * 	1.4、集合在java JDK中哪个包下？
 * 		java.util.*;
 * 			所有的集合类和集合接口都在java.util包下。
 *
 * 	1.5、为了让大家掌握集合这块的内容，最好能将集合的继承结构图背会！！！
 * 		集合整个这个体系是怎样的一个结构，你需要有印象。
 *
 * 	1.6、在java中集合分为两大类：
 * 		一类是单个方式存储元素：
 * 			单个方式存储元素，这一类集合中超级父接口：java.util.Collection;
 *
 * 		一类是以键值对儿的方式存储元素
 * 			以键值对的方式存储元素，这一类集合中超级父接口：java.util.Map;
 *
 * 2、总结重点：
 *
 * 	第一个重点：把集合继承结构图背会。
 *
 * 	第二个重点：把Collection接口中常用方法测试几遍。
 *
 * 	第三个重点：把迭代器弄明白。
 *
 * 	第四个重点：Collection接口中的remove方法和contains方法底层都会调用equals，这个弄明白。
 */

import java.util.ArrayList;
import java.util.Collection;

/**
 * 关于java.util.Collection接口中常用的方法
 *      1.Collection中能存放什么元素？
 *          没有使用“泛型”之前，Collection中可以存储Object的所有子类型
 *          使用了“泛型”之后，Collection中只能存放某个具体的类型
 *          集合中不能直接存储基本数据类型，也不能直接存储java对象，只是存储对象的内存地址，只能存 引用
 *      2.Collection中常用方法
 *          boolean add（Object e） 向集合中添加元素
 *          int size()  获取集合中元素的个数
 *          void clear() 删除集合中所有元素，清空集合
 *          boolean contains(Object o)  判断当前集合是否包含元素o,包含返回true，不包含返回false
 *          boolean remove(Object o)  删除集合中的元素o
 *          boolean isEmpty()  判断集合是否为空
 *          Object[] toArray()  将集合转换成数组
 */
public class CollectionTest01 {
    public static void main(String[] args) {
        //创建集合对象
        //Collection c = new Collection(); 行不通，接口不能直接new对象
        //多态
        Collection c = new ArrayList();
        //测试方法
        c.add(100);//自动装箱了，实际存储的是一个对象的内存地址  Integer x = new Integer(100);
        c.add(3.14);//自动装箱
        c.add(new Object());
        c.add(new String());
        c.add(true);//自动装箱

        //获取集合中元素的个数
        System.out.println("集合中元素个数是:" + c.size());//集合中元素个数是:5

        //清空集合
        c.clear();
        System.out.println("集合中元素个数是:" + c.size());//集合中元素个数是:0

        //再放元素进去
        c.add("hello world!!!");//"hello world!!!"对象的内存地址放进去了
        c.add('航');//char类型对象'航'自动装箱，内存地址放进去了

        //判断当前集合是否包含元素"hello world!!!"
        boolean b = c.contains("hello world!!!");
        System.out.println(b);//true
        boolean b1 = c.contains("yuhang");
        System.out.println(b1);//false

        //删除元素
        c.remove("hello world!!!");
        System.out.println("集合中元素个数是:" + c.size());//集合中元素个数是:1   证明已经删除了

        //判断集合是否为空
        System.out.println(c.isEmpty());//false
        c.remove('航');
        System.out.println(c.isEmpty());//true 表示集合已经被清空了

        //将集合转换成数组
        c.add("yuhang");
        c.add("zhanhui");
        c.add(123);
        c.add(234);
        c.add(false);
        Object[] objects = c.toArray();
        System.out.println(objects[2]);//123
        System.out.println(objects[3]);//234
    }
}

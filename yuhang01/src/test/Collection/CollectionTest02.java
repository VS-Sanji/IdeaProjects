package test.Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 关于集合遍历/迭代专题
 */
public class CollectionTest02 {
    public static void main(String[] args) {
        //以下讲解的遍历方式/迭代方式，所有Collection通用
        //在Map集合中不能用。在所有的Collection及其子类中使用
        //创建集合对象
        Collection c = new ArrayList();//后面的集合无所谓，主要是看前面的接口Collection，怎么遍历/迭代
        //添加元素
        c.add("宋宇航");
        c.add(123);
        c.add(123);
        c.add(true);
        c.add(false);
        //对集合Collection进行遍历/迭代
        //第一步：获取集合对象的迭代器Iterator对象
        Iterator iterator = c.iterator();

        //第二步：用获取的迭代器进行迭代/遍历集合
        /**
         * 主要用到两个方法
         * 1. boolean hasNext()  如果仍然有元素可以迭代，返回true
         * 2. Object next()  返回迭代的下一个元素
         */
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            System.out.println(obj);
            //宋宇航
            //123
            //123
            //true
            //false
            //有序可重复，存进去什么顺序，出来也是什么顺序，并且可重复
        }

        //HashSet集合;无序不可重复
        Collection c1 = new HashSet();
        // 无序：存进去和取出来的顺序不一定相同
        //不可重复：存储100，不能再存储100
        c1.add(100);
        c1.add(10);
        c1.add(200);
        c1.add(300);
        c1.add(40);
        c1.add(500);
        c1.add(100);
        int i = c1.size();
        System.out.println(i);//6  说明最后一个100并没有存进去
        Iterator iterator1 = c1.iterator();
        while(iterator1.hasNext()) {
            System.out.println(iterator1.next());
            //100
            //500
            //200
            //40
            //10
            //300
            //无序的，和存进去的顺序不一致
        }

        /**
         * 深入Collection集合的contains方法
         *      boolean contains(Object o)
         *      判断集合中是否包含某个对象
         *      如果包含返回true，否职返回false
         */
        //创建集合对象
        Collection c2 = new ArrayList();

        //向集合中存储元素
        String s = new String("abc");
        c2.add(s);

        //集合总元素个数
        System.out.println(c2.size());//2

        //新建对象String
        String s1 = new String("abc");

        //判断是否包含s2
        System.out.println(c2.contains(s1));
        //结果是：true
        /**
         * 分析源码，ArrayList 中的 contains() 调用了 indexOf(),而 indexOf() 又调用了 equals() 方法
         * 引用从contains()中一直往下传递，最后的结果是调用 引用.equals(数组[i]) 遍历比较，
         * 且比较的东西是由引用指向的对象是否重写了equals方法来决定的，重写了就比较内容，未重写就比较内存地址
         * 那么在这里是 引用（String类），重写了equals方法，比较的是内容而不是内存地址
         * 所以这里相当于是 “abc”.equals(数组[i]),而集合（数组）中存在引用指向“abc”，所以结果是true，证明集合中存在 “abc” 元素
         */

        //删除集合中的对象
        c2.remove(s1);
        System.out.println(c2.size());//0
        //证明这里s1就是s，这是因remove方法在底层也调用了equals方法进行判断比较，判定结果就是s1与s是相同对象，所以用remove方法删除集合中的s1即是删除了s
        /**
         * 所以以上证明，每写一个类都要重写他的equals方法。不然在以上这些情况下会出问题
         */



    }
}

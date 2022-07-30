package test.Collection.List.ArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * ArrayList集合：
 *      1.默认初始化容量10 (底层先创建了一个长度为0的数组，当添加第一的元素进来的时候，初始化容量10)
 *      2.集合底层是一个Ocject[]数组
 *      3.构造方法：
 *          new ArrayList()
 *          new ArrayList(20)
 *      4.ArrayList集合的扩容
 *          增长到原容量的1.5倍
 *          ArrayList集合的底层是数组，那么由于数组扩容的效率较低，建议在使用ArrayList集合的时候预估以下元素的个数，给定一个初始化容量
 *      5.数组优点：
 *          检索效率比较高
 *      6.数组缺点：
 *          随机增删元素效率比较低，但是注意，向数组末尾增删元素效率不受影响
 *          另外数组无法存储大数据量（很难找到一块非常巨大的连续的内存空间）
 *      7.面试官经常问的一个问题：
 *          这么多的集合中，哪个用的最多？
 *          答：ArrayList集合，因为向数组末尾添加元素效率不受影响，另外我们检索/查找某个元素的操作比较多
 *      8.ArrayList集合是非线程安全的
 */
public class ArrayListTest02 {
    public static void main(String[] args) {
        //默认初始化容量10
        List list = new ArrayList();
        System.out.println(list.size());;//结果：0  注意：集合的size()方法是获取当前集合中元素的个数，而不是获取集合的容量

        //指定初始化容量
        //数组长度是20
        List list1 = new ArrayList(20);


        /**
         * 关于ArrayList集合的构造方法
         */
        //默认初始化容量10
        List myList1 = new ArrayList();

        //指定初始化容量
        List myList2 = new ArrayList(123);

        //创建一个HashSet集合
        Collection c = new HashSet();
        //添加元素
        c.add(123);
        c.add(true);
        c.add(false);

        //通过这个构造方法可以将HashSet集合转换成List集合
        List myList3 = new ArrayList(c);
        for (int i = 0; i < myList3.size(); i++) {
            System.out.println(myList3.get(i));
            //0
            //false
            //123
            //true


        }
    }
}

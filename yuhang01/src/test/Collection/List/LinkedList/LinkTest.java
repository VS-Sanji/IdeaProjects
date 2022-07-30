package test.Collection.List.LinkedList;

import java.util.LinkedList;
import java.util.List;

/**
 * 单向链表中的节点
 * 节点是单向链表中基本的单元
 * 每一个节点Node都有两个属性:
 *      属性一：存储的数据
 *      属性二：下一个节点的内存地址
 * 链表的优点：
 *      由于链表上的元素在空间存储上内存地址不连续，所以随即增删元素的时候不会有大量元素位移，因此随即增删效率较高
 *      在以后的开发中，如果遇到随即增删集合中元素的业务比较多时，建议使用LinkedList
 * 链表的缺点：
 *      不能通过数学表达式计算被查找元素的内存地址，每一次查找都是从头节点开始遍历，直到找到为止，
 *      所以LinkedList集合检索/查找的效率较低（LinkedList也是有下标的，但是检索效率还是比较低，这跟它的数据结构有关系）
 *
 *      ArrayList：把检索发挥到极致。（末尾添加元素效率还是很高的）
 *      LinkedList：把随机增删发挥到极致
 *      加元素都是往末尾加，所以ArrayList用的比LinkedList多
 *
 * 双向链表
 *
 *
 */
public class LinkTest {
    public static void main(String[] args) {
        //LinkedList集合没有初始化容量，最初这个链表中没有任何元素，first 和 last 引用中都是null
        //不管是LinkedList还是ArrayList，以后写代码时不需要关心具体是那个集合，因为我们要面向接口编程，调用的都是接口中的方法
        //List list = new ArrayList();  这样写表示在底层使用了 数组数据结构
        List list = new LinkedList();   //这样写表示在底层使用了 双向链表数据结构
        list.add(23);
        list.add(3);
        list.add("yuhang");
        //面向接口编程
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            //23
            //3
            //yuhang
        }

    }
}

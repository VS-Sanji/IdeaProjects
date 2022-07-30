package test.Collection.List.LinkedList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 关于List接口中常用方法
 *      1.list集合存储元素的特点： 有序可重复
 *      有序：list集合的元素有索引   从0开始，以1递增
 *      可重复：存储一个1，还可以再存储一个1
 *
 *      2.list是Collection接口的子接口，他有自己较为 “特色” 的方法
 *      void add(int index, E element)
 *      E get(int index)
 *      int indexOf(Object o)
 *      int lastIndexOf(Object o)
 *      E remove(int index)
 *      E set(int index, E element)
 *
 */
public class ListTest {
    public static void main(String[] args) {
        //创建list集合
        List list = new ArrayList();

        //添加元素
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(true);

        list.add(2,"songyuhang");//在索引为2的位置上加入元素"songyuhang"（内存地址），然后后面的元素的索引统一向后位移一位

        System.out.println(list.get(4));;//获取当前索引下的元素，即下标为4的元素   结果：1

        System.out.println(list.indexOf(true));;//获取当前元素在集合中第一次出现时的索引   结果：5

        System.out.println(list.lastIndexOf(1));;//获取当前元素在集合中最后一次出现时的索引   结果：4

        list.remove(5);//移除集合中当前索引下的元素

        list.set(3,false);//将集合中当前索引下的元素修改为所传入的值，注意索引不要越界


        //获取迭代器
        Iterator iterator = list.iterator();
        //迭代集合
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            System.out.println(obj);
            //1
            //2
            //songyuhang
            //false
            //1
        }

        /**
         * 计算机英语：增删改查
         *      增：add，save，new
         *      删：delete，drop，remove
         *      改：update，set，modify
         *      查：find，get，query，select
         */
    }
}

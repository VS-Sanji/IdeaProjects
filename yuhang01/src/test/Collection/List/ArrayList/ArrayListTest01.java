package test.Collection.List.ArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 关于集合中的remove
 */
public class ArrayListTest01 {
    public static void main(String[] args) {
        //创建集合
        Collection c = new ArrayList();

        /**
         * 在这里获取迭代器，等于说集合还没有添加元素进去，该迭代器指向的就是一个空的集合（没有元素的集合）
         * 注意：当一个集合结构发生改变时，一定要重新获取迭代器
         * 在创建迭代器对象的时候，该迭代器会记录下当前集合的结构，遍历集合的时候会按照其所记录的结构进行。
         * 当调用  集合.remove 方法去删除集合中的元素时，就改变了集合的结构，但是此时迭代器中所记录的结构还是未删除之前的那个，此时再用该迭代器去遍历集合就会出现异常
         * 而调用  迭代器.remove 方法去删除集合中的元素时，会将集合中的元素删除，同时也会将迭代器所记录的对应元素删除，那么就保持了迭代器所记录的集合结构与当前集合的结构相同，相当于重新获取了迭代器，迭代器获取了“更新”
         * 重点：在迭代集合元素的过程当中，不能调用集合对象的remove方法，删除元素。即 集合.remove() 在迭代过程中不能这样做，不能使用集合自身的remove方法，要使用迭代器Iterator的remove方法
         */
        Iterator it = c.iterator();

        //添加元素
        c.add(123);//Iterator
        c.add(234);
        c.add(345);

/*        //迭代集合
        while (it.hasNext()) {
            Object obj = it.next();
            System.out.println(obj);//这里集合由空变成存储了三个元素的状态，但是迭代器没有重新获取，所以输出异常信息 java.util.ConcurrentModificationException
        }*/

        //在这里重新获取迭代器
        Iterator it1 = c.iterator();
        //重新迭代/遍历集合
        while (it1.hasNext()) {
            Object obj1 = it1.next();
            //c.remove() 假如在这里删除了集合中的元素，就改变了集合的结构。
            // 但是在进行下一轮循环时，这个迭代器还是 it1 ，没有重新获取，则会出现异常java.util.ConcurrentModificationException
            /**重点：在迭代集合元素的过程当中，不能调用集合对象的remove方法，删除元素。即 c.remove() 在迭代过程中不能这样做
             *但是，我们可以采取另一种方法。--> 使用迭代器Iterator的remove去删除  即：it1.remove(), 这样可行，在这种方法删除了集合中的元素之后，迭代器也会进行“更新”
             */
            it1.remove();//这里的remove方法 作用的对象一定是当前迭代器指向的元素
            System.out.println(obj1);
            System.out.println(c.size());
            //123
            //2
            //234
            //1
            //345
            //0
            //输出结果证明迭代器已经更新，没有出现异常，且用 迭代器.remove 方法成功的删除了集合中的元素，并且更新了迭代器
        }
    }
}

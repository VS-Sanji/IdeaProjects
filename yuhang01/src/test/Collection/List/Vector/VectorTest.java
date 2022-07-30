package test.Collection.List.Vector;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Vector:
 *      1.底层也是个数组
 *      2.初始化容量10
 *      3.关于扩容，当被填满时，扩容为原本的2倍
 *      4.ArrayList集合扩容时，扩容时为原本的1.5倍
 *      5.Vector集合中所有的方法都是线程同步的，都带有synchronized关键字，是线程安全的。效率比较低，使用较少
 */
public class VectorTest {
    public static void main(String[] args) {
        List vector = new Vector();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        vector.add(4);
        vector.add(5);
        vector.add(6);
        vector.add(7);
        vector.add(8);
        vector.add(9);
        vector.add(10);
        //往下扩容了
        vector.add(11);
        vector.add(12);

        Iterator it = vector.iterator();
        while (it.hasNext()){
            Object obj = it.next();
            System.out.println(obj);
            //1
            //2
            //3
            //4
            //5
            //6
            //7
            //8
            //9
            //10
            //11
            //12
        }

    }
}

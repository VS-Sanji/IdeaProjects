package cn.itcast.algorithm.sort;

/**
 * 选择排序
 * 排序原理：
 * 1.每一次遍历的过程中，都假定剩余第一个索引处的元素是最小值，和其他索引处的值依次进行比较，如果当前索引处的值大于其他某个索引处的值，则假定其他某个索引处的值为最小值，最后可以找到最小值所在的索引
 * 2.交换第一个索引处和最小值所在索引处的值
 * 时间复杂度O(n2)
 */
public class Select {

    public static void sort(Comparable[] a) {
/*        //外层循环，每一次选择排序得出一个“最小值”
        for (int i = 0; i < a.length - 1; i++) {
            //内层循环，循环比较过程,若后者比剩余第一个小，则交换
            for (int j = i + 1; j < a.length; j++) {
                if (greater(a[i], a[j])) {
                    exch(a, i, j);
                }
            }
        }*/

        //另一种理解：用索引来标记最小
        int minIndex;
        for (int i = 0; i < a.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (greater(a[minIndex], a[j])) {
                    minIndex = j;
                }
            }
            exch(a, i, minIndex);

        }

    }

    //比较v元素与w元素的大小
    //大于0则表示v比w大
    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    //数组元素i和j交换位置
    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

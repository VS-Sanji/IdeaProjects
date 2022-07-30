package cn.itcast.algorithm.sort;

/**
 * 冒泡排序算法
 * 排序原理：
 * 1.比较相邻的元素。如果前一个元素比后一个元素大，就交换这两个元素的位置
 * 2.对每一对相邻元素做同样的工作，从开始第一对元素到结尾的最后一对元素。最终最后位置的元素就是最大值
 * 时间复杂度O(n2)
 */
public class Bubble {
    //排序:对数组a中元素进行排序
    public static void sort(Comparable[] a) {
        //外循环，表示的是冒泡排序执行次数，与数组长度相关
        for (int i = 0; i < a.length; i++) {
            //内循环，数组元素之间的比较
            //数组下标每次都从0开始，但由于每次都会比较出一个较大的值，而这个值在下一次冒泡中不参与进来，所以下标有限制
            for (int j = 0; j < a.length - (i + 1); j++) {
                if (greater(a[j], a[j + 1])) {
                    exch(a, j, j + 1);
                }
            }
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

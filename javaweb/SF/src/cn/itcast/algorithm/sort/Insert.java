package cn.itcast.algorithm.sort;

/**
 * 插入排序
 * 排序原理；
 * 1.把所有元素分为两组，已经排序和未排序的
 * 2.找到未排序的组中的第一个元素，向已排序的组中进行掺入
 * 3.倒叙遍历已经排序的元素，一次和待插入的元素进行比较，指导找到一个元素小于等于待插入元素，那么就把待插入元素放到这个位置，其他已排序元素向后移动一位
 */
public class Insert {
    public static void sort(Comparable[] a) {
        //外层循环，表示需要循环多少次才能排完
        for (int i = 1; i < a.length; i++) {
            //j表示未排序的首元素下标，每结束一次增加一
            for (int j = i; j > 0; j--) {
                if (greater(a[j - 1], a[j])) {
                    exch(a, j - 1, j);
                } else {
                    break;
                }
            }
        }

    }


    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

package test.SuanFa;
/**
 * 冒泡排序的算法是这样的：
 * 首先从数组的最左边开始，取出第 0 号位置（左边）的数据和第 1号位置（右边）的数据，
 * 如果左边的数据大于右边的数据，则进行交换，否而不进行交换。
 * 接下来右移一个位置，取出第 1 个位置的数据和第 2 个位置的数据，进行比较，
 * 如果左边的数据大于右边的数据，则进行交换，否而不进行交换。
 * 沿着这个算法一直排序下去，最大的数就会冒出水面，这就是冒泡排序。
 */
public class BubbleTest {
    public static void main(String[] args) {
        //数组array有7个数，则数组下标为 0，1，2，3，4，5，6
        int[] array = {1,23,4,5,5,2,33};
        //7个数用冒泡排序算法排序，需要进行6次外循环（除了最后两个数的比较，一次循环比较得出一个最大数，则7个数中的5个要5次，最后2个数循环一次，共6次）
        for (int i = 0; i < array.length; i++) {
        }
        for (int i = array.length - 1; i > 0 ; i--) {

        }
        //外循环i从 0 --> 5 共6次
        //内循环i从 6 --> 1 共6次
        //第一次 内循环6次 内循环的 j 表示数组每个下标的话,就从 0 开始,则应该用 i 来控制 j 的范围, j 从 0 --> 5 ,即 j < i,所以选择后者循环方式
        for (int i = array.length - 1; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]){
                    //创建局部变量暂存数组中的数组
                    int temp;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j +1] = temp;
                }
            }
        }

        for (int i = 0; i < array.length - 1; i++) {
            System.out.println(array[i]);
        }




    }
}

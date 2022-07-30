package test.SuanFa;

/**
 * 选择排序对冒泡排序进行了改进，使交换次数减少，但比较次数仍然没有减少。
 * 假设有 5 个数字 3，1，6，2，5 在一个 int 数组中，要求按从小到大排序输出
 * 采用选择排序，选择排序是这样的，先从左端开始，找到下标为 0 的元素，然后和后面的元素
 * 依次比较，如果找到了比下标 0 小的元素，那么再使用此元素，再接着依次比较，直到比较完成所有的元素，最后把最小的和第 0 个位置交换。
 */
public class SelectTest {
    public static void main(String[] args) {
        //数组中6个元素,下标从 0 --> 5
        int[] array = {1,2,4,5,6,2};
        for (int i = 0; i < array.length; i++) {
            //假设一开始最左边的最小,则下标为i min存储最小值对应的下标
            int min = i;
            //找到最小值对应存储空间的下标
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]){
                    min = j;
                }

            }
//            //创建一个temp变量来暂时存储值
//            int temp;
//            //将每次比较完后的第一个数暂存在temp中
//            temp = array[i];
//            //将找到的最小值赋值到每次的首个位置上
//            array[i] = array[min];
//            //将暂存的值传到最小值的空间上,完成调换
//            array[min] = temp;

            //可以加一个判断,这样可以提高效率,如果最小值的小标就是i,就不用执行以下代码了
            if (min != i) {
                //创建一个temp变量来暂时存储值
                int temp;
                //将每次比较完后的第一个数暂存在temp中
                temp = array[i];
                //将找到的最小值赋值到每次的首个位置上
                array[i] = array[min];
                //将暂存的值传到最小值的空间上,完成调换
                array[min] = temp;
            }

        }

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}

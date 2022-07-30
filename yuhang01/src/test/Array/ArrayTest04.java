package test.Array;
/**
 *二维数组是特殊的一维数组，只不过其中的元素是个一维数组（即存着一维数组的地址）
 *三维数组又是特殊的二维数组
 * 二维 {{1，2}，{1，2}}
 *
 * 三维 {
 * {{1，2，3}，{1，2，3}}，
 * {{1，2，3}，{1，2，3}}
 * }
 */
public class ArrayTest04 {
    public static void main(String[] args) {
        //一维数组
        int[] a = {2,34,5,6};

        //二维数组
        //里面是三个一维数组
        int[][] b = {
                {1,2,3,4},
                {1,4,5},
                {12,2,4,5,5,6,2}
        };
        System.out.println(b.length);  //二维数组的长度  3
        System.out.println(b[1].length);  //二维数组的第二个一维数组的长度  3

        //读取二维数组中的数据
        //表示打印二维数组中第三个一维数组第四个数  5
        System.out.println(b[2][3]);

        //修改二维数组中的数据
        //表示将2345赋值给二维数组中的第三个一维数组中的第四个元素
        b[2][3] = 7;

        //二维数组的遍历，用到嵌套for循环
        /**
        for (int i = 0; i < b.length; i++) {   //外循环，二维数组b有三个元素，循环三次
            for (int j = 0; j < b[i].length; j++) {   //内循环，二维数组中一维数组的遍历
                System.out.println(b[i][j]);
            }
            System.out.println();
        }
         */

        printArray(b);
    }

    //写一个遍历二维数组并打印的方法
    public static void printArray(int[][] array){    //传一个二维数组进来
        for (int i = 0; i < array.length; i++) {   //外循环，二维数组b有array.length个元素，循环array.length次
            for (int j = 0; j < array[i].length; j++) {   //内循环，二维数组中一维数组的遍历
                System.out.println(array[i][j]);
            }
            System.out.println();
        }
    }
}

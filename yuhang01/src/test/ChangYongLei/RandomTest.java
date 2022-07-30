package test.ChangYongLei;

import java.util.Random;

/**
 * 随机数
 * 4.1、怎么产生int类型随机数。
 * 		Random r = new Random();
 * 		int i = r.nextInt();
 * 	4.2、怎么产生某个范围之内的int类型随机数。
 * 		Random r = new Random();
 * 		int i = r.nextInt(101); // 产生[0-100]的随机数。
 */
public class RandomTest {
    public static void main(String[] args) {
        //创建随机数对象
        Random random = new Random();

        //随机产生一个int类型数据
        int i = random.nextInt();
        System.out.println(i);//1311018701

        //生成一个[0 ~ 100]之间的随机数，不包括101
        //nextInt（bound 101） 翻译为下一个int类型的数据是101，而不包括101
        int j = random.nextInt(101);//不包括101 返回一个伪随机的、均匀分布在0(包括)和指定值(不包括)之间的int值
        System.out.println(j);//46

        /**
         * 编写程序，生成5个不重复的随机数，重复的话重新生成
         * 最终生成的5个随机数存到数组中
         */
//        int i1 = getRandom();
//        int i2 = getRandom();
//        int i3 = 0;
//        int i4 = 0;
//        int i5 = 0;
//        while (true) {
//            if (i1 == i2) {
//                i2 = getRandom();
//                continue;
//            }else {
//                i3 = getRandom();
//            }
//            if (i1 == i3 || i2 == i3) {
//                i3 = getRandom();
//                continue;
//            }else {
//                i4 = getRandom();
//            }
//            if (i1 == i4 || i2 == i4 || i3 == i4) {
//                i4 = getRandom();
//                continue;
//            }else {
//                i5 = getRandom();
//            }
//            if (i1 == i5 || i2 == i5 || i3 == i5 || i4 == i5) {
//                i5 = getRandom();
//                continue;
//            }
//            break;
//        }
//        int[] ints = {i1 , i2 , i3 , i4 , i5};
//        for (int k = 0; k < ints.length; k++) {
//            System.out.println(ints[k]);
//        }



        //创建长度为5的int数组
        int[] ints1 = new int[5];
        for (int k = 0; k < ints1.length; k++) {
            ints1[k] = -1;
        }
        //下标
        int index = 0;
        //循环
        while (true) {
            //随机数
            int num = getRandom();
            //if的括号内，判断数组中有无该元素，需要遍历数组比较，可以考虑写一个方法
            if (!contains(ints1, num)){
                ints1[index] = num;
                index++;
            }
            //下标到5结束循环
            if (index == 5) {
                break;
            }
        }
        for (int k = 0; k < ints1.length; k++) {
            System.out.println(ints1[k]);
        }
    }


    //生成随机数
    public static int getRandom(){
        Random random = new Random();
        int i = random.nextInt(6);
        return i;
    }
    //该方法判断数组是否包含目标元素
    public static boolean contains(int[] arr, int key){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return true;
            }
        }
        return false;
    }
}

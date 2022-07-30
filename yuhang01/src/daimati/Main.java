package daimati;

import java.util.Scanner;

/**
 * 读入一个正整数 n，计算其各位数字之和，用汉语拼音写出和的每一位数字。
 */


public class Main {
    public static void main(String[] args) {
//        //正整数n
//        int n  = 12345678;
//        //余数
//        int a;
//        //各位数之和
//        int sum= 0;
//        //取出各位数
//        while (true) {
//            if (n < 10) {
//                sum += n;
//                break;
//            }else {
//                a = n % 10;
//                sum += a;
//                n = (n - a) / 10;
//                continue;
//            }
//
//        }
        Scanner scanner = new Scanner(System.in);
        String n = scanner.nextLine();
        int sum = 0;
        for (int i = 0; i < n.length(); i++) {
            sum += n.charAt(i) - '0';          //字符串是个char数组对象，里面存储的都是char数据，我们需要的是整型数据，所以可以做一个char数据的运算，然后得到的就是int了（整型运算规则，有long则结果是long，其他的运算结果都是int）
        }

        String[] strings = {"ling" , "yi" , "er" , "san" , "si" ,"wu" , "liu" , "qi" , "ba" , "jiu"};
        //将各位数之和转成字符串
        String s = String.valueOf(sum);
        //位数
        int i = s.length();
        String[] strings1 = new String[i];
        if (sum < 10) {
            for (int j = 0; j < 10; j++) {
                if (sum == j) {
                    System.out.println(strings[j]);
                }
            }
        }
        if (sum >= 10) {
            //余数
            int b;
            while (true) {
                b= sum % 10;
                for (int k = 0; k < 10; k++) {
                    if (b == k) {
                        strings1[i - 1] = strings[k];
                        i--;
                        break;
                    }

                }
                sum = (sum - b) / 10;
                if (i == 0) {
                    break;
                }

            }
            for (int l = 0; l < strings1.length; l++) {
                if (l != strings1.length - 1) {
                    System.out.print(strings1[l] + " ");
                }else {
                    System.out.print(strings1[strings1.length - 1]);
                }

            }
        }

    }
}

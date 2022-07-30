package com.bjpowernode.array;


/**
 * 二维数组 转 稀疏数组的思路
 * 1.遍历 原始的二维数组，得到有效数据的个数 sum
 * 2.根据 sum 就可以创建 稀疏数组spareseArr int[sum + 1][3]
 * 3.将二维数组的有效数据存入到稀疏数组
 * <p>
 * 稀疏数组转原始的二维数组的思路
 * <p>
 * 1.先读取稀疏数组的第0行（这里的0表示下标），根据这一行的数组，创建原始的二维数组
 * 2.在读取稀疏数组后几行的数据，并赋给 原始的二维数组即可
 */

/**
 * 将一个二维数组压缩成稀疏数组
 * 1.这个稀疏数组的第一行第一列表示二维数组有多少行，
 * 2.这个稀疏数组的第一行第二列表示二维数组有多少列
 * 3.这个稀疏数组的第一行第二列表示二维数组中有效数组个数
 */
public class SparseArray {


    public static void main(String[] args) {

        /**
         * 1.新建一个二维数组
         * 2.给某些元素赋值
         */
        int[][] dArr = new int[11][11];
        dArr[1][2] = 2;
        dArr[2][3] = 3;

        //打印这个二维数组
        for (int[] a : dArr) {
            for (int i : a) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }

        //遍历二维数组，拿到有效数据个数
        int count = 0;
        for (int i = 0; i < dArr.length; i++) {
            for (int j = 0; j < dArr[i].length; j++) {
                if (dArr[i][j] != 0) {
                    count++;
                }
            }
        }

        //新建一个二维数组，即稀疏数组压缩二维数组，这个稀疏数组的行数是可以确定的，总是等于有效数据个数加一
        //列数是固定值，就三列
        int[][] sparseArrays = new int[count + 1][3];
        sparseArrays[0][0] = 11;
        sparseArrays[0][1] = 11;
        sparseArrays[0][2] = count;

        int no = 0;
        for (int i = 0; i < dArr.length; i++) {
            for (int j = 0; j < dArr[i].length; j++) {
                if (dArr[i][j] != 0) {
                    no++;
                    sparseArrays[no][0] = i;
                    sparseArrays[no][1] = j;
                    sparseArrays[no][2] = dArr[i][j];

                }

            }

        }

        //打印稀疏数组
        for (int i = 0; i < sparseArrays.length; i++) {
            for (int j = 0; j < sparseArrays[i].length; j++) {
                System.out.print(sparseArrays[i][j] + "\t");

            }
            System.out.println();
        }

        //从稀疏数组还原出二维数组
        int[][] dArr1 = new int[sparseArrays[0][0]][sparseArrays[0][1]];
        for (int i = 1; i <= sparseArrays[0][2]; i++) {
            dArr1[sparseArrays[i][0]][sparseArrays[i][1]] = sparseArrays[i][2];
        }

        //打印这个还原出来的二维数组
        for (int[] a : dArr1) {
            for (int i : a) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }


    }
}

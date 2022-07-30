package com.bjpowernode.Recursion;

/**
 * 递归
 * 1.执行一个方法时，就会创立一个新的受保护的独立空间
 * 2.方法的局部变量是独立的，不会相互影响
 * 3.如果方法中使用的是引用类型变量，就会共享该引用类型的数据，如下例中的map二维数组
 * 4.递归必须向退出递归的条件逼近，否则就是无限递归了，死龟了
 * 5.当一个方法执行完毕，或者遇到return，就会返回，遵守谁调用，就将结果返回给谁，同时当方法执行完毕或者返回时，该方法也就执行完毕
 */
public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];

        //设置边框为1，表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[2][1] = 1;
        map[2][2] = 1;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

        setWay(map, 1, 1);

        System.out.println("------------------------------------------------");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来给小球找路
     * 说明：
     * 1.map 表示地图
     * 2.i，j表示从地图的哪个位置开始出发
     * 3.如果小球能到map[6][5]位置，则说明通路找到
     * 4.约定：当map[i][j]为0表示该点没有被走过（代码执行中会将走过的标记为其他数字），1表示墙，2表示通路可以走，3表示该点已经走过，但是走不通
     * 5.在走迷宫时，需要确定一个策略（方法） 下->右->上->左，如果该点走不通，再回溯
     *
     * @param map 表示地图
     * @param i   从哪个位置开始走
     * @param j
     * @return 如果找到通路，就返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { //通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) { //如果当前这个点还没有走过
                map[i][j] = 2; //假设该点可以走通
                if (setWay(map, i + 1, j)) { //向下走，如果返回true，则表明一直往下 递 的过程最终能够将map[6][5] 设成2，是一条通路
                    return true;
                } else if (setWay(map, i, j + 1)) { //向右走，如果返回true，则表明一直往下 递 的过程最终能够将map[6][5] 设成2，是一条通路
                    return true;
                } else if (setWay(map, i - 1, j)) { //向上走，如果返回true，则表明一直往下 递 的过程最终能够将map[6][5] 设成2，是一条通路
                    return true;
                } else if (setWay(map, i, j - 1)) { //向左走，如果返回true，则表明一直往下 递 的过程最终能够将map[6][5] 设成2，是一条通路
                    return true;
                } else {
                    map[i][j] = 3; //能执行到这说明不能走到map[6][5],无法执行 退出递归的条件，将该点重设为3,说明从这个点不能走到终点
                    return false;
                }
            } else { //map[i][j] != 0,可能是1，2，3
                return false;
            }
        }
    }
}

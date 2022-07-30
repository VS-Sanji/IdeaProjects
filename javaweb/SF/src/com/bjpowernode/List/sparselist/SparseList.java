package com.bjpowernode.List.sparselist;

/**
 * 用链表实现将二维数组压缩
 */
public class SparseList {

    private static Node head = new Node(-1, -1, -1);

    public static void main(String[] args) {

        /**
         * 创建一个二维数组
         * 给某些元素赋值
         */
        int[][] dArr = new int[7][7];
        dArr[3][4] = 23;
        dArr[2][5] = 33;
        dArr[6][6] = 77;

        /**
         * 输出一下二维数组
         */
        for (int i = 0; i < dArr.length; i++) {
            for (int j = 0; j < dArr[i].length; j++) {
                System.out.print(dArr[i][j] + "\t");
            }
            System.out.println();
        }

        /**
         * 遍历整个二维数组，拿到有效数据个数
         */
        int count = 0;
        for (int i = 0; i < dArr.length; i++) {
            for (int j = 0; j < dArr[i].length; j++) {
                if (dArr[i][j] != 0) {
                    count++;
                }
            }
        }

        /**
         * 根据有效数据个数即可创建 count + 1 个节点 (整个1是用来存储对应的二维数组规格）
         */
        Node node = new Node(7, 7, count);
        head.setNext(node);
        Node temp = node;
        for (int j = 0; j < dArr.length; j++) {
            for (int k = 0; k < dArr[j].length; k++) {
                if (dArr[j][k] != 0) {
                    Node node1 = new Node(j, k, dArr[j][k]);
                    temp.setNext(node1);
                    temp = temp.getNext();
                }
            }
        }

        /**
         * 遍历整个链表，输出压缩后的数据
         */
        Node cur = head;
        for (int i = 0; i < count + 1; i++) {
            System.out.println(cur.getNext().getRow() + " " + cur.getNext().getCol() + " " + cur.getNext().getData());
            System.out.println();
            cur = cur.getNext();
        }

        /**
         * 将稀疏链表恢复成二维数组
         */
        int[][] dArr1 = new int[head.getNext().getRow()][head.getNext().getCol()];
        Node cur1 = head.getNext().getNext();
        while (true) {
            if (cur1 == null) {
                break;
            }
            int row = cur1.getRow();
            int col = cur1.getCol();
            int data = cur1.getData();
            dArr1[row][col] = data;
            cur1 = cur1.getNext();
        }

        /**
         * 重新输出恢复的二维数组
         */
        for (int i = 0; i < dArr1.length; i++) {
            for (int j = 0; j < dArr1[i].length; j++) {
                System.out.print(dArr1[i][j] + "\t");
            }
            System.out.println();
        }

    }

}

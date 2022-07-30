package com.bjpowernode.List.circle;

public class CircleSingleLinkedList {
    //first指向第一个节点
    Boy first = null;

    /**
     * 构建环形链表
     */
    public void addBoy(int nums) {
        //临时节点
        Boy temp = null;
        //循环构建环形链表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = new Boy(i);
                first.setNext(first);
                temp = first;
            } else {
                temp.setNext(boy);
                boy.setNext(first);
                temp = boy;
            }
        }
    }

    /**
     * 查看环形链表
     */
    public void showBoy() {
        Boy temp = first;
        if (temp.getNext() == first) {
            System.out.println(temp);
            return;
        }
        while (true) {
            if (temp.getNext() == first) {
                System.out.println(temp);
                break;
            }
            System.out.println(temp);
            temp = temp.getNext();
        }
    }

    /**
     * 完成次序输出
     *
     * @param start 起始的节点 从哪个小孩开始报数
     * @param count 每次喊到多少出队
     * @param nums  圈中原本有多少个小孩
     */
    public void countBoy(int start, int count, int nums) {
        if (first == null || nums < 1 || count > nums) {
            System.out.println("wrong");
            return;
        }
        Boy helper = first;
        //保证helper目前的下一个节点一定指向first节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //根据传进来的start更改目前first的指向，将变更所指向的节点作为起始节点,重设以哪个小孩开始
        for (int i = 1; i < start; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //出队
        while (true) {
            if (first == helper) {
                System.out.println("小孩:" + first.getNo() + "出队");
                break;
            }
            for (int i = 0; i < count - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("小孩:" + first.getNo() + "出队");
            helper.setNext(first.getNext());
            first = first.getNext();

        }

    }

}

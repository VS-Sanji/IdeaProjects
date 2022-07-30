package com.bjpowernode.List.yuesefu;

/**
 * 思路:
 * 1.创建一个辅助指针helper，事项应该指向环形链表的最后一个节点，即first的前一个节点
 * 2.重新定位first指针所指节点，即从哪里开始数，需要将first指针和helper均移动 start - 1次
 * 3.根据所报的数字countNum，让first和helper同时移动 countNum - 1 次
 * 4.这时first所指的小孩出圈
 * first = first.getNext();
 * helper = setNext(first);
 * 5.最终first和helper指向同一个节点时，即为最后一个出圈节点，循环结束
 */

public class Josepfu {

    public static void main(String[] args) {
        //测试 构建环形链表 和 遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
        circleSingleLinkedList.showGirl();
        circleSingleLinkedList.countGirl(2, 3, 5);

    }


}

//创建一个环形单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Girl first = new Girl(-1);

    //添加小孩节点，构建成一个环形链表
    public void add(int nums) {
        //校验
        if (nums < 1) {
            System.out.println("nums的值是非法的");
            return;
        }
        Girl temp = null;
        for (int i = 1; i <= nums; i++) {
            Girl girl = new Girl(i);
            if (i == 1) {
                first = girl;
                first.setNext(first);//构成环
                temp = first;
            } else {
                temp.setNext(girl);
                girl.setNext(first);//构成环
                temp = girl;//后移
            }
        }
    }

    //遍历当前的环形链表
    public void showGirl() {
        if (first == null) {
            System.out.println("没有小孩");
            return;
        }
        //因为first不能动，所以需要辅助指针来帮助遍历
        Girl temp = first;
        while (true) {
            System.out.println(temp);
            if (temp.getNext() == first) {//遍历完毕
                break;
            }
            temp = temp.getNext();//后移
        }
    }

    //根据用户的输入，计算小孩出圈的顺序

    /**
     * 顺序出圈
     *
     * @param start    表示从第几个小孩开始
     * @param countNum 表示数几下
     * @param nums     表示最初有多少个小孩在圈中
     */
    public void countGirl(int start, int countNum, int nums) {
        if (first == null || start < 1 || countNum > nums) {
            System.out.println("不满足运行要求");
            return;
        }
        Girl helper = first;
        while (true) {//死循环将helper指向first的前一个位置，这样即为初始状态，（这两个指针始终保持这样的关系）
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        for (int i = 0; i < start - 1; i++) {//根据传进来的参数重新确定first和helper的指向（即从哪个小孩开始）
            first = first.getNext();
            helper = helper.getNext();
        }
        while (true) {
            if (first == helper) {
                System.out.println(first);
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println(first);
            first = first.getNext();
            helper.setNext(first);//最后通过这一步会将helper也指向first所指向的节点，即两者表示同一个节点
        }
    }
}

//创建一个girl类
class Girl {
    private int no;//编号
    private Girl next;//下一个节点

    public Girl getNext() {
        return next;
    }

    public void setNext(Girl next) {
        this.next = next;
    }

    public Girl(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "no=" + no +
                '}';
    }
}
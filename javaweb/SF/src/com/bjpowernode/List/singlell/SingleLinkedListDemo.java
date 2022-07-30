package com.bjpowernode.List.singlell;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {

        //测试
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "时迁", "鼓上蚤");
        HeroNode heroNode4 = new HeroNode(4, "武松", "武大郎");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode4);

        singleLinkedList.list();

        HeroNode heroNode5 = new HeroNode(1, "宇航", "java小子");
        singleLinkedList.update(heroNode5);

        singleLinkedList.list();

        singleLinkedList.delete(1);

        singleLinkedList.list();

        int length = getLength(singleLinkedList.head);
        System.out.println(length);

        HeroNode lastIndexHeroNode = getLastIndexHeroNode(singleLinkedList.head, 2);
        System.out.println(lastIndexHeroNode);

        SingleLinkedList rList = reverseList(singleLinkedList);
        rList.list();

        reversePrint(singleLinkedList);

    }


    //-------------------------------------------------------------------------------------------------------------------------
    //将单链表从尾到头打印（百度面试题）
    //思路
    //1.上面得题的要求就是进行逆序打印单链表
    //2.方式1：先将单链表进行反转操作，然后再遍历即可，这样做的问题就是会破坏原来的单链表的结构，不建议
    //3.方式2：可以利用栈这个数据结构，将各个节点压入到 栈 中，然后利用栈的 先进后出 特点，就实现了逆序打印的效果
    public static void reversePrint(SingleLinkedList singleLinkedList) {
        if (singleLinkedList.head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Stack<HeroNode> heroNodes = new Stack<>();
        HeroNode temp = singleLinkedList.head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            heroNodes.push(temp);
            temp = temp.next;
        }
        while (true) {
            if (heroNodes.isEmpty()) {
                break;
            }
            System.out.println(heroNodes.pop());
        }

    }


    //反转链表（腾讯面试题）
    //思路
    //1.定义一个临时节点变量reverseListFirst，用来指向翻转链表的首元素，以及一个临时节点temp，用来遍历链表，以及一个临时变量remainNode，用来临时指向当前遍历到的元素
    //2.遍历原来的链表，将每一个节点逐次取出
    //3.除了第一次之外（因为第一次取出节点，reverseListFirst暂且为空），此后每一次遍历都将该节点赋给remainNode，而后temp = temp.next,指向原链表中后一节点
    //4.完成上一步的指向工作后，将remainNode当前所指向的节点的next指向reverseListFirst，链接节点
    //5.链接完节点后，将reverseListFirst设置为remainNode，即当前遍历出的节点做翻转链表的首元素
    //6.遍历结束后，将原链表的头节点的next指向reverseListFirst，即将翻转链表接上一个头节点
    public static SingleLinkedList reverseList(SingleLinkedList singleLinkedList) {
        if (singleLinkedList.head.next == null) {
            return null;
        }
        HeroNode temp = singleLinkedList.head.next;
        HeroNode remainNode;
        HeroNode reverseListFirst = null;
        while (true) {
            if (temp == null) {
                break;
            }
            if (reverseListFirst == null) {
                reverseListFirst = temp;
                //以下两行代码的顺序不能调换，因为由上一行代码，reverseListFirst = temp;此时reverseListFirst和temp都指向同一个对象，对象只有一个
                //如果先执行reverseListFirst.next = null;再执行temp = temp.next;那么对象的next指向就为空了，这里temp.next就是空了，没有达到理想的后移效果
                temp = temp.next;
                reverseListFirst.next = null;
            } else {
                //以下三行代码同上面的道理
                remainNode = temp;
                temp = temp.next;
                remainNode.next = reverseListFirst;

                reverseListFirst = remainNode;
            }
        }
        singleLinkedList.head.next = reverseListFirst;
        return singleLinkedList;
    }


    //查找单链表中的倒数第k个节点（新浪面试题）
    //思路
    //1.编写一个方法，接收一个头节点，同时接收一个index
    //2.index 表示链表中倒数第几个节点
    //3.由于链表长度不同，倒数第几个对于不同长度链表来说正着数是不一样的。因此需要获取链表的长度
    //4.获取到链表长度之后，倒数第index个就相当于从头遍历的 （size - index）个
    public static HeroNode getLastIndexHeroNode(HeroNode head, int index) {
        if (head.next == null) {
            System.out.println("链表为空");
            return null;
        }
        int size = getLength(head);
        if (index <= 0 || index > size) {
            System.out.println("参数不合法");
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //方法：获取到单链表的节点的个数（如果是带头节点的链表，需要不统计头节点）
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            System.out.println("链表为空");
            return 0;
        }
        int length = 0;
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            length++;
            temp = temp.next;
        }
        return length;
    }
}

//-------------------------------------------------------------------------------------------------------------------------
//定义单向链表
class SingleLinkedList {
    //给一个头节点
    HeroNode head = new HeroNode(0, "", "");

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后节点的next 指向 新节点
    public void add(HeroNode newNode) {
        //因为head节点不能改变，所以我们可以建一个临时节点
        HeroNode temp = head;
        //循环找到最后一个节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            //刷新temp
            temp = temp.next;
        }
        //退出循环了表示找到最后节点了,赋值即可
        temp.next = newNode;
    }

    //修改节点，根据编号来修改
    public void update(HeroNode newHeroNode) {
        //判空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //不为空,遍历查找
        HeroNode temp;
        temp = head.next;
        boolean flg = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                flg = true;
                break;
            } else {
                temp = temp.next;
            }
        }
        if (flg) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
            System.out.println("修改成功");
        } else {
            System.out.println("未找到编号为 " + newHeroNode.no + "的元素");
        }
    }

    //删除节点
    //思路：先找到需要删除的这个节点的前一个节点；temp.next = temp.next.next;被删除的节点将不会有其他引用指向，会被垃圾回收机制回收
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        boolean flg = false;
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flg = true;
                break;
            }
            temp = temp.next;
        }
        if (flg) {
            temp.next = temp.next.next;
            System.out.println("删除成功");
        } else {
            System.out.println("未找到编号为 " + no + "的元素");
        }

    }

    //显示这个链表 采用遍历即可
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

//-------------------------------------------------------------------------------------------------------------------------
//定义HeroNode,每一个HeroNode 对象就是一个节点
class HeroNode {

    public int no;

    public String name;

    public String nickName;

    //每一个节点都需要一个next属性，且是与自己相同类型，用来指向下一个节点
    public HeroNode next;

    //构造器
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
package com.bjpowernode.List.doublelinkelist;

/**
 * 分析双向链表的遍历，添加，修改，删除的操作思路
 * 1.遍历 和 单链表一样，只是可以向前，也可以向后
 * 2.添加 （默认添加到双向链表的最后）
 * 先找到双向链表的最后这个节点
 * temp.next =newHeroNode
 * newHeroNode.pre = temp
 * 3.修改的思路 与单向链表一样
 * 4.删除
 * 因为是双向链表，因此，我们可以实现自我删除某个节点
 * 直接找到要删除的这个节点，比如temp
 * temp.pre.next = temp.next
 * temp.next.pre = temp.pre
 */
public class DoubleLinkeListDemo {

    public static void main(String[] args) {
        //测试
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "时迁", "鼓上蚤");
        HeroNode2 heroNode4 = new HeroNode2(4, "武松", "武大郎");
        //创建一个双向链表对象
        DoubleLinkeList doubleLinkeList = new DoubleLinkeList();
        doubleLinkeList.add(heroNode1);
        doubleLinkeList.add(heroNode2);
        doubleLinkeList.add(heroNode3);
        doubleLinkeList.add(heroNode4);

        //显示双向链表
        doubleLinkeList.list();

        //修改
        doubleLinkeList.update(new HeroNode2(2, "公孙胜", "入云龙"));
        doubleLinkeList.list();

        //删除
        doubleLinkeList.delete(3);
        doubleLinkeList.list();

    }
}

//-------------------------------------------------------------------------------------------------------------------------
//定义双向链表
class DoubleLinkeList {
    //给一个头节点
    HeroNode2 head = new HeroNode2(0, "", "");

    //添加节点到双向链表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后节点的next 指向 新节点
    public void add(HeroNode2 newNode) {
        //因为head节点不能改变，所以我们可以建一个临时节点
        HeroNode2 temp = head;
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
        newNode.pre = temp;
    }

    //修改节点，根据编号来修改
    public void update(HeroNode2 newHeroNode) {
        //判空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //不为空,遍历查找
        HeroNode2 temp;
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
    //思路
    //      因为是双向链表，因此，我们可以实现自我删除某个节点
    //      直接找到要删除的这个节点，比如temp
    //      temp.pre.next = temp.next
    //      temp.next.pre = temp.pre
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        boolean flg = false;
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {//这已经是链表的最后了（最后一个节点的next）
                break;
            }
            if (temp.no == no) {//找到需要删除的节点
                flg = true;
                break;
            }
            temp = temp.next;
        }
        if (flg) {
            temp.pre.next = temp.next;
            if (temp.next != null) {//防止temp指向最后一个节点，导致temp.next为空而产生空指针异常
                temp.next.pre = temp.pre;
            }
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
        HeroNode2 temp = head.next;
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
//定义HeroNode2,每一个HeroNode2 对象就是一个节点
class HeroNode2 {

    public int no;

    public String name;

    public String nickName;

    //每一个节点都需要一个pre属性，且是与自己相同类型，用来指向上一个节点
    public HeroNode2 pre;

    //每一个节点都需要一个next属性，且是与自己相同类型，用来指向下一个节点
    public HeroNode2 next;

    //构造器
    public HeroNode2(int no, String name, String nickName) {
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
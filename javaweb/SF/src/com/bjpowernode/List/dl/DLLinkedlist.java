package com.bjpowernode.List.dl;

/**
 * 带头结点和不带头结点的线性链表的区别
 * 1.所有的链表都要有个头指针first，带头结点的链表的头指针指向的是头结点，头结点的指针域指向首元结点，不带头结点的头指针直接指向首元结点。
 * <p>
 * 2.两者在操作上有区别：在删除和插入操作中，无论删除和插入的位置如何，带头结点的链表不需要修改头指针的值，而不带头结点的有时候需要。在清空操作中，带头结点的保留头结点，而不带头结点的要销毁。
 * <p>
 * 3.在结构上，带头结点的单链表，不管链表是否为空，均含有一个头结点，不带头结点的单链表不含头结点。
 * <p>
 * 4.在操作上，带头结点的单链表的初始化为申请一个头结点。无论插入或删除的位置是地第一个结点还是其他结点，算法步骤都相同。不带头结点的单链表，其算法步骤要分别考虑插入或删除的位置是第一个结点还是其他结点。
 */
public class DLLinkedlist {

    private GoodsNode node = new GoodsNode(0, "", 0.0);

    /**
     * 往链表末尾添加节点
     */
    public void add(GoodsNode goodsNode) {
        GoodsNode temp = node;

        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = goodsNode;
    }

    /**
     * 按照id值从小到大添加
     */
    public void addOrder(GoodsNode goodsNode) {
        GoodsNode temp = node;
        boolean flg = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.id > goodsNode.id) {
                break;
            }
            if (temp.next.id == goodsNode.id) {
                flg = true;
                break;
            }
            temp = temp.next;

        }
        if (flg) {
            System.out.println("id不能重复。。。");
        } else {
            goodsNode.next = temp.next;
            temp.next = goodsNode;
        }

    }

    /**
     * 修改节点
     * 1.找到需要修改的节点
     * 2.修改节点
     */
    public void updata(GoodsNode goodsNode) {
        GoodsNode temp = node;
        boolean flg = false;
        while (true) {
            if (temp.next == null) {
                flg = true;
                break;
            }
            if (temp.id == goodsNode.id) {
                break;
            }
            temp = temp.next;
        }
        if (flg) {
            System.out.println("对不起，没有找到目标节点");
        } else {
            temp.name = goodsNode.name;
            temp.price = goodsNode.price;
        }

    }

    /**
     * 删除节点
     * 条件：根据节点id
     */
    public void delete(int id) {
        GoodsNode temp = node;
        boolean flg = false;
        while (true) {
            if (temp.next == null) {
                flg = true;
                break;
            }
            if (temp.next.id == id) {
                break;
            }
            temp = temp.next;
        }
        if (flg) {
            System.out.println("未找到要删除的节点");
        } else {
            temp.next = temp.next.next;

        }

    }

    /**
     * 查看链表中每一个元素
     */
    public void list() {
        if (this.node.next == null) {
            System.out.println("空链表");
            return;
        }
        GoodsNode temp = node.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 获取链表节点个数
     */
    public int getLength() {
        int count = 0;
        if (this.node == null) {
            System.out.println("空链表");
            return 0;
        }
        GoodsNode temp = node.next;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

}

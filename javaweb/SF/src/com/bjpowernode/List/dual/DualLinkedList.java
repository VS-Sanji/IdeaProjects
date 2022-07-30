package com.bjpowernode.List.dual;

public class DualLinkedList {
    //头节点
    private BookNode head = new BookNode(0, "", 0.00);

    /**
     * 向结尾添加新节点
     */
    public void add(BookNode newNode) {
        BookNode temp = this.head;

        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //将最后一个节点的next指向newNode
        temp.next = newNode;
        //将newNode的pre指向最后一个节点
        newNode.pre = temp;
    }

    /**
     * 修改节点
     * 条件：双向链表中每一个节点的id和修改的id进行对比，如果对比成功，则进行修改
     * 如果没有对比成功，双向链表没有找到目标节点
     */
    public void update(BookNode newNode) {
        BookNode temp = this.head;
        if (this.head.next == null) {
            System.out.println("空链表");
            return;
        }
        boolean flg = false;
        while (true) {
            if (temp.id == newNode.id) {
                flg = true;
                break;
            }
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        if (flg) {
            temp.name = newNode.name;
            temp.price = newNode.price;
        } else {
            System.out.println("未找到该节点");
        }
    }

    /**
     * 双向链表的删除
     * 条件：根据id编号进行删除
     */
    public void delete(int id) {
        if (head.next == null) {
            System.out.println("空链表");
            return;
        }
        BookNode temp = head.next;
        boolean flg = false;
        while (true) {
            if (temp.id == id) {
                flg = true;
                break;
            }
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        if (flg) {
            temp.pre.next = temp.next;
            temp.next.pre = temp.pre;
        } else {
            System.out.println("未找到该节点");
        }
    }

    /**
     * 查找某节点
     * 条件：根据id进行查找
     */
    public BookNode find(int id) {
        if (head.next == null) {
            System.out.println("空链表");
            return null;
        }
        BookNode temp = head.next;
        boolean flg = false;
        while (true) {
            if (temp.id == id) {
                flg = true;
                break;
            }
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        if (flg) {
            return temp;
        } else {
            System.out.println("未找到该节点");
            return null;
        }
    }


}

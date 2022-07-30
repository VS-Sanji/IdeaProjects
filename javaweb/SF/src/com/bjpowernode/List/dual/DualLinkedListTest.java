package com.bjpowernode.List.dual;

public class DualLinkedListTest {
    public static void main(String[] args) {

        DualLinkedList dualLinkedList = new DualLinkedList();

        BookNode bookNode1 = new BookNode(1, "红楼梦", 33.00);
        BookNode bookNode2 = new BookNode(2, "三国", 99.00);
        BookNode bookNode3 = new BookNode(3, "西游", 88.00);
        BookNode bookNode4 = new BookNode(4, "水浒", 77.00);

        dualLinkedList.add(bookNode1);
        dualLinkedList.add(bookNode2);
        dualLinkedList.add(bookNode3);
        dualLinkedList.add(bookNode4);

        BookNode bookNode = dualLinkedList.find(3);
        System.out.println(bookNode.name);


    }
}

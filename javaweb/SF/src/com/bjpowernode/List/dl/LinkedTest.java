package com.bjpowernode.List.dl;

import com.bjpowernode.List.dl.DLLinkedlist;
import com.bjpowernode.List.dl.GoodsNode;

public class LinkedTest {
    public static void main(String[] args) {

        DLLinkedlist dlLinkedlist = new DLLinkedlist();

        GoodsNode goodsNode1 = new GoodsNode(1, "nike", 499.00);
        GoodsNode goodsNode2 = new GoodsNode(2, "lining", 399.00);
        GoodsNode goodsNode3 = new GoodsNode(3, "adi", 699.00);
        GoodsNode goodsNode4 = new GoodsNode(4, "anta", 899.00);

//        dlLinkedlist.add(goodsNode1);
//        dlLinkedlist.add(goodsNode2);
//        dlLinkedlist.add(goodsNode3);
//        dlLinkedlist.add(goodsNode4);

        dlLinkedlist.addOrder(goodsNode3);
        dlLinkedlist.addOrder(goodsNode2);
        dlLinkedlist.addOrder(goodsNode4);
        dlLinkedlist.addOrder(goodsNode1);
        dlLinkedlist.list();

        dlLinkedlist.updata(new GoodsNode(2, "peek", 299.00));
        dlLinkedlist.list();

        dlLinkedlist.delete(3);
        dlLinkedlist.list();

    }
}

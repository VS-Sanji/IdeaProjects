package com.bjpowernode.List.dl;

public class GoodsNode {

    public int id;

    public String name;

    public double price;

    //一个节点对象要有下一个节点对象的信息（引用）
    public GoodsNode next;

    public GoodsNode(int i, String s, double v) {
        this.id = i;
        this.name = s;
        this.price = v;
    }

    @Override
    public String toString() {
        return "GoodsNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

package com.bjpowernode.List.sparselist;

public class Node {

    private int row;

    private int col;

    private int data;

    private Node next;

    public Node(int row, int col, int data) {
        this.row = row;
        this.col = col;
        this.data = data;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

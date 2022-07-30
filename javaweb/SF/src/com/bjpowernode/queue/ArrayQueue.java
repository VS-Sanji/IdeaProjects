package com.bjpowernode.queue;

import java.util.Scanner;

public class ArrayQueue {
    public static void main(String[] args) {
        arrQueue arrQueue = new arrQueue(3);
        char key = ' ';//用于接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("输入s，显示队列所有数据");
            System.out.println("输入e，退出程序");
            System.out.println("输入a，向队列中添加数据");
            System.out.println("输入g，获取队列数据");
            System.out.println("输入h，显示队列头部数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出");
                    break;
                case 'a':
                    int i = scanner.nextInt();
                    arrQueue.addQueue(i);
                    break;
                case 'g':
                    int value = arrQueue.getQueue();
                    System.out.println("取出的数据是：" + value);
                    break;
                case 'h':
                    arrQueue.headQueue();
                    break;
            }
        }
    }

}

/**
 * 目前这个数组使用一次就不能用了，没有达到复用的效果
 */
//使用数组模拟队列-编写一个ArrayQueue类
class arrQueue {
    private int maxSize;//表示数组的最大容量

    private int front;//队列头

    private int rear;//队列尾

    private int[] arr;//数组模拟队列

    //构造器
    public arrQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
        front = -1;//指向队列头部，分析出front是指向队列头的前一个位置，不包数组的第一个元素
        rear = -1;//指向队列尾部，就是队列最后一个数据
    }

    /**
     * 判断队列是否已满
     */
    public boolean isFull() {
        return rear == this.maxSize - 1;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 添加数据到队列
     */
    public void addQueue(int value) {
        if (isFull()) {
            throw new RuntimeException("队列已满，无法添加");
        }
        rear++;//让rear指针后移
        arr[rear] = value;
    }

    /**
     * 获取队列数据，出队列
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据");
        }
        front++;
        return arr[front];
    }

    /**
     * 显示队列所有数据
     */
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    /**
     * 显示队列头数据，不是取数据
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        //这里只是显示队列头数据，而不是将其取出，所以这个front就不用自加操作了
        return arr[front + 1];
    }
}


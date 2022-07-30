package com.bjpowernode.queue;

import java.util.Scanner;

/**
 * 思路：
 * 1.front变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素,front的初始值为0
 * 2.rear变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定，rear的初始值为0
 * 3.当队列满时，条件是(rear + 1) % maxSize == front
 * 4.队列为空的条件，rear == front
 * 5.当我们这样分析，队列中有效的数据个数为：(rear + maxSize - front) % maxSize
 */
public class CircleQueueDemo {
    public static void main(String[] args) {
        CircleQueue circleQueue = new CircleQueue(3);
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
                    circleQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出");
                    break;
                case 'a':
                    int i = scanner.nextInt();
                    circleQueue.addCircleQueue(i);
                    break;
                case 'g':
                    int value = circleQueue.getCircleQueue();
                    System.out.println("取出的数据是：" + value);
                    break;
                case 'h':
                    circleQueue.headQueue();
                    break;
            }
        }
    }
}

class CircleQueue {
    private int maxSize;//表示数组的最大容量

    private int front;//队列头,这次将front指向第一个值的位置

    private int rear;//队列尾，这次将rear指向最后一个有效数据的后一个位置,并且整个数组预留一个位置（如数组长度为4，当rear指向下标3时即认为队列已满）

    private int[] arr;//数组模拟环形队列

    //构造器
    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
    }

    /**
     * 判断环形队列是否已满
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 添加数据到环形队列
     */
    public void addCircleQueue(int value) {
        if (isFull()) {
            throw new RuntimeException("队列已满，无法添加");
        }
        arr[rear] = value;
        rear = (rear + 1) % maxSize;
    }

    /**
     * 获取环形队列数据，出队列
     */
    public int getCircleQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;//这行代码表示：在front + 1 小于maxSzie之前，就将front + 1赋值给front，大于等于则取余
        return value;
    }

    /**
     * 显示环形队列所有数据
     * 不同于普通队列，这里需要知道环形队列中有多少个数据，所以需要写一个方法
     */
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        for (int i = front; i < front + Size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 获取环形队列中有效数据个数
     */
    public int Size() {
        if (isEmpty()) {
            throw new RuntimeException("空队列");
        }
        return (rear + maxSize - front) % maxSize;//这行代码表示：在 rear + maxSize - front 小于 maxSzie 之前，rear + maxSize - front就是当前有效数据个数，大于等于则取余
    }

    /**
     * 显示环形队列头数据，不是取数据
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        //这里只是显示环形队列头数据，而不是将其取出，所以这个front就不用自加操作了
        return arr[front];
    }
}

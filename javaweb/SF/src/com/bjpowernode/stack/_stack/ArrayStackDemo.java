package com.bjpowernode.stack._stack;
/**
 * 栈的应用场景
 * 1.子程序的调用：在跳往子程序前，会先将下个指令的地址存到堆栈中，直到子程序执行完后再将地址取出，以回到原来的程序中
 * 2.处理递归调用：和子程序的调用类似，只是除了存储下一个指令的地址外，也将参数、区域变量等数据存入堆栈中
 * 3.表达式的转换[中缀表达式转后缀表达式]与求值（实际解决）
 * 4.二叉树的遍历
 * 5.图形的深度优先（depth--first）搜索法
 */

import java.util.Scanner;

/**
 * 实现栈的思路分析
 *      1.用数组模拟栈
 *      2.定义一个top来表示栈顶，初始化为1
 *      3.入栈的操作，当有数据加入到栈时，top++；stack[top] = data;
 *      4.出栈的操作，int value = stack[top]; top--;return value;
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        //测试
        //创建一个ArrayStack对象 表示栈
        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push: 入栈");
            System.out.println("pop: 弹栈");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int i = scanner.nextInt();
                    arrayStack.push(i);
                    break;
                case "pop":
                    try {
                        int pop = arrayStack.pop();
                        System.out.println("出栈的数据是：" + pop);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

    }
}

//定义一个ArrayStack 表示栈
class ArrayStack {
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

}
package com.bjpowernode.stack;

/**
 * 栈的应用场景
 * 1.子程序的调用：在跳往子程序前，会先将下个指令的地址存到堆栈中，直到子程序执行完后再将地址取出，以回到原来的程序中
 * 2.处理递归调用：和子程序的调用类似，只是除了存储下一个指令的地址外，也将参数、区域变量等数据存入堆栈中
 * 3.表达式的转换[中缀表达式转后缀表达式]与求值（实际解决）
 * 4.二叉树的遍历
 * 5.图形的深度优先（depth--first）搜索法
 */
public class ArrayStack {
    /**
     * 栈的大小
     */
    private int maxStack;

    /**
     * 数组模拟栈
     */
    private int[] stack;

    /**
     * 栈顶所在位置，没有数据则使用 -1
     */
    private int top = -1;

    //构造方法
    public ArrayStack(int maxStack) {
        this.maxStack = maxStack;
        //int数组模拟栈，需要初始化容量，容器需要初始化容量
        stack = new int[maxStack];
    }

    /**
     * 对栈的操作
     * 1.压栈
     * 2.弹栈
     * 3.判断是否为空
     * 4.判断栈是否已满
     */
    //判断是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    //判断是否已满
    public boolean isFull() {
        return top == stack.length - 1;
    }

    //压栈
    public void push(int value) {
        //先判断是否已满
        if (isFull()) {
            throw new RuntimeException("栈已满");
        }
        //top自加
        top++;
        stack[top] = value;

    }

    //弹栈
    public int pop() {
        //先判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈已空");
        }
        int value = stack[top];
        stack[top] = 0;
        top--;
        return value;
    }

    //打印栈中所有元素
    public void list() {
        //判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈已空");
        }
        for (int i = 0; i < stack.length; i++) {
            //输出这串东西 stack[%d]=%d ，其中以第一 %d 为 i， 第二个%d 为 stack[i]
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //判断是否为一个运算符
    public boolean isOper(char value) {
        if (value == '*' || value == '/' || value == '+' || value == '-') {
            return true;
        }
        return false;
    }

    //判断运算符栈中栈顶符号类型
    public int topType() {
        int top = getTop();
        if (top == '*' || top == '/') {
            return 2;
        } else if (top == '-') {
            return 1;
        } else if (top == '+') {
            return 0;
        }
        //返回-1表示非 加减乘除
        return -1;
    }

    //判断当前运算符类型
    public int type(char v) {
        if (v == '*' || v == '/') {
            return 2;
        } else if (v == '-') {
            return 1;
        } else {
            return 0;
        }
    }

    //获取栈顶元素
    public int getTop() {
        return stack[top];
    }

    //获取栈的容量
    public int stackLength() {
        return this.stack.length;
    }

    //计算
    //减法和除法要注意次序，后进先出，先进后出
    public int calculate(int num1, int num2, int Oper) {
        int result = 0;
        switch (Oper) {
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            default:
                break;
        }
        return result;
    }


}

package com.bjpowernode.stack._stack;

/**
 * 使用栈完成表达式的计算思路
 * 1.通过一个index来遍历表达式
 * 2.如果是数字，直接入数栈
 * 3.如果是符号，分情况
 * 3.1 如果当前符号栈为空，则直接入栈
 * 3.2 如果当前符号栈不为空，则需要比较当前符号与栈顶符号的运算优先级。
 * 如果栈顶符号的运算优先级大于或等于当前符号，则需要从数栈中弹出两个元素，以及弹出符号栈栈顶元素进行运算，得到结果，将结果重新压入数栈
 * 如果栈顶符号的运算优先级小于当前符号，则直接入栈
 * 4.当表达式扫描完毕，就按顺序从数栈和符号栈中弹出相应元素运算，最后数栈中剩下的就是结果
 */
public class Caculator {
    public static void main(String[] args) {
        //完成表达式的运算
        String expression = "3+20*6-2";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int result = 0;
        char temp = ' ';
        String keepNum = "";
        //扫描表达式
        while (true) {
            temp = expression.substring(index, index + 1).charAt(0);
            boolean flg = operStack.isOper(temp);
            if (flg) {//是符号
                if (operStack.isEmpty()) {//符号栈为空
                    operStack.push(temp);//直接入栈
                } else {//符号栈不为空
                    if (operStack.priority(temp) <= operStack.priority((char) operStack.peek())) {//当前符号优先级较低
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.cal(num1, num2, oper);
                        //把运算结果入数栈
                        numStack.push(result);
                        //然后将符号入栈
                        operStack.push(temp);
                    } else {//当前符号优先级较高,直接入栈
                        operStack.push(temp);
                    }
                }
            } else {//是数，则直接入栈
//                numStack.push(temp - 48);//? "1+3" '1' --> 1 根据ASCII码对应来的
                //分析思路
                //1.当处理多位数时，不能发现一个数就立即入栈，因为它可能是个多位数
                //2.在处理数，需要向expression表达式的index往后再看一位，如果是数就进行扫描，如果是符号才入栈
                //3.因此我们需要定义一个变量 字符串，用于拼接

                //处理多位数
                keepNum += temp;

                if (index == expression.length() - 1) {//这里表示遍历到表达式的最后一位了，直接入栈就行了
                    numStack.push(Integer.parseInt(keepNum));
                } else {//不是最后一位，还要往后看一位是不是数，是数字继续往后看
                    char c = expression.substring(index + 1, index + 2).charAt(0);
                    if (operStack.isOper(c)) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";//后一位不是符号，是数字，则直接将keepNum入栈，但是注意要把keepNum重新置空
                    }
                }
            }
            index++;//index自加，并判断是否到最后了
            if (expression.length() <= index) {
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从 数栈 和 符号栈 中依次弹出进行运算
        while (true) {
            if (operStack.isEmpty()) {//符号栈为空，则表明所有运算结束
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1, num2, oper);
            //把运算结果入数栈
            numStack.push(result);
        }
        System.out.println("结果为：" + numStack.pop());

    }
}

//定义一个ArrayStack 表示栈
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    //返回栈顶的值，但不是弹栈
    public int peek() {
        return stack[top];
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

    //返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
    //数字越大，则优先级越高
    public int priority(char oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假定目前只有加减乘除
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    //计算
    public int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }
}

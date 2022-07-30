package com.bjpowernode.stack._stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {
        //首先定义一个逆波兰表达式
        //(3+4)*5-6 --> 3 4 + 5 * 6 -
        //为了方便拆分，将 后缀表达式 的数字和符号使用空格分开
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路
        //1.先将 "3 4 + 5 * 6 -" -》放到ArrayList中
        //2.将ArrayList 传递给一个方法，遍历ArrayList 配合栈 完成计算
        List<String> list = toSuffixExpressionList(suffixExpression);
        int result = calculate(list);
        System.out.println("结果是:" + result);
    }

    /**
     * 将一个 后缀表达式，依次将数据和运算符 放入到ArrayList中,方便操作
     */
    public static List<String> toSuffixExpressionList(String suffixExpression) {
        //将suffixExpression分割
        String[] s = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : s) {
            list.add(ele);
        }
        return list;
    }

    //完成对 后缀表达式 的计算

    /**
     * 1.从左至右扫描，将3和4压入堆栈
     * 2.遇到+运算符，因此将4和3 弹栈，（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈
     * 3.将5入栈
     * 4.接下来是*运算符，因此弹出5和7，进行计算得35，将35入栈
     * 5.将6入栈
     * 6.最后是-运算符，计算35-6的值，由此得出最终结果
     */
    public static int calculate(List<String> list) {
        //创建栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String s : list) {
            //这里使用正则表达式来取出数
            if (s.matches("\\d+")) {//匹配多位数
                stack.push(s);
            } else {//不是数，是运算符
                int num1 = Integer.parseInt(stack.pop());//从list集合中遍历出来的是个字符串，需要转成int
                int num2 = Integer.parseInt(stack.pop());//从list集合中遍历出来的是个字符串，需要转成int
                int result = 0;
                if (s.equals("+")) {
                    result = num1 + num2;
                } else if (s.equals("-")) {
                    result = num2 - num1;//后弹出的减去先弹出的
                } else if (s.equals("*")) {
                    result = num1 * num2;
                } else if (s.equals("/")) {
                    result = num2 / num1;//后弹出的减去先弹出的
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把结果压入栈
                stack.push(String.valueOf(result));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

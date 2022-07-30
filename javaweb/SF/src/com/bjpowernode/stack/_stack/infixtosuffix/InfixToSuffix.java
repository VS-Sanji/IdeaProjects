package com.bjpowernode.stack._stack.infixtosuffix;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中缀表达式 转 后缀表达式
 * 思路：
 * 1.初始化两个栈：运算符栈s1和存储中间结果的栈s2
 * 2.从左至右扫描中缀表达式
 * 3.遇到操作数时，将其压入s2
 * 4.遇到运算符时，比较其与s1栈顶运算符的优先级：
 * 4.1 如果s1为空，或栈顶运算符为左括号"(",则直接将此运算符入栈
 * 4.2 如果s1不为空，且栈顶运算符不是左括号"(",则判断优先级，若比栈顶运算符高，也将其入栈
 * 4.3 如果s1不为空，且栈顶运算符不是左括号"(",则判断优先级，若比栈顶运算符低或相同，
 * 则将s1栈顶的运算符弹出并压入到s2中，再次转到 4.1 与 s1中新的栈顶运算符比较
 * 5.遇到括号时：
 * 5.1 如果是左括号"("，则直接压入s1
 * 5.2 如果是右括号")"，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃、
 * 6.重复步骤 2 - 5，直到表达式的最右边
 * 7.将s1中剩余的运算符依次弹出并压入s2
 * 8.依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
 * （存储中间结果 是栈的话，那么最终的弹栈结果的逆序就是对应的后缀表达式，即未弹栈时，从栈底到栈顶的顺序就是对应的后缀表达式）
 * （存储中间结果 是list集合的话，那么最终按顺序输出就是对应的后缀表达式）
 */
public class InfixToSuffix {

    public static void main(String[] args) {

        //完成将一个中缀表达式转成后缀表达式的功能
        //说明
        //1. 1+((2+3)*4)-5 -> 1 2 3 + 4 * 5 -
        //2. 因为直接对str 进行操作，不方便，因此先将 "1+((2+3)*4)-5" -> 中缀表达式对应的List
        //   即 "1+((2+3)*4)-5" -》 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3.将得到的中缀表达式对应的List -》 后缀表达式对应的List
        //   即ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] -》 ArrayList[1,2,3,+,4,*,+,5,-]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
        List<String> suffixExpressionList = toSuffixExpressionList(infixExpressionList);
        System.out.println(suffixExpressionList);

    }

    /**
     * 将得到的中缀表达式对应的List -> 后缀表达式对应的List
     */
    public static List<String> toSuffixExpressionList(List<String> list) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //说明：因为s2这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里就不用Stack<String> 直接使用List<String> s2
        //Stack<String> s2 = new ArrayStack<>(); 存储中间结果的栈s2
        List<String> s2 = new ArrayList<>();

        //遍历list
        for (String s : list) {
            if (s.matches("\\d+")) {//如果是一个数，则直接加入到s2list集合中
                s2.add(s);
            } else if (s.equals("(")) {//是一个 左括号( 则直接压入s1
                s1.push(s);
            } else if (s.equals(")")) {//是一个 右括号) 则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃、
                while (true) {
                    if (s1.peek().equals("(")) {
                        break;
                    }
                    s2.add(s1.pop());
                }
                s1.pop();//!!!，将 ( 弹出s1栈，消除小括号
            } else {//执行这个分支表明是一个运算符
                //判空和判断s1栈顶是否是 左括号(
                if (s1.empty() || s1.peek().equals("(")) {//是则直接压入s1
                    s1.push(s);
                } else {//非空，且栈顶不是(
                    //当s的优先级小于等于s1栈顶运算符，则将s1栈顶的运算符弹出并加入到s2中，之后再继续与 s1中新的栈顶运算符比较
                    //这里就涉及到运算符优先级的问题，可以考虑写一个方法进行处理
                    while (!s1.empty() && priority(s) <= priority(s1.peek())) {
                        s2.add(s1.pop());
                    }
                    s1.push(s);//这里包括两种情况，但都是将s压入s1栈中，分别是上述循环结束，将s压栈，或者一开始s的优先级就大于s1栈顶元素的优先级，则直接压栈
                }
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;//注意：因为是存放到一个list集合中，因此按顺序输出就是对应的后缀表达式对应的List
    }

    /**
     * 写一个方法判断运算符优先级
     */
    public static int priority(String oper) {
        int i = 0;
        switch (oper) {
            case "+":
                break;
            case "-":
                break;
            case "*":
                i = 1;
                break;
            case "/":
                i = 1;
                break;
            default:
                throw new RuntimeException("符号不合法");
        }
        return i;
    }

    /**
     * 将中缀表达式转成对应的List
     */
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List，存放中缀表达式对应的数据
        List<String> list = new ArrayList<>();
        int index = 0;//定义一个指针，用来遍历传进来的字符串
        char c;//定义一个字符，用来存放遍历出的字符
        String str;//定义一个空串，用来进行多位数的拼接
        while (true) {
            if (index >= s.length()) {
                break;
            }
            if ((c = s.charAt(index)) < 48 || (c = s.charAt(index)) > 57) {//根据ASCII码值来的，不在 48 - 57 这个范围内，即表示不是一个数字
                list.add(String.valueOf(c));
                index++;
            } else {//这个分支表明遍历到的是一个数,但是需要考虑多位数
                str = "";
                while ((index < s.length()) && (c = s.charAt(index)) >= 48 && (c = s.charAt(index)) <= 57) {
                    str += c;
                    index++;
                }
                list.add(str);
            }
        }
        return list;
    }

}

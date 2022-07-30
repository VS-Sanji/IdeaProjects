package com.bjpowernode.stack;

public class ArrayStackTest2 {
    public static void main(String[] args) {
        /**
         * 1.获取字符串长度，拆分字符串,遍历字符串，获取每一个字符
         * 2.判断是数字还是运算符，是数字压入数字栈，是运算符压入符号栈
         * 3.是符号时，判断栈是否为空，是空则直接入栈。
         * 4.不是空时，需要判断目前栈顶符号的类型，若是 * /，直接进行运算，并将结果压入数字栈，再重新判断栈顶符号是否为 - 同时待压栈符号不是 * /，可先进行运算，并将结果压入数字栈，最后压入符号;  进行减号的判断是为了避免结果变成 5-4-1这类情况，由于栈数据结构的特性而导致4-1先算造成的错误
         *                                    若是 - ，判断待入栈符号，若非* /，直接先进行计算，并将结果压入数字栈，最后压入符号.若是* /，则直接压入符号栈
         *                                    若是 + ，直接压栈
         * 4.全部入栈后，数字栈弹栈，符号栈弹栈进行计算，每一次计算结果重新入数字栈。直到符号栈为空，则计算结束，此时数字栈中所剩元素即为运算结果
         */
        ArrayStack numStack = new ArrayStack(15);
        ArrayStack symbolStack = new ArrayStack(15);

        String s = "5-4/2-1*2*3-1-3";

        int length = s.length();
        int num1 = 0;
        int num2 = 0;
        int symbol = 0;
        int result = 0;
        String value = "";
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            //是运算符
            if (symbolStack.isOper(c)) {
                //判断运算符栈是否为空
                if (symbolStack.isEmpty()) {
                    //空
                    symbolStack.push(c);
                } else {
                    //不为空
                    //判断栈顶符号类型
                    //栈顶是 * 或者 /，则先计算
                    if (symbolStack.topType() == 2) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        symbol = symbolStack.pop();
                        result = numStack.calculate(num1, num2, symbol);
                        numStack.push(result);
                        //计算完了，还要注意到此时栈顶符号，若是 - 且此时待压栈符号 不是 * 或者 /，则先进行减法的运算
                        if (symbolStack.topType() == 1 && symbolStack.type(c) != 2) {
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            symbol = symbolStack.pop();
                            result = numStack.calculate(num1, num2, symbol);
                            numStack.push(result);
                        }
                        //算完了再压栈
                        symbolStack.push(c);

                        //栈顶是 -
                    } else if (symbolStack.topType() == 1) {
                        //待压栈符号是 - 或者 +，则先进行运算
                        if (symbolStack.type(c) == 1 || symbolStack.type(c) == 0) {
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            symbol = symbolStack.pop();
                            result = numStack.calculate(num1, num2, symbol);
                            numStack.push(result);
                            symbolStack.push(c);

                            //* / 直接压栈
                        } else {
                            symbolStack.push(c);
                        }
                    } else if (symbolStack.type(c) == 0) {
                        symbolStack.push(c);
                    }
                }
            } else {
                //是数字
                //数字可能不止一位
                value += c;
                if (i == s.length() - 1) {
                    numStack.push(Integer.parseInt(value));
                } else {
                    if (symbolStack.isOper(s.substring(i + 1, i + 2).charAt(0))) {
                        //下一个字符是运算符
                        numStack.push(Integer.parseInt(value));
                        value = "";
                    }
                }
            }

        }

        while (true) {
            if (symbolStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            symbol = symbolStack.pop();
            result = numStack.calculate(num1, num2, symbol);
            numStack.push(result);
        }
        System.out.println(numStack.pop());


    }
}

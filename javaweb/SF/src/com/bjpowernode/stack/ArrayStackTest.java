package com.bjpowernode.stack;

public class ArrayStackTest {
    public static void main(String[] args) {
        /**
         * 回文数据
         * 回文：  aba asdfdsa
         * 需求：通过上面以数组模拟栈来判断一个字符串是否是一个回文数据
         */
        String value = "asdfgfdsa";
        System.out.println(isHuiwen(value));


    }

    public static boolean isHuiwen(String value) {
        //新建栈对象
        ArrayStack arrayStack = new ArrayStack(10);
        //获取字符串长度
        int length = value.length();
        //根据长度，划分为length个char字符，将每一个char字符放入栈中
        for (int i = 0; i < length; i++) {
            arrayStack.push(value.charAt(i));
        }


        //取出数据，弹栈,并且拼接字符
        String newValue = "";
        for (int i = length - 1; i >= 0; i--) {
            char val = (char) arrayStack.pop();
            newValue += val;

        }
        if (newValue.equals(value)) {
            return true;
        }
        return false;

    }
}

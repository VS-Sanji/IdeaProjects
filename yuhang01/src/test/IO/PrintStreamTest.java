package test.IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * PrintStream:标准的字节输出流，默认输出到控制台
 */
public class PrintStreamTest {
    public static void main(String[] args) {
        //组合起来写，可以拆分为以下形式
        System.out.println("hello world");

        //分开写
        PrintStream ps = System.out;
        ps.println("hello zhanhui");
        ps.println("hello wangwu");
        ps.println("hello mafei");
        ps.println("今天又是学习java的一天");


        //尝试改变输出方向：不往控制台输出了
        PrintStream printStream = null;
        try {
            //创建一个标准输出流
            //标准输出流不再指向控制台，而是指向log文件
            printStream = new PrintStream(new FileOutputStream("log",true)); //加true，不清空原文件，不加则清空原文件
            //修改输出方向，将输出方向改到 log 文件
            System.setOut(printStream);
            //再进行输出，这时不会打印到控制台了
            printStream.println("hello zhanhui");
            printStream.println("hello wangwu");
            printStream.println("hello mafei");
            printStream.println("今天又是学习java的一天");

            printStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //标准输出流不用手动close关闭
    }
}

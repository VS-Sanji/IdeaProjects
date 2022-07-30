package test.IO;

import java.io.FileWriter;
import java.io.IOException;

/**
 * FileWriter
 *      文件字符输出流：只能输出普通文本
 */
public class FileWriterTest {
    public static void main(String[] args) {
        FileWriter fw = null;
        try {
            //会把原文件清空再写入
            fw = new FileWriter("file");

            //给一个char数组
            char[] chars = {'我','是','中','国','人'};
            //把数组全部写入
            fw.write(chars);
            //把数组部分写入
            fw.write(chars,2,3);

            //不把原文件清空，并且写入
            fw = new FileWriter("file",true);

            //给一个String
            String s = "学java";
            //把String写入
            fw.write(s);
            fw.write("\n");
            fw.write("我是中国人，请叫我宋宇航");
            fw.write("\n");
            fw.write("hello world");


            //养成好习惯，输出必刷新
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

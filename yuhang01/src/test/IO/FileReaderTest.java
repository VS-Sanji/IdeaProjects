package test.IO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * FileReader:
 *      文件字符输入流，只能读取普通文本；读取纯文本内容时，比较方便
 */
public class FileReaderTest {
    public static void main(String[] args) {
        FileReader fr = null;
        try {
            fr = new FileReader("yuhang01/src/test/file9");
            //准备一个char数组
            char[] chars = new char[4];
            int readCount = 0;
            while ((readCount = fr.read(chars)) != -1) {
                System.out.print(new String(chars,0,readCount));
                //我是中国人，我是沾灰，我是宋宇航，我是java小子
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

package test.IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件字节输出流，负责写。从 内存--->硬盘
 */
public class FileOutputStreamTest {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        try {
            //当目标文件不存在时，会自动新建。这里的file6不存在，会自动去新建
            //这种方式谨慎使用，这种方式会把原文件清空，然后重新写入
            fos = new FileOutputStream("yuhang01/src/test/file6");
            //开始写
            byte[] bytes = {97,98,99};
            //将byte数组全部写出
            fos.write(bytes);//写入：abc
            //将byte数组的一部分写出
            fos.write(bytes,0,2);//写入：ab
            //最终结果：abcab

            //这种方式谨慎使用，这种方式会把原文件清空，然后重新写入
            //file7中本来是一个HelloWorld文件，现在对其进行写入，会把他清空，再进行写入
            fos = new FileOutputStream("yuhang01/src/test/file7");
            //开始写
            byte[] bytes1 = {97,98,99,100,101,102};
            //全部写入
            fos.write(bytes1);//写入：abcdef
            //部分写入
            fos.write(bytes1,3,2);//写入：de
            //最终结果：abcdegde

            //要想不清空原文件，可以调用另一个构造方法 FileOutputStream(String name, boolean append)  这里的String表示文件名，布尔类型的append，如果是true，表示在原文件的末尾追加；反之则不追加
            fos = new FileOutputStream("yuhang01/src/test/file8",true);
            byte[] bytes2 = {97,98,99,100,101,102};
            fos.write(bytes2);
            //执行前的file8文件内容：sjflkjaslkfdjklsjflkjasldjfjklzmncnviksjf
            //执行后的file8文件内容：sjflkjaslkfdjklsjflkjasldjfjklzmncnviksjfabcdef
            //测试证明，如果原文件最后有空格，在写入时会抹去，直接跟在最后面；如果是中间有空格，则保留

            //对于字符串数据，可以先把它转成数组,再进行写入
            String s = "沾灰，属于是属于是了";
            byte[] bytes3 = s.getBytes();
            fos.write(bytes3);//sjflkjaslkfdjklsjflkjasldjfjklzmncnviksjfabcdef沾灰，属于是属于是了


            //写完之后一定要刷新
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

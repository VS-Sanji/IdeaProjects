package test.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 使用FileInputStream + FileOutputStream 完成文件的复制（拷贝）
 * 拷贝的过程是：一边读，一边写  从硬盘--->内存--->硬盘
 * 使用以上字节流拷贝文件的时候，文件类型随意，万能的，什么文件都能拷
 */
public class CopyTest {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //创建一个输入流
            fis = new FileInputStream("E:\\com\\bjpowernode\\yuhang1\\sanji.jpeg");
            //创建一个输出流
            fos = new FileOutputStream("D:\\com\\bjpowernode\\yuhang2\\sanji2.jpeg");//这里要写最后的文件名来接收拷贝后的数据，且要求不改变格式的话，后缀名要和输入流的文件后缀名相同

            //一边读，一边写
            byte[] bytes = new byte[1024 * 1024];//准备一个byte数组，一次最多拷1M
            int readCount = 0;
            while ((readCount = fis.read(bytes)) != -1) {
                //读了多少，写多少
                fos.write(bytes,0,readCount);
            }

            //刷新，输出流最后记得刷新
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //这里有两个流，都需要关闭，且都有异常需要处理，这个时候要分开处理；如果一起处理的话，可能一个出异常，另外一个就不会执行了，这样不可取
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //分开try
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

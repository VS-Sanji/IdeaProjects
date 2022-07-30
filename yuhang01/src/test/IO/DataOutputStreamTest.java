package test.IO;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * java.io.DataOutputStream:数据专属的流
 * 这个流可以将数据连同数据的类型一并写入文件
 * 注意：这个文件不是普通文本文档（这个文件使用记事本打不开）
 */
public class DataOutputStreamTest {
    public static void main(String[] args) {
        DataOutputStream dos = null;
        try {
            //创建数据专属的字节输出流
            dos = new DataOutputStream(new FileOutputStream("DataOutputStream"));
            //写数据
            byte b = 1;
            short s = 2;
            int i = 3;
            long L = 4L;
            float f = 2.33f;
            double d = 234.2345;
            boolean sex = true;
            char c = '爱';

            //写   该数据流写数据有特点：按照一定的顺序写入的，当我们去读取的时候也要按照写入时的顺序才能正确的读出来
            dos.writeByte(b); //这里写数据把数据的类型也一起写入到文件当中
            dos.writeShort(s);
            dos.writeInt(i);
            dos.writeLong(L);
            dos.writeFloat(f);
            dos.writeDouble(d);
            dos.writeBoolean(sex);
            dos.writeChar(c);

            //刷新
            dos.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

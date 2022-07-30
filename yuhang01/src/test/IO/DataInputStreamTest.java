package test.IO;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * DataInputStream:数据专属流 数据字节输入流
 * DataOutputStream写的文件，只能使用DataInputStream去读，并且读的时候需要提前知道写入的顺序
 * 读的顺序和写的顺序要一致，才能正常读出数据
 *
 */
public class DataInputStreamTest {
    public static void main(String[] args) {
        DataInputStream dis = null;
        try {
            //创建数据字节输入流
            dis = new DataInputStream(new FileInputStream("DataOutputStream"));
            //开始读，按照写入时的顺序
            //经过测试，如果中间某个没读到，最后结果会乱码；但是如果是按顺序读取，但是没有读取完，能够正常输出数据
            byte b = dis.readByte();
            short s = dis.readShort();
            int i = dis.readInt();
            long L = dis.readLong();
            float f = dis.readFloat();
            double d = dis.readDouble();
            boolean sex = dis.readBoolean();
            char c = dis.readChar();

            System.out.println(b);
            System.out.println(s);
            System.out.println(i);
            System.out.println(L);
            System.out.println(f);
            System.out.println(d);
            System.out.println(sex);
            System.out.println(c);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}

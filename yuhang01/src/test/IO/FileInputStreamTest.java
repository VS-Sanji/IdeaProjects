package test.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * java.io.FileInputStream
 *      1.文件字节输入流:万能的，任何类型的文件都可以通过这个流来读取
 *      2.以字节的方式读取，完成输入的操作（读的操作） 从 硬盘--->内存
 */
public class FileInputStreamTest {
    public static void main(String[] args) {
        //创建文件字节输入流对象
        //D:\code\FileInputStream  这是文件的绝对路径，可以看到其中的分隔斜杠\只有一个，IDEA工具会自动将 \ --> \\ ，因为单斜杠是有特殊意义的(java中 \ 表示转义)，\\才是真正的斜杠。
        //这里也可以这样写：D:/code/FileInputStream/wenbenwenjian.txt,效果一样     FileInputStream fis = new FileInputStream("D:/code/FileInputStream/wenbenwenjian.txt")
        //以下都是采用绝对路径
        FileInputStream fis = null;  //将流定义在外面，因为不仅是try语句块中会用到，finally也会用到
        try {
            fis = new FileInputStream("D:\\code\\FileInputStream\\wenbenwenjian.txt");

            //开始读,这里调用read方法会出现受检异常，处理方式有多个，在这选择再添加一个catch语句块就行
            /**
             * int readData = fis.read();          读取第一个字节，并且返回其对应的值。读的是“字节”本身，例如：读到 a ，返回 97 （a对应97）。如果已经读到文件的末尾了，再调用read方法读，则返回-1，表示没有数据了
             * System.out.println(readData);       返回122，对应的是z
             */
            //对以上代码进行改进，用循环的方式
//            while (true){
//                int readData = fis.read();
//                if (readData == -1) {
//                    break;
//                }
//                System.out.print(readData + " ");//122 104 97 110 104 117 105 110 105 109 97 115 105 108 101
//            }

            //以上while循环还可以再改进一下
            //先创建一个变量来存储读出的数据，然后直接把 (readData = fis.read()) != -1 当做while循环的判断语句，如果读取出来的值不是 -1 （即条件成立），就继续循环，否则就退出循环，这样就能一直读取到最后一个数据
//            int readData = 0;
//            while ((readData = fis.read()) != -1) {
//                System.out.println(readData);
//            }

            /**
             * 其实以上的while循环也不常用，一次只读一个字节，效率比较低。
             * 我们可以一次读取多个，做法：传入一个byte数组，一次读byte.length个字节
             * int read(byte[] bytes)
             * 往byte数组当中读
             * 比如 数组长度为4，文件数据是:abcdef
             * 那么第一次读：abcd   第二次读：efcd   第二次只能读两个了，会把第一次读的ab给覆盖掉
             */
            /**
             * 这里插入讲一下 相对路径 的内容
             * 相对路径：相对路径一定是从当前所在的位置为起点开始找，那么在这里的起点就是IdeaProjects：C:/User/26313/IdeaProjects，然后相对路径只需要补充后面的即可
             * IDEA默认的当前路径是指：工程Project的根
             */
            //比如在根下建一个文件，那么相对路径就是 file1
            fis = new FileInputStream("file1");
            int readData = fis.read();
            System.out.println(readData);//97 读到了，说明相对路径正确

            //再比如在text下建一个文件，那么相对路径就是 yuhang01/src/test/file2
            fis = new FileInputStream("yuhang01/src/test/file2");
            readData = fis.read();
             System.out.println(readData);//97 读到了，说明相对路径正确

            //在text下再建一个文件，相对路径 yuhang01/src/test/file3 ，测试byte数组读取数据
            fis = new FileInputStream("yuhang01/src/test/file3");
            byte[] bytes = new byte[4]; //准备了一个4个长度的byte数组，一次最多读取4个字节

            /**这时候 read(byte[] bytes) 方法的返回值表示的是：读取到的字节的数量。（不是字节本身）*/
            int readCount = fis.read(bytes);
            System.out.println(readCount); //第一次读取到4个字节，返回值4
            //调用String的有参构造方法，传一个数组进去，将其（内容）全部转成字符串
            //System.out.println(new String(bytes));//abcd
            //不应该全部转，而是读了多少个就转多少个，调用另外一个构造方法，这里readCount就表示读了多少个字节，把他传进去就好了，结果就是将数组中从下标0开始往后readCount个数转成字符串
            System.out.println(new String(bytes,0,readCount));//abcd


            readCount = fis.read(bytes);
            System.out.println(readCount); //第二次读取到2个字节，返回值2
            //调用String的有参构造方法，传一个数组进去，将其（内容）全部转成字符串
            //System.out.println(new String(bytes));//efcd
            //不应该全部转，而是读了多少个就转多少个，调用另外一个构造方法，这里readCount就表示读了多少个字节，把他传进去就好了
            System.out.println(new String(bytes,0,readCount));//ef

            readCount = fis.read(bytes);
            System.out.println(readCount); //第三次读取到0个字节，返回值-1，因为已经读取完了


            /**
             * 最终版
             */
            fis = new FileInputStream("yuhang01/src/test/file4");
            //准备byte数组
            byte[] bytes1 = new byte[6];
//            while (true) {
//                readCount = fis.read(bytes1);
//                if (readCount == -1) {
//                    break;
//                }
//                //将byte数组转换成字符串，
//                System.out.print(new String(bytes1,0,readCount));
//                //全部读到了
//                //public class HelloWorld{
//                //    public static viod main(String[] args){
//                //        System.out.println("Hello World!!!");
//                //    }
//                //}
//            }
            //改进
            while ((readCount = fis.read(bytes1)) != -1){
                System.out.print(new String(bytes1,0,readCount));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //在finally语句块中确保 流 一定关闭！！
            if (fis != null) {    //先进行判断，确保流不是空，避免空指针异常
                //关闭流的前提：流不是空，流是null没必要关闭
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        System.out.println();
        /**
         * java.io.FileInputStream类中的其他常用方法：
         *      int available() :返回流中剩余的没有读到的字节数
         *      long skip(long n) :跳过几个字节不读
         */
        try {
            fis = new FileInputStream("yuhang01/src/test/file5");
//            int available = fis.available(); //获取总字节数量
//            System.out.println("可读取的字节数：" + available);//可读取的字节数：5
//            byte[] bytes2 = new byte[available]; //这种做法可以直接指定数组的长度为文件字节数，读取一次就可以全部读完，不用循环了。但是这种做法不适合大数据量的文件，因为数组不能太大
//            int readCount = fis.read(bytes2);
//            System.out.println(new String(bytes2,0,readCount));//abcde

            //跳过2个字节不读取,当文件已经读取完了，再执行这个方法，结果就是 -1
            fis.skip(2);
            System.out.println(fis.read());//99
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }




    }

}

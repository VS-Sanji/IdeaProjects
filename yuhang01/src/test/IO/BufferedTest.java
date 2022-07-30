package test.IO;

import java.io.*;

/**
 * BufferedReader
 *      带有缓冲区的字符输入流
 *      使用这个流的时候不需要自定义char数组，或者不需要自定义byte数组。自带缓冲
 */
public class BufferedTest {
    public static void main(String[] args) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            //当一个流的构造方法中需要一个流的时候，被传进来的流，叫做：节点流
            //而接收这个流所构造出来的流（外部负责包装的流）叫做：包装流/处理流
            //对于当前这个程序来说，FileReader fr 是节点流，BufferedReader br 是包装流
            fr = new FileReader("file");
            br = new BufferedReader(fr);

            //BufferedReader这个流的一个方法：readLine，一次读取一行
            String s = br.readLine();
            System.out.println(s);//学java

            //循环，全部读
            //readLine()方法表示读取一个文本行，但是不带换行符，所以需要换行的话要自己加，这里就选择println来换行
            String s1 = null;
            while ((s1 = br.readLine()) != null){
                System.out.println(s1);
                //学java
                //我是中国人，请叫我宋宇航
                //hello world
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                //关闭流
                //对于包装流来说，只需要关闭最外层的流即可，里面的节点流不是不用关，而是在关闭包装流的同时节点流也关闭了，在包装流的close方法中节点流也调用了自己的close方法()
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        /**
         * 转换流：InputStreamReader
         *      将一个字节流转换成字符流
         */
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br1 = null;
        try {
//            //字节流
//            fis = new FileInputStream("file");
//
//            //通过转换流将 字节流 转换成 字符流
//            //节点流和包装流是相对的，这里：fis就是节点流，isr就是包装流
//            isr = new InputStreamReader(fis);
//
//            //BufferedReader的构造方法只能传一个 字符流，不能传一个字节流，所以需要将 字节流--->字符流
//            //节点流和包装流是相对的，这里：isr就是节点流，br1就是包装流
//            br1 = new BufferedReader(isr);

            //以上代码可以合并，最后可以直接写成以下形式
            br1 = new BufferedReader(new InputStreamReader(new FileInputStream("file")));

            String s = null;
            while ((s = br1.readLine()) != null) {
                System.out.println(s);
                //学java
                //我是中国人，请叫我宋宇航
                //hello world
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //用玩流，要关闭
            if (br1 != null) {
                try {
                    br1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


        /**
         * BufferedWriter：带有缓冲的字符输出流
         * OutputStreamWriter:转换流
         */
        BufferedWriter bw = null;
        try {
            //直接传一个FileWriter可行
            //bw = new BufferedWriter(new FileWriter("copy"));

            //通过转换流，将字节流转成字符流也行
            //bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("copy")));
            //还能以追加的形式，不会清空原文件
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("copy",true)));

            String s = "hello world";
            String s1 = "\n";
            String s2 = "java";
            String s3 = "\n";
            char[] chars = {'我','是','中','国','人'};

            bw.write(s);
            bw.write(s1);
            bw.write(s2);
            bw.write(s3);
            bw.write(chars);

            //输出流，要刷新
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}

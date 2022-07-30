package test.IO;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * File
 *      1.File类和四大家族没有继承关系，所以File类不能完成文件的读和写
 *      2.File对象表示：
 *          文件和目录路径名的抽象表示形式
 *          D:\code 这是一个File对象
 *          D:\code\FileInputStream\wenbenwenjian.txt  这也是一个File对象
 *          一个File对象有可能对应的是目录，也有可能对应的是文件
 *          File只是路径名的抽象表现形式
 *      3.File类中常用方法（掌握）
 */
public class FileTest {
    public static void main(String[] args) {
        //创建一个File对象
        File f1 = new File("D:/file");

        //判断是否存在对应的文件或者目录
        System.out.println(f1.exists());//false

        //如果D:/file 不存在，则可以文件的形式创建出来
        if (!(f1.exists())){
            try {
                f1.createNewFile();//以文件的形式创建出来
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //如果D:/file 不存在，则也可以目录的形式创建出来
        if (!(f1.exists())){
            f1.mkdir();//以目录的形式创建出来
        }

        //创建多重目录
        File f2 = new File("D:/a/b/c/e/f/g");
        if (!(f2.exists())){
            f2.mkdirs();
        }

        //获取文件的父路径:两个办法,返回值类型不同，一个String 一个File
        File f3 = new File("D:\\code\\FileInputStream\\wenbenwenjian.txt");
        //法一
        String parentPath = f3.getParent();
        System.out.println(parentPath);//D:\code\FileInputStream
        //法二
        File parentFile = f3.getParentFile();
        System.out.println("获取绝对路径" + parentFile.getAbsolutePath());//获取绝对路径D:\code\FileInputStream

        //
        File f4 = new File("copy");
        System.out.println("获取copy文件的绝对路径" + f4.getAbsolutePath());//获取copy文件的绝对路径C:\Users\26313\IdeaProjects\copy

        //获取文件名
        File f5 = new File("D:\\code\\FileInputStream\\wenbenwenjian.txt");
        String fileName = f5.getName();
        System.out.println(fileName);//wenbenwenjian.txt

        //判断是否是一个目录
        System.out.println(f5.isDirectory());//false

        //判断是否是一个文件
        System.out.println(f5.isFile());//true

        //获取文件最后一次修改时间
        long haoMiao = f5.lastModified(); //这个方法返回的是一个毫秒数，从1970年1月1日 00：00：000 到当前时间的毫秒数
        Date lastTime = new Date(haoMiao);//将毫秒数转换成当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SSSS");
        String strLastTime = sdf.format(lastTime);
        System.out.println(strLastTime);//2021-11-09 13-49-25 0767

        //获取文件大小
        System.out.println(f5.length());//15字节

        //获取当前目录下的所有子目录
        File f6 = new File("D:\\");
        File[] files = f6.listFiles();
        for (File file : files){
            System.out.println(file.getAbsolutePath());
            //D:\$RECYCLE.BIN
            //D:\1049732100891宋宇航quiz3.txt
            //D:\1049732100891宋宇航quiz4.txt
            //D:\a
            //D:\BaiduNetdisk
            //D:\BaiduNetdiskNew_7.7.8.1.exe
            //D:\code
            //D:\com
            //D:\Dan
            //D:\DeliveryOptimization
            //D:\eclipse-java-2021-09-R-win32-x86_64
            //D:\eclipse-java-2021-09-R-win32-x86_64.zip
            //D:\file
            //D:\jdk-8u212-windows-x64.exe
            //D:\MapData
            //D:\MobileFile
            //D:\Office_Tool_with_runtime
            //D:\Program Files
            //D:\QQ9.2.2.26569.exe
            //D:\quiz-topic2.txt
            //D:\quiz-topic3.txt
            //D:\sanji.jpeg
            //D:\shimada_topic2_ferroelectrics and liquid crystals_new.pdf
            //D:\shimada_topic3_oscillation_and_chaos.pdf
            //D:\songyuhang
            //D:\steam
            //D:\System Volume Information
            //D:\topic 4 & 5 slides for lecture and quiz.zip
            //D:\UdpServer.java
            //D:\WindowsApps
            //D:\WpSystem
            //D:\专业型硕士研究生-《玻璃制备及应用》课程大纲-材料学院.docx
        }
    }
}

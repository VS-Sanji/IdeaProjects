package test.IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 写一个日志记录工具
 */
public class LogTest {
    public static void main(String[] args) {
        log.logRecord("调用系统垃圾回收器，建议执行垃圾回收");
        log.logRecord("用户登录账户失败");
    }
}

class log {
    public static void logRecord(String msg){
        try {
            //创建标准输出流
            PrintStream ps = new PrintStream(new FileOutputStream("logfile.txt",true));//加true，不清空原文件，不加则清空原文件
            //修改输出方向
            System.setOut(ps);

            //获取记录时间
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SSSS");
            String nowTime = sdf.format(date);

            //输出记录信息
            ps.println(nowTime + ":" + msg);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
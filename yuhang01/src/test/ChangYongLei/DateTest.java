package test.ChangYongLei;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * java中对日期的处理
 *  2.1、获取系统当前时间
 * 		Date d = new Date();
 * 	2.2、日期格式化：Date --> String
 * 		yyyy-MM-dd HH:mm:ss SSS
 * 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
 * 		String s = sdf.format(new Date());
 * 	2.3、String --> Date
 * 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 * 		Date d = sdf.parse("2008-08-08 08:08:08");
 * 	2.4、获取毫秒数
 * 		long begin = System.currentTimeMillis();
 * 		Date d = new Date(begin - 1000 * 60 * 60 * 24);
 */
public class DateTest {
    public static void main(String[] args) throws Exception{
        //获取系统当前时间（精确到毫秒）
        //直接调用无参构造就行
        Date nowTime = new Date();          //java.util.Date; 不是 java.sql.Date;
        //java.util.Date类的toString方法已经被重写了
        System.out.println(nowTime); //Wed Oct 27 14:33:10 CST 2021
        //Wed Oct 27 14:33:10 CST 2021 不适合我们阅读，可以考虑格式化，改成适合我们阅读的形式
        //将日期类型Date，按照指定的格式进行转化： Date -- 转换成具有一定格式的日期字符串 --> String
        /**
         * SimpleDateFormat 是 java.text 包下的，专门负责日期格式化
         * SimpleDateFormat sdf = new SimpleDateFormat(日期格式);   方法的括号内要指定一个日期格式
         * yyyy 年（年是4位）
         * MM 月（月是2位）
         * dd 天（天是2位）
         * HH 时（时是2位）
         * mm 分（分是2位）
         * ss 秒（秒是2位）
         * SSS 毫秒（毫秒3位，最高999，1000毫秒则进1秒）
         * 注意：日期格式中，除了 y M d H m s S 这些字符不能随便写之外，剩下的符号格式自己随意组织
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yy-MM-dd");
        //调用format方法，对日期进行格式化
        String str = sdf.format(nowTime);
        String str1 = sdf1.format(nowTime);
        System.out.println(str);//2021-10-27 15:55:44 340
        System.out.println(str1);//21-10-27

        //以上是将 Date --> String 的做法

        //以下是将 String --> Date 的做法

        /**
         * 假设现在有一个日期字符串String，怎么转换成Date类型
         */
        String time = "1943-12-1 09:09:09 999";
        //SimpleDateFormat sdf2 = new SimpleDateFormat("格式不能随便写，要和日期字符串格式保持一致");
        //字符串的日期格式和SimpleDateFormat中的格式要相同，不然会出现 Java.text.ParseException 异常
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        //Date dateTime = sdf3.parse(time);  这里的parse方法会出现异常，后面学，现在在主方法上加 throws Exception
        Date dateTime = sdf2.parse(time);  //parse方法，解析字符串
        System.out.println(dateTime);//Wed Dec 01 09:09:09 CDT 1943  这里没有显示毫秒，是因为Date这个类在重写toString方法的时候没加


        /**
         * 获取自1970年1月1日 00：00：00 000 到当前系统时间的总毫秒数
         * 1秒 = 1000 毫秒
         */
        long nowTimeMillis = System.currentTimeMillis();
        System.out.println(nowTimeMillis);//1635323351342

        //假如有一个需求：统计一个方法执行所需要的时间
        //在方法执行之前记录一次毫秒数
        long begin = System.currentTimeMillis();

        //执行方法
        print();

        //在方法执行之后再记录一次毫秒数
        long end = System.currentTimeMillis();

        //则该方法执行所需要的时间就可以通过作差得出
        System.out.println("执行方法耗时" + (end - begin) + "毫秒");//执行方法耗时6毫秒


        /**
         * public Date(long date)，这个构造方法，需要传入一个毫秒数，通过这个构造方法创建的日期对象表示从标准基时间1970年1月1日 00：00：00 000 经过这么多毫秒后的时间
         * 比如传一个 1 进去，则创建的对象，他的时间为 1970年1月1日 00：00：00 001
         */
        Date nowTime1 = new Date(1);   //参数为毫秒数
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String stringTime = sdf3.format(nowTime1);
        System.out.println(stringTime);//1970-01-01 08:00:00 001  为什么小时是8呢，因为我们在中国，与格林威治标准基时间有8小时时差

        //那么通过这个构造方法，再配和System.currentTimeMillis();方法，就可以计算出任意一个时间点了
        //获取昨天此刻的时间
        Date yestdayNowTime = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String s = sdf4.format(yestdayNowTime);
        System.out.println(s);//2021-10-26 17:06:13 774

        //获取去年此刻的时间
        Date lastyearNowTime = new Date(System.currentTimeMillis() - 365 * 24 * 60 * 60 * 1000L);//365 * 24 * 60 * 60 * 1000L  这里一年的毫秒数超出了 int 类型的取值范围，所以要将其转成 long 类型
        String s1 = sdf4.format(lastyearNowTime);
        System.out.println(s1);//2020-10-27 17:23:20 236





    }

    public static void print(){
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}

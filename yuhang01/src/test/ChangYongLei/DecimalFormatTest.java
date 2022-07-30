package test.ChangYongLei;

import java.text.DecimalFormat;

/**
 * 关于数字的格式化
 */
public class DecimalFormatTest {
    public static void main(String[] args) {
        //java.text.DecimalFormat 是专门负责数字格式化的
        //DecimalFormat df = new DecimalFormat("数字格式");

        /**
         * 数字格式有哪些？
         * # 代表任意数字
         * , 代表千分位
         * . 代表小数点
         * ###,###.##   表示：加入千分位，并且保留两位小数
         */

        DecimalFormat df = new DecimalFormat("###,###.0000");  //保留四个小数位，不够补0
        String s = df.format(23134.32);
        System.out.println(s);//23,134.3200

        DecimalFormat df1 = new DecimalFormat("###,###.##");  //小数位后不止两位的砍掉，只保留两位
        String s1 = df1.format(323424.52452);
        System.out.println(s1);//323,424.52


    }
}

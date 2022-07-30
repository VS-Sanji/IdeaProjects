package test.ChangYongLei.String;

/**
 * 1、String类。
 * 	1.1、对String在内存存储方面的理解：
 * 		第一：字符串一旦创建不可变。
 * 		第二：双引号括起来的字符串存储在字符串常量池中。
 * 		第三：字符串的比较必须使用equals方法。
 * 		第四：String已经重写了toString()和equals()方法。
 *
 * 	1.2、String的构造方法。
 * 		String s = "abc";
 * 		String s = new String("abc");
 * 		String s = new String(byte数组);
 * 		String s = new String(byte数组, 起始下标, 长度);
 * 		String s = new String(char数组);
 * 		String s = new String(char数组, 起始下标, 长度);
 *
 * 	1.3、String类常用的21个方法。
 *
 * 2、StringBuffer/StringBuilder
 * 	2.1、StringBuffer/StringBuilder可以看做可变长度字符串。
 * 	2.2、StringBuffer/StringBuilder初始化容量16.
 * 	2.3、StringBuffer/StringBuilder是完成字符串拼接操作的，方法名：append
 * 	2.4、StringBuffer是线程安全的。StringBuilder是非线程安全的。
 * 	2.5、频繁进行字符串拼接不建议使用“+”
 */
public class StringTest {
    public static void main(String[] args) {
        /**
         * 关于String类中的构造方法
         */
        //常用的在这里练习一下，往后有需要查阅API帮助文档

        //最常用的一种方式
        String s = "沾灰我是你爹";

        //String(byte[] bytes)  传一个byte数组，将其转换成字符串输出
        byte[] array = {97,98,99};
        String s1 = new String(array);
        System.out.println(s1);        //输出结果为 abc  97-->a 98-->b 99-->c
        //输出一个引用，自动调用对象的toString方法，默认Object的话，则输出对象的内存地址
        //通过输出结果得知，String类已经重写了toString方法

        //String(byte[] bytes, int offset, int length)   传一个byte数组，数组元素起始下标，长度      实际就是把数组的一部分转成字符串
        String s2 = new String(array,2,1);
        System.out.println(s2);       //输出结果为 c   表明把abc的下标标记为 0 1 2 ，则offset=2 表示从下标为2的开始取，length=1表示长度取一  则结果为c

        //String(char[] value)      传一个char数组，全部元素都转成字符串
        char[] value = {'詹','辉','我','是','你','爹'};
        String s3 = new String(value);
        System.out.println(s3);    //输出结果：詹辉我是你爹

        //String(char[] value, int offset, int count)    传一个char数组，数组元素起始下标，长度      实际就是把数组的一部分转成字符串
        String s4 = new String(value,3,2);
        System.out.println(s4);      //输出结果：是你   第四个开始，往后两个


        /**
         * String类中常用方法
         * 都是用字符串对象去调用
         */
        // char charAt(int index)  返回指定位置的char值
        char c = "沾灰我是你爹".charAt(4);
        System.out.println(c);   //传入4，表示返回第五个位置上的字符值  结果：你

        //int compareTo(String anotherString)   按字典顺序比较两个字符串。
        //返回参与比较的前后两个字符串的asc码的差值，如果两个字符串首字母不同，则该方法返回首字母的asc码的差值
        // String a1 = "a";
        // String a2 = "c";
        // System.out.println(a1.compareTo(a2));//结果为-2
        //即参与比较的两个字符串如果首字符相同，则比较下一个字符，直到有不同的为止，返回该不同的字符的asc码差值，
        //String a1 = "aa";
        //String a2 = "ad";
        //System.out.println(a1.compareTo(a2));//结果为-3
        //如果两个字符串不一样长，可以参与比较的字符又完全一样，则返回两个字符串的长度差值
        //String a1 = "aa";
        //String a2 = "aa12345678";
        //System.out.println(a1.compareTo(a2));//结果为-8
        //返回为正数表示a1>a2, 返回为负数表示a1<a2, 返回为0表示a1==a2；
        //数字类型不能用compareTo，int跟int的比较不能用compareTo方法,直接用大于(>) 小于(<) 或者 等于(==) 不等于(!=)来比较即可
        int i = "songyuhang".compareTo("sonyuhang");  //如果两个字符串不一样长，参与比较的字符不完全一样，比较的是g y
        System.out.println(i);                     //结果：-18

        //boolean contains(CharSequence s)  判断当前字符串是否包含后者字符串
        boolean b = "我是宋宇航".contains("宋宇航");
        System.out.println(b); //"我是宋宇航"  包含  "宋宇航" ，结果为true

        //boolean endsWith(String suffix) 测试此字符串是否以指定的后缀结尾。
        boolean b1 = "songyuhang.java".endsWith(".java");
        System.out.println(b1); //songyuhang.java"  以  ".java" 结尾   true

        //boolean startsWith(String prefix)  判断字符串是否以形参内的字符串为起始
        boolean bl = "javayydssongyuhangyidinnengxuehuijavase".startsWith("java");
        System.out.println(b1);  //true

        //boolean equals(Object anObject)  将此字符串与指定对象进行比较。
        boolean b2 = "songyuhang".equals("zhanhui");
        System.out.println(b2);    //false

        //boolean equalsIgnoreCase(String anotherString)  忽略大小写，判断两个字符串是否相等
        boolean b3 = "SongYuHang".equalsIgnoreCase("songyuhang");
        System.out.println(b3);    //true

        //byte[] getBytes()  将字符串转换成byte数组
        byte[] bt = "abcde".getBytes();
        for (int j = 0; j < bt.length; j++) {
            System.out.print(bt[j] + " ");//97 98 99 100 101
        }

        //char[] toCharArray()  将字符串转成char数组
        char[] chars = "我是中国人".toCharArray();
        for (int l = 0; l < chars.length; l++) {
            System.out.println(chars[l]);
            //我
            //是
            //中
            //国
            //人
        }

        //String[] split(String regex)  拆分字符串,返回一个字符串数组
        String[] strings = "1943-12-1".split("-");  //将"1943-12-1" 以 "-" 进行拆分
        for (int l = 0; l < strings.length; l++) {
            System.out.println(strings[l]);
            //1943  数组第一个位置上
            //12    数组第二个位置上
            //1     数组第三个位置上
        }

        //int indexOf(String str)  判断某个子字符串在当前字符串中第一次出现的索引（下标）
        int j = "javapythonphpc++c".indexOf("python");
        System.out.println(j);  //4    下标为4

        //int lastIndexOf(String str)  判断某个子字符串在当前字符串中最后一次出现的索引（下标）
        int k = "zhsyhzhsyhzhsyhzhsyh".lastIndexOf("zh");
        System.out.println(k); //15

        //boolean isEmpty() 判断某个字符串是否为空字符串
        String s5 = "";
        boolean b4 = s5.isEmpty();
        System.out.println(b4);  //true

        //int length()  判断字符串长度并返回一个值
        //面试题：判断数组长度和字符串长度不一样
        //判断数组长度是length属性，判断字符串长度是length()方法
        System.out.println("songyuhang".length());  //10


        //String replace(CharSequence target, CharSequence replacement)
        //替换：用形参中的后者替换当前对象中形参中的前者
        String str = "http//:www.baidu.com".replace("http//","https//");
        System.out.println(str); //https//:www.baidu.com
        String str2 = "zhangsan=yifandf=fsdfdf=sdfadjkl=dk".replace("=","/");
        System.out.println(str2);//zhangsan/yifandf/fsdfdf/sdfadjkl/dk



        //String substring(int beginIndex)  传入起始下标，一直到最后，截取字符串
        String substring = "http://www.baidu.com".substring(7);
        System.out.println(substring); //www.baidu.com

        //String substring(int beginIndex, int endIndex)
        //起始位置 包括
        //终止位置 不包括
        String substring1 = "http://www.baidu.com".substring(7,10);
        System.out.println(substring1);//www  左闭右开，从7开始包括7，到10结束但不包括10



        //String trim()  去除字符串前后空白，中间的不会去除
        String s6 = "   song yu hang   ".trim();
        System.out.println(s6);//song yu hang

        //String 中只有一个是静态的，不需要new对象
        //这个方法叫：valueOf  把基本类型转化为对象类型的方法
        //作用：将“非字符串”转换成“字符串”
        String s7 = String.valueOf(true);
        String s8 = String.valueOf(false);
        String s9 = String.valueOf(123);
        String s10 = String.valueOf(3.14);
        System.out.println(s7);
        System.out.println(s8);
        System.out.println(s9);
        System.out.println(s10);
        //true
        //false
        //123
        //3.14
        String s11 = String.valueOf(new Man());
        System.out.println(s11);//test.Man@1b6d3586
        //println（）方法在输出内容时，底层会去调用String类的valueOf（）方法，将其转成字符串再进行输出
        //String.valueOf(new Man()) 将Man对象转成字符串，鉴于他是一个引用数据类型，指向一个对象，所以会去调用对象的toString将其转成字符串，然而这里Man中没有重写Object的toString方法，所以直接输出内存地址了
        //System.out.println(引用);//可以解释：println方法打印，底层调用String.valueOf方法，而对于引用valueOf又去调用对象的toString方法将其转成字符串再输出，故对象的toString方法必须要重写，不然就输出内存地址了
    }
}

class Man{

}
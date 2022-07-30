package test.ChangYongLei;

/**
 * 八种基本数据类型对应的包装类
 * 	3.1、包装类存在有什么用？
 * 		方便编程。
 *      在某些时候，不能直接传数据，可以通过包装类间接将值传进去，曲线救国、、、
 * 	3.2、八种包装类的类名是什么？
 * 		Byte
 * 		Short
 * 		Integer
 * 		Long
 * 		Float
 * 		Double
 * 		Boolean
 * 		Character
 * 	3.3、所有数字的父类Number，Number是一个抽象类
 * 	3.4、照葫芦画瓢：学习Integer，其它的模仿Integer。
 * 	3.5、什么是装箱？什么是拆箱？
 */
public class IntegerTest {
    public static void main(String[] args) {
        //234 这个数据通过构造方法的包装，从 基本数据类型  -->  引用数据类型
        //基本数据类型  -->  引用数据类型  （手动装箱）
        Integer i = new Integer(234);

        //引用数据类型  -->  基本数据类型  （手动拆箱）
        float f = i.floatValue();
        System.out.println(f);//234.0      将 int 型数据转换成了 folat 型数据


        /**
         * 关于Integer包装类的构造方法，有两个：
         *  Integer(int)
         *  Integer(String)
         *其他包装类的构造方法也同Integer相类似
         */
        //将2345转换成Integer包装类   int --> Integer
        Integer integer = new Integer(2345);
        System.out.println(integer);  //调用toString方法，且toString方法已经重写，输出值 2345

        //将"123"转换成Integer包装类  String --> Integer
        Integer integer1 = new Integer("123");
        System.out.println(integer1);  //调用toString方法，且toString方法已经重写，输出值 123


        /**
         * 通过访问包装类的常量，来获取最大值、最小值
         */
        System.out.println("int的最大值" + Integer.MAX_VALUE);
        System.out.println("int的最小值" + Integer.MIN_VALUE);
        System.out.println("long的最大值" + Long.MAX_VALUE);
        System.out.println("long的最小值" + Long.MIN_VALUE);
        //int的最大值2147483647
        //int的最小值-2147483648
        //long的最大值9223372036854775807
        //long的最小值-9223372036854775808

        /**
         * 现在java支持自动装箱 和 自动拆箱
         */
        //自动装箱  int类型 -自动转换-> Integer类型
        Integer integer2 = 100;   //等同于手动：Integer integer2 = new Integer(100);   只不过现在SUN公司帮我们做了，现在允许我们直接写成 Integer integer2 = 100;
        System.out.println(integer2);//100     这里自动调用了toString方法，而且包装类都重写了toString方法，返回值
        System.out.println(integer2 + 1);   //101  这里也自动拆箱了，在做 加减乘除 运算时，自动将 integer2 转换成基本数据类型类型

        //自动拆箱  Integer类型 -自动转换-> int类型
        int j = integer2;           //这里自动拆箱了，将integer2中的值100赋给了j变量
        System.out.println(j);       //100

        Integer integer3 = 1000;    //Integer integer3 = new Integer(1000);  这里 integer3 是一个引用，是一个变量，并不是直接存储着 1000 这个值 ，而是保存内存地址指向对象
        Integer integer4 = 1000;    //Integer integer4 = new Integer(1000);  这里 integer4 是一个引用，是一个变量，并不是直接存储着 1000 这个值 ，而是保存内存地址指向对象
        System.out.println(integer3 == integer4);//false  结果是不相等
        //  ==  比较的是对象的内存地址，integer3 和 integer4 保存的内存地址是不一样的
        //  ==  这个运算符不会触发自动拆箱机制，只有 + - * / 四则运算会触发

        /**
         * 以下两个例子的结果证明了一件事：
         * java中为了提高程序的运行效率，将 【 -128 ~ 127 】之间的所有对象提前创建好，
         * 放到了一个方法区的 ”整数型常量池“ 里了，目的是只要用这个区间的数据不需要再new了，直接从 ”整数型常量池“ 中取出来就行了，效率高
         * 这个 ”整数型常量池“ 在类加载的时候就已经完成创建了
         */
        Integer a = 128;
        Integer b = 128;
        System.out.println(a == b);//false  这里结果为 false 是因为 128 超出了 ”整数型常量池“ 所存储值的范围（其实就是byte的取值范围），在底层就创建了对象，a 和 b 所保存的内存地址是不相同的，所以比较的结果是false

        Integer x = 127;
        Integer y = 127;
        System.out.println(x == y);//true   这里结果为 true 是因为 127 未超出 ”整数型常量池“ 所存储值的范围（其实就是byte的取值范围），在底层未创建对象，x 和 y 所保存的内存地址是相同的，都指向 ”整数型常量池“ 中的127，所以比较的结果是true

        /**
         * 总之，不是 ”数字“ ，不能包装成 Integer 等包装类
         */
        Integer k = new Integer("12345");//运行无异常
        //Integer l = new Integer("中文");//运行出现异常，java.lang.NumberFormatException  ”“号中不能是中文，而将其包装成Integer
        //Integer m = new Integer("abc");//运行出现异常，java.lang.NumberFormatException  ”“号中不能是英文，而将其包装成Integer

        /**
         * 总结一下目前接触到的经典的异常
         * 1.空指针异常         NullPointerException
         * 2.类型转换异常       ClassCastException
         * 3.数组下标越界异常   ArrayIndexOutOfBoundsException
         * 4.数字格式化异常     NumberFormatException
         */

        //Integer 中的一些方法
        //static int parseInt(String s)  传一个String类型，返回一个int类型
        int retValue = Integer.parseInt("121");// String --> int
        System.out.println(retValue + 212);//333
        //int retValue1 = Integer.parseInt("yuhang");
        //System.out.println(retValue1);                java.lang.NumberFormatException  同样是会出现数字格式化异常

        //依葫芦画瓢
        //static double parseDouble(String s)  传一个String类型，返回一个double类型
        double retValue1 = Double.parseDouble("23.44");
        System.out.println(retValue1);//23.44

        /**
         * String --> int   Integer.parseInt("234")                  字符串 --> 基本数据类型   用对应 包装类 去调用 parse***（）方法 ，传入 “基本数据”  即可
         * int --> String   数字 + “”  或者 String.valueOf(int)       基本数据类型 --> 字符串   基本数据 + “”   /   调用String.valueOf()方法，传入 基本数据 即可
         *
         * int --> Integer  自动装箱  或者  Integer.valueOf(int)      八种基本数据类型 与其对应的 八种包装类 之间的相互转换，直接利用 自动装箱机制 与 自动拆箱机制 即可
         * Integer --> int  自动拆箱  或者  intValue(引用)
         *
         * String --> Integer   Integer.valueOf("234")              字符串 --> 包装类   调用 包装类的 valueOf() 方法，传入 “基本数据” 即可
         * Integer --> String   String.valueOf(引用)                包装类 --> 字符串   调用 字符串的 valueOf() 方法，传入 引用 即可
         */
        int i1 = 100;
        String string = i1 + "";
        System.out.println(string);  //100  说明 String string = i1 + ""; 这个代码将100 转成了字符串
        Boolean bl = Boolean.valueOf("true");
        System.out.println(bl);//true

    }
}

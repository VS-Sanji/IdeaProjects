package test.ChangYongLei.String;

/**
 * StringBuffer/StringBuilder
 * 	2.1、StringBuffer/StringBuilder可以看做可变长度字符串。
 * 	2.2、StringBuffer/StringBuilder初始化容量16
 * 	2.3、StringBuffer/StringBuilder是完成字符串拼接操作的，方法名：append
 * 	2.4、StringBuffer是线程安全的。StringBuilder是非线程安全的。
 * 	2.5、频繁进行字符串拼接不建议使用“+”
 */
public class StringBufferTest {


    public static void main(String[] args) {
        //我们在开发中，如果需要频繁的进行字符串的拼接，由于java中字符串一旦创建就不能再改变，每次进行拼接都会产生新的字符串，这样就创建了大量的字符串对象，占用大量的方法区内存，造成空间的浪费
        //例如：String s = "hello";
        // s += "world";
        //创建了三个字符串对象  "hello"   "world"    "helloworld"

        //创建StringBuffer对象，初始化一个容量为16的byte[]数组
        StringBuffer stringBuffer = new StringBuffer();

        //拼接字符串，以后就使用append方法，追加，尽量不使用 + 来链接
        stringBuffer.append("song");
        stringBuffer.append("yu");
        stringBuffer.append("hang");
        stringBuffer.append("xue");
        stringBuffer.append("javase");
        stringBuffer.append(3.14);
        stringBuffer.append(true);
        stringBuffer.append(100L);

        System.out.println(stringBuffer);  //这里字符串存在 stringBuffer 引用指向的对象中，所以输出引用，自动调用toString方法  结果：songyuhangxuejavase3.14true100
        //就是在原先的字符串上，往后继续加内容  基本数据类型也能追加形成字符串

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("yuhang");
        stringBuilder.append("java");
        stringBuilder.append("c");
        stringBuilder.append(true);
        stringBuilder.append(false);
        stringBuilder.append(2234);
        System.out.println(stringBuilder);//yuhangjavactruefalse2234


    }
}

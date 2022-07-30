package test.Map.HashTable.Properties;

import java.util.Properties;

/**
 * 目前只需要掌握Properties属性类对象的相关方法即可
 * Properties是一个Map集合，继承Hashtable，Properties的key和value都是String类型
 * Properties被称为属性类对象
 * Properties是线程安全的
 */
public class PropertiesTest {
    public static void main(String[] args) {
        //创建一个Properties对象
        Properties pro = new Properties();

        //需要掌握Properties的两个方法：一个存，一个取
        pro.setProperty("name","zhanhui");
        pro.setProperty("job","driver");
        pro.setProperty("gender","boy");
        pro.setProperty("age","三十");
        pro.setProperty("身高","一米八");

        //通过key获取value
        System.out.println(pro.getProperty("jobb"));//null   不存在的key取出null
        System.out.println(pro.getProperty("name"));//zhanhui
        System.out.println(pro.getProperty("age"));//三十
        System.out.println(pro.getProperty("身高"));//一米八
        System.out.println(pro.getProperty("job"));//driver
    }
}

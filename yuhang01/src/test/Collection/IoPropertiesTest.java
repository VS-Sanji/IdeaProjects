package test.Collection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * IO + Properties的联合使用
 * 非常好的一个设计理念：
 *      以后经常改动的数据，可以单独写到一个文件当中，使用程序进行动态读取。将来只需要修改这个文件当中的数据，java代码不需要修改也不需要重新编译，服务器也不需要重启，就可以拿到动态的信息。
 *
 *      类似于以上机制的这种文件被称为 配置文件。
 *      并且当配置文件中的内容格式是：key=value 时，我们把这种配置文件叫做属性配置文件。
 *
 *      java规范中有要求：属性配置文件建议以 .properties 结尾，但这不是必须的。这种以 .properties结尾的文件在java中被称为：属性配置文件。
 *      其中Properties是专门存放属性配置文件的一个类
 */
public class IoPropertiesTest {
    public static void main(String[] args) {
        /*
        Properties是一个Map集合，key和value都是String类型
        想将userinfo文件中的数据加载到Properties对象中
         */
        FileInputStream fis = null;
        try {
            //创建一个文件输入流
            fis = new FileInputStream("userinfo");
            //创建一个Map集合
            Properties pro = new Properties();

            //调用Properties对象的load方法将文件中的数据加载到Map集合中。
            pro.load(fis);//文件中的数据将顺着流加载到Map集合中，其中等号=左边做key，右边做value

            //通过key来获取value
            String username = pro.getProperty("username");
            String password = pro.getProperty("password");
            String age = pro.getProperty("age");
            System.out.println(username);
            System.out.println(password);
            System.out.println(age);
            //songyuhang
            //1212121212
            //12

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

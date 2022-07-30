package test.IO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ObjectOutputStream 对应的是一个 序列化（Serialize） 的过程：java对象存储到文件中，将java对象的状态保存下来的过程   拆分对象
 * ObjectInputStream 对应的是一个 反序列化（Deserialize） 的过程：将文件中的数据重新恢复到内存中，恢复成一个java对象  组装对象
 * 参与 序列化 和 反序列化 的对象，必须要实现 Serializable 接口
 *      Serializable接口是一个标识接口（java中有两种接口，一种是普通接口，一种是标识接口）：
 *          public interface Serializable {
 *          }
 *          这个接口中什么代码都没有，它只是起到一个标识的作用，标志的作用，java虚拟机看到这个类实现了这个接口，就可能会对这个类进行特殊处理
 *          Serializable这个标志接口是给java虚拟机参考的，java虚拟机识别到这个接口后，会为该类自动生成一个序列化版本号
 *
 *          源代码修改了，那么新的代码会进行重新编译，得到一个全新的字节码文件，并且class文件再次运行的时候，java虚拟机生成的序列化版本号也会发生相应的改变
 *      序列化版本号的作用：
 *          序列号不同出异常
 *          java.io.InvaliaClassException
 *              local class incompatible: Stream classdesc serialVersionUID = -1L,(之前序列号)
 *                                        local class serialVersionUID = -2L.（改动之后序列号）
 *          java语言中是采用什么机制来区分类的:
 *              第一：首先通过类名进行比对，如果类名不一样，肯定不是同一个类
 *              第二：如果类名一样，再依靠序列化版本号进行区分
 *
 *          我编写了一个类 Student implements Serializable
 *          你编写了一个类 Student implements Serializable
 *          不同的人编写了同样名字的类，但是类中的内容可能是不同的，那这两个类就不是同一个类，这时序列化版本号就起作用了
 *          对于java虚拟机来说，是能够区分开的，因为两个类都实现了 Serializable 接口，都有默认的序列化版本号，它们的序列化版本号不一样，所以区分开了（自动生成序列化版本号的优点）
 *
 *          自动生成序列化版本号的缺陷：
 *              一旦代码确定之后不能进行修改，只要进行修改，必然会重新编译，此时会生成一个全新的序列化版本号，这时java虚拟机会认为这是一个全新的类。这样不好，其实类还是那个类，只是由于新的需求改动了而已，我们不希望他的序列化版本号改变
 *              建议将序列化版本号写出来：private static final long serialVersionUID = 1L;
 * 最终结论：凡是一个类实现了Serializable接口，建议给该类提供一个固定不变的的序列化版本号，这样的话，即使以后这个类中的代码修改了，但是版本号不变，java虚拟机还会认定为同一个类
 */
public class ObjectOutputStreamTest {
    public static void main(String[] args) {
        //创建用户对象
        UserExample ue = new UserExample("zhansan",20,1);
        UserExample ue2 = new UserExample("zhansan",20,1);

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            //序列化
            oos = new ObjectOutputStream(new FileOutputStream("UserExample"));
            //序列化对象，写
            oos.writeObject(ue);//如果UserExample类没有实现Serializable接口 java.io.NotSerializableException: test.UserExample  报错了，这是因为UserExample这个类要想实现序列化需要实现一个 Serializable 接口,这个接口是一个 标识接口，接口中没有任何内容

            //反序列化
            ois = new ObjectInputStream(new FileInputStream("UserExample"));
            //反序列化对象，读
            Object obj = ois.readObject();
            //反序列化得到一个UserExample对象，打印输出会调用对象的toString方法
            System.out.println(obj);//UserExample{name='zhansan', age=20, no=1}

            /**
             * 一次序列化多个对象：将对象放到集合当中，序列化整个集合就行
             * 参与序列化的集合 以及 其中的元素 都需要实现 Serializable 接口
             * 不用集合存储，存第二个对象就会报错
             */
            List<UserExample> list = new ArrayList<>();//参与序列化的集合 以及 其中的元素 都需要实现 Serializable 接口
            list.add(new UserExample("zhangsan",22,1));
            list.add(new UserExample("wangwu",23,2));
            list.add(new UserExample("zhangsan",24,3));
            //序列化集合，写
            oos = new ObjectOutputStream(new FileOutputStream("UserExample2"));
            oos.writeObject(list);

            //反序列化集合，读
            ois = new ObjectInputStream(new FileInputStream("UserExample2"));
            List<UserExample> list1 = (List<UserExample>)ois.readObject();
            for (UserExample us : list1) {
                System.out.println(us);
                //UserExample{name='zhangsan', age=22, no=1}
                //UserExample{name='wangwu', age=23, no=2}
                //UserExample{name='zhangsan', age=24, no=3}
            }

            //刷新
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}


class UserExample implements Serializable {
    //java虚拟机识别到Serializable接口后，会自动生成一个序列化版本号
    //这个没有手动写出来，java虚拟机会默认给这个类添加一个序列化版本号
    /**
     * transient关键字：游离的，表示被该关键字修饰的变量不参与序列化
     */
    private static final long serialVersionUID = 1L;//手动写序列化版本号
    private String name;
    private transient int age;//age被tramsient修饰，不参与序列化
    private int no;

    /**
     * 源代码修改了，那么新的代码会进行重新编译，得到一个全新的字节码文件
     * 并且class文件再次运行的时候，java虚拟机生成的序列化版本号也会发生相应的改变
     */

    public UserExample() {
    }

    public UserExample(String name, int age, int no) {
        this.name = name;
        this.age = age;
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserExample that = (UserExample) o;
        return age == that.age && no == that.no && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, no);
    }

    @Override
    public String toString() {
        return "UserExample{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", no=" + no +
                '}';
    }
}

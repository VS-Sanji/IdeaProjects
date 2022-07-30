package test.Reflect;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 反射机制（比较简单，因为只要会查帮助文档，就可以了。）
 *
 * 	2.1、反射机制有什么用？
 * 		通过java语言中的反射机制可以操作字节码文件。
 * 		优点类似于黑客。（可以读和修改字节码文件。）
 * 		通过反射机制可以操作代码片段。（class文件。）
 *
 * 	2.2、反射机制的相关类在哪个包下？
 * 		java.lang.reflect.*;
 *
 * 	2.3、反射机制相关的重要的类有哪些？
 *
 * 		java.lang.Class：代表整个字节码，代表一个类型，代表整个类。
 *
 * 		java.lang.reflect.Method：代表字节码中的方法字节码。代表类中的方法。
 *
 * 		java.lang.reflect.Constructor：代表字节码中的构造方法字节码。代表类中的构造方法
 *
 * 		java.lang.reflect.Field：代表字节码中的属性字节码。代表类中的成员变量（静态变量+实例变量）。
 *
 * 		java.lang.Class：
 * 			public class User{
 * 				// Field
 * 				int no;
 *
 * 				// Constructor
 * 				public User(){
 *              }
 * 				public User(int no){
 * 					this.no = no;
 *              }
 *
 * 				// Method
 * 				public void setNo(int no){
 * 					this.no = no;
 *              }
 * 				public int getNo(){
 * 					return no;
 * 				}
 *          }
 *
 * 3、关于JDK中自带的类加载器：（聊一聊，不需要掌握，知道当然最好！）
 * 	3.1、什么是类加载器？
 * 		专门负责加载类的命令/工具。
 * 		ClassLoader
 *
 * 	3.2、JDK中自带了3个类加载器
 * 		启动类加载器:rt.jar
 * 		扩展类加载器:ext/*.jar
 * 		应用类加载器:classpath
 *
 * 	3.3、假设有这样一段代码：
 * 		String s = "abc";
 *
 * 		代码在开始执行之前，会将所需要类全部加载到JVM当中。通过类加载器加载，看到以上代码类加载器会找String.class文件，找到就加载，那么是怎么进行加载的呢？
 * 			首先通过 “启动类加载器” 加载。
 * 				注意：启动类加载器专门加载：C:\Program Files\Java\jdk1.8.0_101\jre\lib\rt.jar（每个人的文件位置不同，后面\jre\lib\rt.jar都是一样的）
 * 				rt.jar中都是JDK最核心的类库。
 * 			如果通过“启动类加载器”加载不到的时候，会通过 "扩展类加载器" 加载。
 * 				注意：扩展类加载器专门加载：C:\Program Files\Java\jdk1.8.0_101\jre\lib\ext\*.jar（每个人的文件位置不同，后面\jre\lib\ext\*.jar都是一样的）
 * 			如果“扩展类加载器”没有加载到，那么会通过 “应用类加载器” 加载。
 * 				注意：应用类加载器专门加载：classpath中的类。
 *
 * 	3.4、java中为了保证类加载的安全，使用了双亲委派机制。
 * 		优先从启动类加载器中加载，这个称为“父”。“父”无法加载到，再从扩展类加载器中加载，这个称为“母”。
 * 		双亲委派。如果都加载不到，才会考虑从应用类加载器中加载。直到加载到为止。
 *
 * 1、回顾反射机制
 *
 * 	1.1、什么是反射机制？反射机制有什么用？
 * 		反射机制：可以操作字节码文件
 * 		作用：可以让程序更加灵活。
 *
 * 	1.2、反射机制相关的类在哪个包下？
 * 		java.lang.reflect.*;
 *
 * 	1.3、反射机制相关的主要的类？
 * 		java.lang.Class
 * 		java.lang.reflect.Method;
 * 		java.lang.reflect.Constructor;
 * 		java.lang.reflect.Field;
 *
 * 	1.4、在java中获取Class的三种方式？
 * 		第一种：
 * 			Class c = Class.forName("完整类名");
 * 		第二种：
 * 			Class c = 对象.getClass();
 * 		第三种：
 * 			Class c = int.class;
 * 			Class c = String.class;
 *
 * 	1.5、获取了Class之后，可以调用无参数构造方法来实例化对象
 *
 * 		//c代表的就是日期Date类型
 * 		Class c = Class.forName("java.util.Date");
 *
 * 		//实例化一个Date日期类型的对象
 * 		Object obj = c.newInstance();
 *
 * 		一定要注意：
 * 			newInstance()底层调用的是该类型的无参数构造方法。
 * 			如果没有这个无参数构造方法会出现"实例化"异常。
 *
 * 	1.6、如果你只想让一个类的“静态代码块”执行的话，你可以怎么做？
 * 		Class.forName("该类的类名");
 * 		这样类就加载，类加载的时候，静态代码块执行！！！！
 * 		在这里，对该方法的返回值不感兴趣，主要是为了使用“类加载”这个动作。
 *
 * 	1.7、关于路径问题？
 *
 * 		String path = Thread.currentThread().getContextClassLoader()
 * 						  .getResource("写相对路径，但是这个相对路径从src出发开始找").getPath();
 *
 * 		String path = Thread.currentThread().getContextClassLoader()
 * 						  .getResource("abc").getPath();	//必须保证src下有abc文件。
 *
 * 		String path = Thread.currentThread().getContextClassLoader()
 * 						  .getResource("a/db").getPath();	//必须保证src下有a目录，a目录下有db文件。
 *
 * 		String path = Thread.currentThread().getContextClassLoader()
 * 						  .getResource("com/bjpowernode/test.properties").getPath();
 * 						  //必须保证src下有com目录，com目录下有bjpowernode目录。
 * 						  //bjpowernode目录下有test.properties文件。
 *
 * 		这种方式是为了获取一个文件的绝对路径。（通用方式，不会受到环境移植的影响。）
 * 		但是该文件要求放在类路径下，换句话说：也就是放到src下面。
 * 		src下是类的根路径。（其实类的根路径是out，那里存放了编译后的class字节码文件，我们在写的时候就放到src下就好了，对应编译后放在out下）
 *
 * 		直接以流的形式返回：
 * 		InputStream in = Thread.currentThread().getContextClassLoader()
 * 								.getResourceAsStream("com/bjpowernode/test.properties");
 *
 * 	1.8、IO + Properties，怎么快速绑定属性资源文件？
 *
 * 		//要求：第一这个文件必须在类路径下
 * 		//第二这个文件必须是以.properties结尾。
 * 		ResourceBundle bundle = ResourceBundle.getBundle("com/bjpowernode/test");
 * 		String value = bundle.getString(key);
 *
 *
 * 2、今日反射机制的重点内容
 * 	2.1、通过反射机制访问对象的某个属性。
 * 	2.2、通过反射机制调用对象的某个方法。
 * 	2.3、通过反射机制调用某个构造方法实例化对象。
 * 	2.4、通过反射机制获取父类以及父类型接口。
 */


public class ReflectTest01 {
    public static void main(String[] args) {
        /**
         * 方式一：class.forName()
         *      1.静态方法
         *      2.方法的参数是一个字符串
         *      3.字符串需要的是一个完整类名
         *      4.完整类名必须带有包名。java.lang包也不能省略
         */
        try {
            Class c1 = Class.forName("java.lang.String");//获取的是 String 这个类，不是String类的实例，以下同理     c1代表String.class文件，或者说c1代表String类型
            Class c2 = Class.forName("java.util.Date");//c2代表Date类型
            Class c3 = Class.forName("java.lang.Integer");//c3代表Integer类型
            Class c4 = Class.forName("java.lang.System");//c4代表System类型
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * 方式二：对象.getClass();
         *      java中任何一个对象都有getClass()方法。这是从老祖宗Object中继承过来的
         */
        String s = "宇航";
        Class x = "宇航".getClass();//x代表String.class字节码文件，代表String类型
//        Class c1 = Class.forName("java.lang.String");//获取的是 String 这个类，不是String类的实例     c1代表String.class文件，或者说c1代表String类型
//        System.out.println(c1 == x);  这个结果是true，==判断的是内存地址，这两个引用所保存的内存地址均指向了方法区中的 String.class字节码文件，而字节码文件在类加载的时候只有一份，所以是相同的

        /**
         * 方式三：java语言中任何一种类型，包括基本数据类型，都有class属性
         */
        Class z = int.class;         //z代表int类型
        Class k = String.class;      //k代表String类型
        Class l = Integer.class;     //l代表Integer类型
        Class m = double.class;      //m代表double类型

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //不使用反射机制去创建对象     (不灵活，换一个平台就不适用了)
        UserType userType = new UserType();
        System.out.println(userType);



        //通过反射机制，获取Class，通过Class来实例化对象   (适用性广泛)
        try {
            Class ut = Class.forName("test.UserType");//给一个完整的类名带包名
            Class<UserType> ut2 = (Class<UserType>)Class.forName("test.UserType");
            //Class x = Class.forName("test.user");  这里的完整类名以src为根目录，从这开始往下的类名

            //newInstance()方法会去调用这个类的无参构造方法，完成对象的创建，必须保证无参构造方法是存在的
            //java.lang.InstantiationException: test.UserType   如果没有无参构造，调用该方法就会报这个异常；所以建议无参构造方法还是建议加上
            Object obj = ut.newInstance();//返回Object类型，Object可以表示所有的对象
            UserType userType1 = ut2.newInstance();//加入泛型之后，返回的直接就是UserType类型了
            System.out.println(obj);
            System.out.println(userType1);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        /**以下这种写法，以后代码不需要改动,可以做到不同对象的实例化，只需要修改属性配置文件种的类名（带包名）即可，非常灵活。符合OCP原则，对扩展开放，对修改关闭*/
        //验证反射机制的灵活性
        //通过IO流获取属性配置文件classinfo.properties
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("classinfo.properties");
            /**
             * 这里还能写成以下形式：
             *      String path = Thread.currentThread().getContextClassLoader().getResource("classinfo.properties").getPath();
             *      fileReader = new FileReader(path);
             * 以上两行代码还能整合成以下写法，直接以流的形式返回
             *      InputStream fileReader = Thread.currentThread().getContextClassLoader().getResourceAsStream("classinfo.properties");
             * 然后在下面加载的时候，同样把fileReader传进去就行，效果相同
             */
            //创建属性类对象Map
            Properties properties = new Properties();//key和value都是String类型
            //加载
            properties.load(fileReader);
            //通过key获取value
            String value = properties.getProperty("className");
            System.out.println(value);//java.util.Date

            //拿到value可以通过反射机制实例化对象
            Class n = Class.forName(value);
            Object obj = n.newInstance();
            System.out.println(obj);//Fri Nov 19 14:00:30 CST 2021

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally{
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        /**以上这种写法，以后代码不需要改动,可以做到不同对象的实例化，只需要修改属性配置文件种的类名（带包名）即可，非常灵活。符合OCP原则，对扩展开放，对修改关闭*/

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //研究一下class.forName()发生了什么
        try {
            Class.forName("test.StaticCode");
            //输出结果：静态代码块执行了     证明了Class.forName()方法的执行会导致类加载，类加载时，静态代码块执行

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //研究一下文件路径的问题
        //以下这种方式的路径缺点是：移植性差，再IDEA中默认的当前路径是工程Project的根，这个代码假设离开了IDEA，换到了其他位置，可能当前路径就不是Project的根了，这时候这个路径就失效了
        //FileReader reader = new FileReader("classinfo.properties");

        //这里介绍一种比较通用的一种路径。即使代码位置换了，这样编写仍然是通用的。   但是需要注意：使用这种方式的前提是：该文件必须放在类路径下，也就是 src 下，编写代码时，src是类路径
        //这种方式的本质：就是说把当前文件都放在一个类路径下，当所处环境改变时，其绝对路径改变的只是src之前的父路径，而src后的路径名不变。等于是将其嫁接到不同的父路径上了，放到哪，只需要变父路径就行。
        /*
        Thread.currentThread() 当前线程对象
        getContextClassLoader() 是线程的方法，可以获取到当前线程的类加载器对象
        getResource() 这是类加载器的方法，当前线程的类加载器默认从类的根路径下加载资源.在这里，只需要将类的根路径下文件的路径名作为字符串参数传进去就能获取这个资源
         */
        //file2文件的绝对路径
        String path = Thread.currentThread().getContextClassLoader().getResource("test/file2").getPath();
        System.out.println(path);
        ///C:/Users/26313/IdeaProjects/out/production/yuhang01/test/file2  这样就能获取到文件的绝对路径了
        /**
         * 以上这种方法，就不受限于环境的变化了，在不同的环境下都能获取文件的绝对路径
         * 要求就是文件放在 类路径下面
         */
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        /**
         * java.util包下提供了一个资源绑定器，便于获取属性配置文件中的内容
         * 使用这种方式的时候，属性配置文件xxx.properties必须放到 类路径 下
         */
        //资源绑定器，只能绑定xxx.properties文件，并且这个文件只能放到 类路径 下，文件扩展名也必须是properties。 并且在写路径的时候，路径后面的扩展名不能写
        ResourceBundle resourceBundle = ResourceBundle.getBundle("test/name");
        //ResourceBundle resourceBundle = ResourceBundle.getBundle("test/name.properties");  这个文件路径可千万不能把 .properties 也加上，加上就报错Exception in thread "main" java.util.MissingResourceException
        resourceBundle.getString("name");
        System.out.println();//"zhangsan"  拿到了属性配置文件中的value值

    }
}



//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

class UserType {
    private int no;

    //无参构造方法
    public UserType() {
    }

    //有参构造方法
    //当我们自己手动定义了有参构造方法，默认添加的无参构造方法就没有了，此时我们可以自己再手动添加一个。如果自己不加上，那么newInstance方法就调用不了，从而无法实例化对象，会报错
    public UserType(int no) {
        this.no = no;
    }
}


class StaticCode{
    static{
        System.out.println("静态代码块执行了");
    }
}
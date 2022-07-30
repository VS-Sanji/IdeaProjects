package test.Exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 异常的定义：在程序执行过程当中出现了不正常的情况，叫做：异常
 * java是很完善的语言，提供了异常的处理方式，程序执行过程当中出现了不正常的情况，
 *java会把异常情况的信息打印到控制台上，供程序员参考，程序员看到异常信息后，可以对程序进行修改，让程序更加健壮
 *
 * 0、异常处理机制
 * 	0.1、java中异常处理机制的作用是：增强程序健壮性。
 * 	0.2、java中异常以类和对象的形式存在。 当出现异常的时候，JVM会new一个异常对象，然后将异常抛出，并打印到控制台上，用来告诉程序员哪出错了
 *
 * 1、java的异常处理机制
 *
 * 	1.1、异常在java中以类和对象的形式存在。那么异常的继承结构是怎样的？
 * 	我们可以使用UML图来描述一下继承结构。
 * 	画UML图有很多工具，例如：Rational Rose（收费的）、starUML等....
 * 		Object
 * 		Object下有Throwable（可抛出的）
 * 		Throwable下有两个分支：Error（不可处理，直接退出JVM）和 Exception（可处理的）
 * 		Exception下有两个分支：
 * 			所有RuntimeException类及其子类的实例被称为Runtime异常，      （一般是一些逻辑问题，而这些逻辑问题是可以通过完善逻辑来避免的，逻辑问题在运行阶段是肯定会发生异常，反馈信息给我们程序员的，所有在编译阶段不要求提供应对方法，做安全措施）
 * 			不是RuntimeException类及其子类的异常实例都被称为Checked异常。（一般是由一些外部因素导致的问题，在编译的时候是检查不出来的，即使程序逻辑没问题也还是可能会发生，所以需要对其进行预先处理，提供应对方法，做安全措施，否则我们不知道程序是否发生异常，可能出现更大的问题）
 *          只有Java语言提供了Checked异常，就是必须在编译前处理，否则不予通过编译。即：没有完善处理的代码根本就不会被执行。也就是说Java中除了RuntimeException及其任何子类，其他异常类都被Java异常强制处理机制强制异常处理
 *
 * 	1.2、编译时异常和运行时异常，都是发生在运行阶段。编译阶段异常是不会发生的。（异常对象只在运行阶段产生）
 * 	编译时异常因为什么而得名？
 * 		因为编译时异常必须在编写阶段提供处理手段，如果不提供处理手段编译器报错，因此得名。
 * 		所有异常都是在运行阶段发生的。因为只有程序运行阶段才可以new对象。
 * 		因为异常的发生就是new异常对象。
 *
 * 	1.3、编译时异常和运行时异常的区别？
 *
 * 		编译时异常一般发生的概率比较高。
 * 			举个例子：
 * 				你看到外面下雨了，倾盆大雨的。
 * 				你出门之前会预料到：如果不打伞，我可能会生病（生病是一种异常）。
 * 				而且这个异常发生的概率很高，所以我们出门之前要拿一把伞。
 * 				“拿一把伞”就是对“生病异常”发生之前的一种处理方式。
 *
 * 对于一些发生概率较高的异常，需要在运行之前对其进行预处理（提供应对办法去应对这个异常，并不是消除、解决了这个异常，即并不是使得这个异常消失了，
 * 而是进行抛出或者捕捉了（从程序执行来看，异常被捕捉了对于此后的代码的执行就没有影响），做一个安全措施，当发生异常时能保障系统不出更大的问题）。
 * java中异常机制本质：就是当程序出现错误，程序安全退出的机制。
 *
 * 一个基本的哲学是，如果一个问题没有被发现，那么系统会进入不可预知的状态，会产生更严重的后果，并且使得问题更难被发现，所以，一个鲁棒的系统应该尽可能早地和多地报告异常。这个哲学思想也可以叫做“故障导向安全原则”。
 * 系统类库的编写不能只考虑你某个场景而忽略别的场景，比如你是程序员控制的，但是别的程序中这些是用户或者外界输入的，你能控制，人家不能控制。
 * 另一方面，系统类库不能假设使用它的都是小心谨慎的程序员，再小心谨慎也会犯错误，而且明明系统类库可以做到的非要让使用它的人小心，这让用户觉得不爽。
 * 好比你买的汽车不安装任何安全装置，而对此的解释是，只要驾驶员足够小心，这些装置都是多余的，你会去买这样的车么？
 *
 *
 * 		运行时异常一般发生的概率比较低。
 * 			举个例子：
 * 				小明走在大街上，可能会被天上的飞机轮子砸到。
 * 				被飞机轮子砸到也算一种异常。
 * 				但是这种异常发生概率较低。
 * 				在出门之前你没必要提前对这种发生概率较低的异常进行预处理。
 * 				如果你预处理这种异常，你将活的很累。
 *
 * 		假设你在出门之前，你把能够发生的异常都预先处理，你这个人会更加
 * 		的安全，但是你这个人活的很累。
 *
 * 		假设java中没有对异常进行划分，没有分为：编译时异常和运行时异常，
 * 		所有的异常都需要在编写程序阶段对其进行预处理，将是怎样的效果呢？
 * 			首先，如果这样的话，程序肯定是绝对的安全的。
 * 			但是程序员编写程序太累，代码到处都是处理异常的代码。
 *
 * 	1.4、编译时异常还有其他名字：
 * 		受检异常：CheckedException
 * 		受控异常
 *
 * 	1.5、运行时异常还有其它名字：
 * 		未受检异常：UnCheckedException
 * 		非受控异常
 *
 * 	1.6、再次强调：所有异常都是发生在运行阶段的。
 *
 * 	1.7、Java语言中对异常（一般指的是受检异常）的处理（应对方法，安全措施）包括两种方式：
 *
 * 		第一种方式：在方法声明的位置上，使用throws关键字，抛给上一级。
 * 			谁调用我，我就抛给谁。抛给上一级。
 *
 * 		第二种方式：使用try..catch语句进行异常的捕捉。
 * 			这件事发生了，谁也不知道，因为我给抓住了。
 *
 * 		举个例子：
 * 			我是某集团的一个销售员，因为我的失误，导致公司损失了1000元，
 * 			“损失1000元”这可以看做是一个异常发生了。我有两种处理方式，
 * 			第一种方式：我把这件事告诉我的领导【异常上抛】
 * 			第二种方式：我自己掏腰包把这个钱补上。【异常的捕捉】
 *
 * 			张三 --> 李四 ---> 王五 --> CEO
 *
 * 		思考：
 * 			异常发生之后，如果我选择了上抛，抛给了我的调用者，调用者需要
 * 			对这个异常继续处理，那么调用者处理这个异常同样有两种处理方式。
 *
 * 	1.8、注意：Java中异常发生之后如果一直上抛，最终抛给了main方法，main方法继续
 * 	向上抛，抛给了调用者JVM，JVM知道这个异常发生，只有一个结果。终止java程序的执行。
 *
 * 	final, finally, finalize
 * 	final 关键字
 * 	    final修饰的类无法继承
 * 	    final修饰的方法无法覆盖
 * 	    final修饰的变量不能重新赋值
 *  finally 关键字
 *      和try一起使用
 *      finally语句块中的代码必须执行
 * finalize 标识符
 *      是Object类中的一个方法名
 *      这个方法是由垃圾回收器负责调用的、
 *
 *
 * SUN提供的内置的异常肯定是不够的，在实际的开发中，会出现一些和业务挂钩的异常，这就需要我们自行定义异常类
 * java中定义异常类：(模仿SUN公司的写法)
 *      第一步:编写一个类继承Exception或RuntimeException
 *      第二步：提供两个构造方法，一个无参的，一个有参的
 *
 * 在java中，方法覆盖之后不能抛出比父类更宽泛的异常
 * 子类重写父类方法所抛出的异常不能超出父类的范畴（仅指受检型异常），可以与父类所抛出的异常相同，或者是其子类型异常，
 * 不然在构成多态时，父类引用指向子类对象，调用子类对象的方法抛出超过父类所抛出异常类型的话，在父类中对于异常的try..catch..就无法捕捉了
 * 子类重写的方法可以抛出任何运行时异常
 */
class MyException extends Exception{
    //无参
    public MyException(){
    }

    //有参
    public MyException(String s){
        super(s);
    }

}

public class ExceptionTest {
    //一般不建议在main方法上使用throws上抛异常，再上抛就抛给JVM了，JVM只有终止。建议用try..catch..进行捕捉
    public static void main(String[] args) {
        int a = 100;
        int b = 0;

        //在运行这行代码的时候，发生了异常，除数不能为0  这时JVM就在会创建一个异常对象，并且将其抛出，输出异常信息打印到控制台上
        //int c = a / b;
        //Exception in thread "main" java.lang.ArithmeticException: / /by zero
        //at test.ExceptionTest.main(ExceptionTest.java:115)

        //由于发生了异常，这行代码就没有继续执行了
        //System.out.println(c);


        //doSome(); 直接调用编译会报错，这是因为doSome方法可能会抛出受检异常，java要求对与受检异常，必须进行预处理
        //用try..catch..语句来进行预先处理   或者   在main方法声明上继续加上  throws ClassNotFoundException ，不过我们一般不在main方法上加
        try {
            doSome();
        } catch (ClassNotFoundException e) {  //catch后括号内类似于方法的形参，为 异常类名 引用
            //e.printStackTrace()  为在控制台上打印异常的堆栈追踪信息，较为常用  也可以用 e.getMessage() 方法，得到异常的简单信息
            //e.getMessage();
            e.printStackTrace();
        }


        try {
            m1();
            //以上方法出现异常，下面的语句就不会执行了,直接进入catch语句块中执行
            System.out.println("hello world");
        } catch (Exception e) {         //这里将捕捉住的异常对象的内存地址赋给了引用 e
            //e.printStackTrace();
            System.out.println(e.getMessage());//E:\MODSKIN_11.19\READE.txt (系统找不到指定的文件。)
            //System.out.println("该文件被删除了");
        }
        //当异常未被捕捉，则后续的代码不会执行
        //当用try..catch..进行捕捉后，则try..catch..后续的代码还会执行
        System.out.println("main over");//main over



        try {
            //创建输入流
            FileInputStream fis = new FileInputStream("E:\\MODSKIN_11.19\\READE.txt");
            //读文件
            fis.read();
            //这里的catch语句可以写多个，并且异常子类需要写在前面，否则会构成多态，后面子类的catch语句永远不会执行；等于说是前面写了个通用的，不管发生哪个子类型异常都走最上面的catch语句，后面的catch语句没有执行机会
        } catch (FileNotFoundException e) {   //这里FileNotFoundException e 范畴小
            e.printStackTrace();
        }catch (IOException e1){           //这里IOException e1 范畴大
            e1.printStackTrace();
        }
        /**
         * catch后面的小括号里的类型可以是具体的异常类型，也可以是该类型的父类型
         * catch可以写多个。建议写catch的时候，精确的一个一个的处理，这样有利于程序的调试
         * catch写多个的时候，从上到下，必须遵循从小范畴到大范畴
         * 在JDK8中，有个新特性，支持：catch（FileNotFoundException | IOException e） 写法
         */

        /**
         * 在以后的开发中，处理受检常，以该选择上报还是捕捉呢？
         * 如果希望调用者知道并处理，就选择throws
         * 其他情况使用捕捉就行了
         */
        /**
         * 异常的两个方法：至少写一个，不然捉住了异常我们也不知道
         * String msg = e.getMessage();  获取异常的简单描述信息
         * e.printStackTrace();   打印异常的追踪信息，这个使用的较多
         */


        /**
         * 关于try..catch..中的finally子句，在finally子句中的代码是最后执行的，并且一定会执行即使try中的代码出现了异常
         * 除了一种情况例外：try语句块中出现了 System.exit(0),表示推出了JVM,那这个时候finally中的语句就没机会执行了，因为finally是在JVM中才能执行
         * finally子句必须和try一起出现，不能单独使用，可以没有catch
         * 通常在finally中完成资源的释放/关闭
         */
        FileInputStream fis = null;  //这个fis的声明要放在外面，因为try 和 finally中都要用到
        try {
            fis = new FileInputStream("E:\\MODSKIN_11.19\\READE.txt");
            String s = null;
            //这里一定会出现空指针异常
            s.toString();

            //流使用完一定要关闭
            //放在这里可能关不了，因为出现异常这里就不会执行了
            //fis.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e1){
            e1.printStackTrace();
        }finally {
            //将流的关闭代码放在这比较安全，一定会执行
            if (fis != null) {   //加判断防止空指针异常
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

        int result = m();
        System.out.println(result);//100
        /**
         * java中有两条法则：
         * 1.方法体中的代码必须遵循自上而下顺序依次逐行执行
         * 2.return语句一旦执行，整个方法必须结束
         *
         * 以下代码经过反编译的结果
         *        int i = 100;
         *        int j = i;
         *        i++;
         *        return j;
         * 从上而下执行，则结果必然是100，为了finally中的语句也执行，引入一个临时变量
         * 这样操作，从结果上来看，的确输出的是100，没有违反第一条法则；同时finally语句块也执行了，最后执行的return j;
         *
         */

    }

    public static int m(){
        int i = 100;
        try {
            return i;
        }finally {
            i++;
        }
    }






    //方法声明加上 throws ClassNotFoundException 表示该方法可能会抛出 ClassNotFoundException 这个异常，且是个受检异常
    public static void doSome() throws ClassNotFoundException{
        System.out.println("类没找到");
    }



    //这里异常类型范畴也不能更小，Exception最大了
    public static void m1() throws Exception{
        System.out.println("m1 begin");
        m2();
        //以上方法出现异常，下面的语句就不会执行了

        System.out.println("m1 over");
    }

    //这里抛出的异常范畴可以更大，即可以是FileNotFoundException 的 父类
    //这里不能是与FileNotFoundException无关的异常类型，比如 ClassCastException，这是不可以的
    public static void m2() throws IOException {
        System.out.println("m2 begin");
        m3();
        System.out.println("m2 over");
    }

    public static void m3() throws FileNotFoundException {
        System.out.println("m3 begin");
        //在这里调用SUN JDK中的某个类的构造方法
        //这个类在IO流中学习
        //创建一个流对象，该流指向一个文件
        new FileInputStream("E:\\MODSKIN_11.19\\READE.txt");
        //这个类的构造方法可能会抛出受检异常
        //public FileInputStream(String name) throws FileNotFoundException {
        //        this(name != null ? new File(name) : null);
        //    }
    }

}

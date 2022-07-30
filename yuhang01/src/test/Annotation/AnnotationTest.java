package test.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * 注解
 *
 * 	3.1、注解，或者叫做注释类型，英文单词是：Annotation
 * 		疑问：注解到底是干啥的？？？？？？？？？
 *
 * 	3.2、注解Annotation是一种引用数据类型。编译之后也是生成xxx.class文件。
 *
 * 	3.3、怎么自定义注解呢？语法格式？
 * 		 [修饰符列表] @interface 注解类型名{
 *
 * 		 }
 *
 * 	3.4、注解怎么使用，用在什么地方？
 * 		第一：注解使用时的语法格式是：
 *            @注解类型名
 *		第二：注解可以出现在类上、属性上、方法上、变量上等....
 * 		注解还可以出现在注解类型上。
 *
 * 	3.5、JDK内置了哪些注解呢？
 *
 * 		java.lang包下的注释类型：
 * 			掌握：
 * 			Deprecated 用 @Deprecated 注释的程序元素，不鼓励程序员使用这样的元素，通常是因为它很危险或存在更好的选择。
 *
 * 			掌握：
 * 			Override 表示一个方法声明打算重写超类中的另一个方法声明。
 *
 * 			不用掌握：
 * 			SuppressWarnings 指示应该在注释元素（以及包含在该注释元素中的所有程序元素）中取消显示指定的编译器警告。
 *
 * 	3.6、元注解
 * 		什么是元注解？
 * 			用来标注“注解类型”的“注解”，称为元注解。
 *
 * 		常见的元注解有哪些？
 * 			Target
 * 			Retention
 *
 * 		关于Target注解：
 * 			这是一个元注解，用来标注“注解类型”的“注解”
 * 			这个Target注解用来标注“被标注的注解”可以出现在哪些位置上。
 *          @Target(ElementType.METHOD)：表示“被标注的注解”只能出现在方法上。
 * 			@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, MODULE, PARAMETER, TYPE})
 *          表示该注解可以出现在：构造方法上、字段上、局部变量上、方法上、包上、模块上、参数上、类上...
 *
 * 		关于Retention注解：
 * 			这是一个元注解，用来标注“注解类型”的“注解”
 * 			这个Retention注解用来标注“被标注的注解”最终保存在哪里。
 * 			@Retention(RetentionPolicy.SOURCE)：表示该注解只被保留在java源文件中。
 * 			@Retention(RetentionPolicy.CLASS)：表示该注解被保存在class文件中。
 * 			@Retention(RetentionPolicy.RUNTIME)：表示该注解被保存在class文件中，并且可以被反射机制所读取。
 *
 * 	3.7、Retention的源代码
 * 		//元注解
 * 	    public @interface Retention {
 * 			//属性
 * 			RetentionPolicy value();
 * 		}
 *
 * 		RetentionPolicy的源代码：
 * 			public enum RetentionPolicy {
 * 				 SOURCE,
 * 				 CLASS,
 * 				 RUNTIME
 * 			}
 *
 * 			//@Retention(value=RetentionPolicy.RUNTIME)
 * 			@Retention(RetentionPolicy.RUNTIME)
 * 			public @interface MyAnnotation{}
 *
 * 	3.8、Target的源代码
 *
 *
 * 	3.9、注解在开发中有什么用呢？
 *
 * 		需求：
 * 			假设有这样一个注解，叫做：@Id
 * 			这个注解只能出现在类上面，当这个类上有这个注解的时候，要求这个类中必须有一个int类型的id属性。如果没有这个属性就报异常。如果有这个属性则正常执行！
 */


public class AnnotationTest {

    /*
    关于JDK lang包下的Override注解
    源代码：
    public @interface Overrice{
    }

     */
    //@Override这个注解，只能用来注解方法
    //@Override这个注解是给编译器参考的，和运行阶段没有关系
    //凡是java中带有这个注解的，编译器都会进行编译检查，如果这个方法不是重写父类的方法，编译器会报错
    @Override
    public String toString(){
        return "toString";
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //被这个注解标注的表示已过时
    @Deprecated
    public static void doSome(){
        System.out.println("doSome方法");
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//    报错的原因：如果一个注解中有属性，必须给属性赋值，除非该属性使用default指定了默认值
//    @MyAnnotation()
//    public void doSome2(){
//    }

    //@MyAnnotation(属性名=属性值)
    //指定name属性的值就好了
    //如果属性有多个，都需要赋值。除了属性有默认值，这里age属性默认值23，在使用注解时候可以不写
    @MyAnnotation(name = "wangwu", color = "黄色")
    public void doSome2(){
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //如果注解中只有一个属性且属性名是value时，在使用注解时，value可以省略  （有且只有一个value）
    @MyAnnotation2("zhansan")
    public void doSome3(){
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //email是个String数组，里面有多个值要加大括号，写成数组形式
    @OtherAnnotation(age = 32, email = {"wangwu@123", "zhansan@123"}, value = Season1.AUTUMN)
//    如果数组中只有一个元素，大括号可以省略
//    @OtherAnnotation(age = 23, email = "wangyi@123")
    public void doSome4(){

    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        /**
         * 通过反射机制获取注解
         */
        try {
            //获取类
            Class otherAnnotation = Class.forName("test.OtherAnnotation");
            //判断类上面是否存在OtherAnnotation注解
            //调用方法 引用.isAnnotationPresent()
            if (otherAnnotation.isAnnotationPresent(Target.class)){
                //如果有，获取这个注解对象
                Target target = (Target) otherAnnotation.getAnnotation(Target.class);
                System.out.println("类上面的注解是：" + target);//类上面的注解是：@java.lang.annotation.Target(value=[TYPE, FIELD, METHOD])
                //如果注解中存在属性，获取属性
                ElementType[] elementTypes = target.value();
                for (ElementType elementType : elementTypes){
                    System.out.println(elementType);
                    //TYPE
                    //FIELD
                    //METHOD
                    //拿到类上面的Target注解中的元素了
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //想要获取AnnotationTest中doSome2这个方法上的注解信息，首先就要获取这个类，然后获取这个方法，最后获取信息
        try {
            //获取类
            Class c = Class.forName("test.Annotation.AnnotationTest");
            //获取方法
            Method doSomeMethod = c.getDeclaredMethod("doSome2");
            //判断该方法上是否有MyAnnotation注解
            if (doSomeMethod.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation myAnnotation = doSomeMethod.getAnnotation(MyAnnotation.class);
                System.out.println(myAnnotation.age());
                System.out.println(myAnnotation.color());
                System.out.println(myAnnotation.name());
                //23
                //黄色
                //wangwu
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------

//自定义注解
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation{
    /**
     * 我们通常在注解当中可以定义属性，以下是这个Annotation的name属性
     * 看着像一个方法，但实际上我们称之为属性
     */
    String name();

    String color();

    int age() default 23;//这里如果指定了默认值，那么在使用该注解的时候该属性可以不写
}

//自定义注解
@interface MyAnnotation2{

    //给一个value属性
    String value();
}

//自定义注解
@interface MyAnnotation3{
    /**
     * 注解中的属性可以是哪些类型？
     *      可以是以下类型及其对应的数组形式
     *          byte short int long float double boolean char String Class enum枚举
     *          及其数组形式
     */

    byte value1();

    short value2();

    int value3();

    long value4();

    double value5();

    char value6();

    String value7();

    boolean[] value8();

    Class parameterType();

    Class[] parameterTypes();

    Season1 value9();
}

enum Season1{
    SPRING,SUMMER,AUTUMN,WINTER
}

//自定义注解
@Retention(RetentionPolicy.RUNTIME)//允许注解可以被反射
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})//只允许注解标注类，属性,方法
@interface OtherAnnotation{
    int age() default 12;

    String[] email();

    Season1 value();
}
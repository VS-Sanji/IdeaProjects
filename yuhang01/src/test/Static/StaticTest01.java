package test.Static;

/**
    static关键字：
        1.static修饰的变量叫做“静态变量”.
        2.static修饰的方法叫做“静态方法”.
        3.static还可以定义静态语句块.

    以下例子演示：static定义静态语句块

    static定义的静态语句块在类加载阶段执行，并且只执行一次，并且是自上而下的顺序执行。
*/
public class StaticTest01 {

    //静态语句块
    static{
        System.out.println("1");
    }

    static{
        System.out.println("2");
    }

    static{
        System.out.println("3");
    }

    static{
        System.out.println("4");
    }

    //入口
    public static void main(String[] args){
        System.out.println("main execute!  1");
        System.out.println("main execute!  2");
    }

}
/**
 * static修饰的静态方法的执行结果不依赖于对象，故抽象为类级别。所以调用不需要创建对象，通过  类名.   的方式调用。
 * 并且该方法在与其他方法在同一个类体中，可以省略  类名.  类名.相当于起到一个指路的作用，告诉jvm去哪里调用
 * 如果不在一个类体中就需要加上。
 * 此外，也可以通过   引用.   的方式调用，但在底层还是通过   类名.   来调用的;
 * 这时将引用赋为null不会产生空指针异常  因为根本就不依赖对象  （空指针异常发生在 空引用访问对象中的数据时）
 * 没有static修饰的实例方法执行结果依赖于对象，需要创建对象才能调用，通过   引用.   的方式访问。
 * 若在实例方法中调用实例方法，可以用   this.   因为能在实例方法中来调用实例方法，说明已经创建了对象，可以用this来指代当前对象
 * 且this可以省略
 *
 */
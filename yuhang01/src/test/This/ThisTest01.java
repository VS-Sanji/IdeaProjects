package test.This;

public class ThisTest01 {
    /**
    this关键字：

        1.this是什么？
            this是一个引用类型，
            在堆中的每一个java对象上都有this，
            this保存内存地址指向自身。

        2.this能用在哪些地方？
            第一：this可以用在成员方法中.

            第二：this可以用在构造方法中.
                语法：this(实参);
                通过一个构造方法去调用另一个构造方法。
                目的：代码重用。
                this(实参);必须出现在构造方法的第一行。

     super关键字

     super能出现在实例方法和构造方法中。

     super的语法是：“super.”、“super()”

     super不能使用在静态方法中。

     super. 大部分情况下是可以省略的。

     super.什么时候不能省略呢？
     父类和子类中有同名属性，或者说有同样的方法，
     想在子类中访问父类的，super. 不能省略。

     super() 只能出现在构造方法第一行，通过当前的构造方法去调用“父类”中
     的构造方法，目的是：创建子类对象的时候，先初始化父类型特征。

     super的使用：
     super.属性名			【访问父类的属性】
     super.方法名(实参)		【访问父类的方法】
     super(实参)			【调用父类的构造方法】

*/

    public static void main(String[] args){
        /*
        //创建对象
        MyDate t1 = new MyDate(2008,8,8);

        System.out.println(t1.year+"年"+t1.month+"月"+t1.day+"日");

        //创建对象
        MyDate t2 = new MyDate(2012,12,20);

        System.out.println(t2.year+"年"+t2.month+"月"+t2.day+"日");
        */

        MyDate t3 = new MyDate();

        System.out.println(t3.year+"年"+t3.month+"月"+t3.day+"日");

    }



    //日期
    static class MyDate{

        //Field
        int year;
        int month;
        int day;

        //Constructor
        //需求：在创建日期对象的时候，默认的日期是:1970-1-1
        MyDate(){
            this(1970,1,1);   //语法：this(实参);通过一个构造方法去调用另一个构造方法。目的：代码重用。this(实参);必须出现在构造方法的第一行。
        /**
        this.year = 1970;
        this.month = 1;
        this.day = 1;
        */
        }

        MyDate(int _year,int _month,int _day){
            year = _year;
            month = _month;
            day = _day;
        }


    }

}


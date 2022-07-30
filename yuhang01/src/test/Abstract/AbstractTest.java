package test.Abstract;
//第一：抽象类怎么定义？在class前添加abstract关键字就行了。
//		第二：抽象类是无法实例化的，无法创建对象的，所以抽象类是用来被子类继承的。
//		第三：final和abstract不能联合使用，这两个关键字是对立的。
//		第四：抽象类的子类可以是抽象类。也可以是非抽象类。
//		第五：抽象类虽然无法实例化，但是抽象类有构造方法，这个构造方法是供子类使用的。
//		第六：抽象类中不一定有抽象方法，但是抽象方法必须出现在抽象类中。
//		第七：抽象方法怎么定义？
//			public abstract void doSome();
//		第八（*****五颗星）：一个非抽象的类，继承抽象类，必须将抽象类中的抽象方法进行覆盖/重写/实现。
public class AbstractTest {
    public static void main(String[] args) {
        //抽象类虽然无法实例化，但是抽象类有构造方法，这个构造方法是供子类使用的，子类用super关键字可以调用父类的构造方法，初始化父类特征
        //AccountNum1 A=new AccountNum1();

    }
}

/**final和abstract不能联合使用，这两个关键字是对立的。final表示最终的，修饰的类不能被继承，而abtract修饰的类就是用来被子类继承的，矛盾了，所以不能一起用*/
//final abstract class accountNum{
//}

abstract class AccountNum1{
    public abstract void doSome();//抽象类中可以有抽象方法，抽象类中也可以没有抽象方法  抽象方法以;号结尾
    public void method(){             ////抽象类中可以有实例方法
        System.out.println("实例方法");
    }
}

//抽象类继承抽象类可行
abstract class bankAccount extends AccountNum1{
}

//非抽象类继承抽象类也是可行的
 class bankAccount1 extends AccountNum1{
    //public abstract void doSome();    非抽象类中不能有抽象方法，抽象方法不能出现在非抽象类中
    public void doSome(){          //abstract要去掉
        System.out.println("重写、实现、覆盖");
    }
}
//java语言中凡是没有方法体的方法都是抽象方法。
//			不对，错误的。
//			Object类中就有很多方法都没有方法体，都是以“;”结尾的，但他们
//			都不是抽象方法，例如：
//				public native int hashCode();
//				这个方法底层调用了C++写的动态链接库程序。
//				前面修饰符列表中没有：abstract。有一个native。表示调用JVM本地程序。

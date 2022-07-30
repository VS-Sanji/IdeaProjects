package test.ChangYongLei;

/**
 *  5.1、枚举是一种引用数据类型。
 * 	5.2、枚举编译之后也是class文件。
 * 	5.3、枚举类型怎么定义？
 * 		enum 枚举类型名{
 * 			枚举值1,枚举值2,枚举值3
 *                }
 * 	5.4、以后当一个方法执行结果只有两种情况，建议使用boolean类型
 * 	     当一个方法执行结果超过两种情况，并且是一枚一枚可以列举出来的时候，建议返回值类型设计为枚举类型。
 * 	例如：颜色 季节 星期 等
 */
public class EnumTest {
    public static void main(String[] args) {
        //switch语句支持枚举类型
        //switch也支持String int
        //低版本的JDK，只支持int
        //高版本的JDK，支持 String  int  枚举
        //byte short char 也可以，因为存在自动类型转换
        switch(Season.WINTER){          //这里括号中必须要用  类名.枚举值  的形式，类名不能省略  int类型的话传值就好了
            //以下各个case 后面 直接跟一个值即可，不加 类名
            case SPRING:
                System.out.println("春天");
                break;
            case SUNMER:
                System.out.println("夏天");
                break;
            case AUTUMN:
                System.out.println("秋天");
                break;
            case WINTER:
                System.out.println("冬天");
                break;
        }
    }
}

//枚举类
enum Season{
    //枚举类中的值
    SPRING, SUNMER, AUTUMN, WINTER
}
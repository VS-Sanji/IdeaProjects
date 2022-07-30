package test.ChangYongLei;

import java.math.BigDecimal;

/**
 * BigDecimal 属于大数据，精度极高，不属于基本数据类型，属于java对象（引用数据类型） java.math.BigDecimal
 * 这是SUN提供的一个类，专门用于财务软件中
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        //这是精度极高的1000
        BigDecimal bigDecimal = new BigDecimal(1000);
        //这是精度极高的3423
        BigDecimal bigDecimal1 = new BigDecimal(3423);
        //两者求和调用add方法，而不是直接用运算符
        //这种数据类型之间的运算都调用对应的方法
        BigDecimal bigDecimal2 = bigDecimal.add(bigDecimal1);
        System.out.println(bigDecimal2);//4423
    }
}

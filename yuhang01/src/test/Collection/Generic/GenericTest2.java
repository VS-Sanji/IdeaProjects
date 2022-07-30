package test.Collection.Generic;

/**
 * 一、什么是泛型？
 * Java泛型设计原则：只要在编译时期没有出现警告，那么运行时期就不会出现ClassCastException异常.
 * 泛型：把类型明确的工作推迟到创建对象或调用方法的时候才去明确的特殊的类型
 * 参数化类型:把类型当作是参数一样传递<数据类型> 只能是引用类型
 * 相关术语：ArrayList<E>中的E称为类型参数变量
 * ArrayList<Integer>中的Integer称为实际类型参数
 * 整个称为ArrayList<E>泛型类型
 * 整个ArrayList<Integer>称为参数化的类型ParameterizedType
 *
 *
 * 二、为什么需要泛型
 * 早期Java是使用Object来代表任意类型的，但是向下转型有强转的问题，这样程序就不太安全
 * 首先，我们来试想一下：没有泛型，集合会怎么样
 * Collection、Map集合对元素的类型是没有任何限制的。本来我的Collection集合装载的是全部的Dog对象，但是外边把Cat对象存储到集合中，是没有任何语法错误的。
 * 把对象扔进集合中，集合是不知道元素的类型是什么的，仅仅知道是Object。因此在get()的时候，返回的是Object。外边获取该对象，还需要强制转换
 *
 * 有了泛型以后：
 *      代码更加简洁【不用强制转换】
 *      程序更加健壮【只要编译时期没有警告，那么运行时期就不会出现ClassCastException异常】
 *      可读性和稳定性【在编写集合的时候，就限定了类型】
 *
 *
 * 有了泛型后使用 增强for 遍历集合
 * 在创建集合的时候，我们明确了集合的类型了，所以我们可以使用增强for来遍历集合！
 *         //创建集合对象
 *         ArrayList<String> list = new ArrayList<>();
 *
 *         list.add("hello");
 *         list.add("world");
 *         list.add("java");
 *
 *         //遍历,由于明确了类型.我们可以增强for
 *         for (String s : list) {
 *             System.out.println(s);
 *         }
 *
 */
public class GenericTest2 {
}

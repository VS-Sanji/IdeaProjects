package test.Map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * java.util.Map接口中常用的方法：
 *      1.Map和Collection没有继承关系
 *      2.Map集合中以key和value的方式存储数据：键值对
 *          key和value都是引用数据类型
 *          key和value都是存储对象的内存地址
 *          key起到主导的地位，value是key的一个附属品
 *      3.Map接口中常用的方法
 *          V put(k key, v value)  向Map集合中添加键值对
 *          V get(Object key)   通过key获取value
 *          void clear()    清空Map集合
 *          boolean containsKey(Object key)   判断Map中是否包含某个key
 *          boolean containsValue(Object value)   判断Map中是否包含某个value
 *          boolean isEmpty()  判断集合是否为空/元素个数是否为0
 *          Set<K> keySet()   获取Map集合中所有的key（所有的key是一个Set集合）
 *          V remove(Object key)   删除该key对应的键值对
 *          int size()   获取Map集合中的键值对个数
 *          Collection<v> values()   获取Map集合中所有的value，返回一个Collection
 *          Set<Map.Entry<K, V>> entrySet()
 *              将Map集合转换为Set集合
 *              假设现在有一个Map集合，如下：
 *                  key         value
 *              -------------------------
 *                  1           zhangsan
 *                  2           lisi
 *                  3           wangwu
 *                  4           zhanhui
 *
 *              Set set = Map.entrySet();
 *              set集合对象
 *                  1=zhangsan    【Map集合通过调用Map.entrySet()方法转换成的这个Set集合，其中的元素的类型是Map.Entry<K, V>】
 *                  2=lisi       【Map.Entry<K, V>和String一样，都是一种类型的名字，只不过在这里：Map.Entry<k, V>是一个静态内部类，是Map中的静态内部类】
 *                  3=wangwu
 *                  4=zhanhui  --->  这个元素的类型是 Map.Entry
 */
public class MapTest01 {
    public static void main(String[] args) {
        //创建Map集合对象
        Map<Integer, String> map = new HashMap<>();
        //添加元素
        map.put(12, "zhansan");
        map.put(2, "wangwu");
        map.put(3, "mafei");
        map.put(5, "zhangfei");

        //通过key获取value
        String s = map.get(2);
        System.out.println(s);//wangwu

        //获取键值对数量
        int i = map.size();
        System.out.println(i);//4

        //通过key删除value
        map.remove(12);//12, "zhansan" 被删除了

        //判断是否包含某个key，contains方法底层调用的都是equals进行比对的，所以自定义的类型都需要重写equals方法
        boolean b = map.containsKey(5);
        System.out.println(b);//true

        //判断是否包含某个value
        boolean b1 = map.containsValue("yuhang");
        System.out.println(b1);//false

        //获取所有的value
        Collection<String> collection = map.values();
        for (String sc : collection){
            System.out.println(sc);
            //wangwu
            //mafei
            //zhangfei
            /**
             * 增强for循环/foreach    对数组或者集合进行遍历
             * 有个缺点：没有下标，在需要下标的时候不建议使用
             * 语法格式：
             *      for(元素类型 变量名 ： 数组或集合){ 数据类型 变量名:声明一个变量用来接收遍历目标遍历后的元素
             *      System.out.println(变量名)
             *      变量名指代的就是数组中或者集合中的元素
             */
        }

        //清空Map集合
        map.clear();
        System.out.println(map.size());//0



        //判断是否为空
        boolean b2 = map.isEmpty();
        System.out.println(b2);//true


    }
}

//静态内部类如下所示（简要的概括一下）
class MyMap {
    private static class Entry<K, V>{
        public void doSome(){
            System.out.println("静态内部类的doSome方法");
        }
    }
}

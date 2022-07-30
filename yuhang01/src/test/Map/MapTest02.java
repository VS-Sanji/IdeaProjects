package test.Map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Map集合的遍历
 */
public class MapTest02 {
    public static void main(String[] args) {
        //第一种方式：获取所有的key，通过key来遍历value
        Map<Integer, String> map = new HashMap<>();
        map.put(12, "zhansan");
        map.put(2, "wangwu");
        map.put(3, "mafei");
        map.put(5, "zhangfei");
        //获取所有的key,得到的是一个Set集合
        Set<Integer> keySet = map.keySet();
        //遍历key，通过key获取value
        //可以用迭代器
        Iterator<Integer> it = keySet.iterator();
        while (it.hasNext()) {
            //取出一个key
            Integer key = it.next();
            //通过key遍历value
            String s = map.get(key);
            System.out.println(s);
            //wangwu
            //mafei
            //zhangfei
            //zhansan
        }

        //foreach也可以
        for (Integer key : keySet){
            System.out.println(map.get(key));
            //wangwu
            //mafei
            //zhangfei
            //zhansan
        }

        //第二种方式，Set<Map.Entry<K, V>> entrySet()
        //将Map集合直接全部转化成Set集合
        //得到的集合中元素的类型是 Map.Entey
        Set<Map.Entry<Integer, String>> set = map.entrySet();
        //遍历set集合 每次取出一个Node对象（Map.Entry<Integer, String>  底层是Node ,Node实现了Entry）  可以使用迭代器，也可以使用for循环
        Iterator<Map.Entry<Integer, String>> it1 = set.iterator();
        while (it1.hasNext()) {
            Map.Entry<Integer, String> m = it1.next();
            Integer it2 = m.getKey();
            String s = m.getValue();
            System.out.println(it2 + "=" + s);
        }

        //foreach   大数据量时常使用，效率比较高，因为获取key和value都是从node对象中直接获取的属性值
        for (Map.Entry<Integer, String> s : set){ // //数据类型 变量名:声明一个变量用来接收遍历目标遍历后的元素  这里目标集合里的元素是Map.Entry<Integer, String> 类型，所以声明它来接收遍历的结果
            System.out.println(s);
        }

        
    }
}

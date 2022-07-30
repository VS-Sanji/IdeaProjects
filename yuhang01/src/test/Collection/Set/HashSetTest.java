package test.Collection.Set;

import java.util.HashSet;
import java.util.Set;

/**
 * HashSet集合：无序不可重复
 *
 */
public class HashSetTest {
    public static void main(String[] args) {
        Set<String> strs = new HashSet<>();

        strs.add("a");
        strs.add("d");
        strs.add("g");
        strs.add("x");
        strs.add("z");
        strs.add("h");
        //以下重复的存不进去
        strs.add("h");
        strs.add("h");
        strs.add("h");

        for (String s : strs){
            System.out.println(s);
            //a
            //d
            //g
            //x
            //h
            //z
            //输出结果和存进去的顺序不同，说明了无序性
            //不可重复
            //放到HashSet集合中的元素实际上是放到HashMap集合的key部分了
        }



    }
}

package test.Collection;

import java.util.*;

/**
 * java.util.Collection 集合接口
 * java.util.Collections 集合工具类，方便集合的操作
 */
public class CollectionsTest {
    public static void main(String[] args) {
        //ArrayList集合不是线程安全的
        List<String> list = new ArrayList<>();

        //将其变成线程安全的
        Collections.synchronizedList(list);

        //排序
        list.add("sdga");
        list.add("bsg");
        list.add("e");
        list.add("ssd");
        list.add("hh");

        Collections.sort(list);
        for (String s : list){
            System.out.println(s);
            //bsg
            //e
            //hh
            //sdga
            //ssd
        }

        List<WuGui> wuGui = new ArrayList<>();
        wuGui.add(new WuGui(1232));
        wuGui.add(new WuGui(12422));
        wuGui.add(new WuGui(12312));
        //注意：对List集合中元素排序，同样也要让List集合实现Comparable接口，重写compareTo方法不然会报错
        //对Set集合不行
        Collections.sort(wuGui);

        //对Set集合怎么排序：
        Set<String> set = new HashSet<>();
        set.add("yuhang");
        set.add("king");
        set.add("zhansan");
        set.add("lisi");
        //将Set集合转成List集合,
        List<String> list1 = new ArrayList<>(set);
        Collections.sort(list1);
        //以下for循环说明set集合中的键值对一个一个放到了数组中，可以从下标定位获取
//        for (int i = 0; i < list1.size(); i++) {
//            System.out.println(list1.get(i));
//
//        }
        for (String s : list1){
            System.out.println(s);
            //king
            //lisi
            //yuhang
            //zhansan
        }
        //或者：Collections.sort(List集合，比较器对象)
    }

}

class WuGui implements Comparable<WuGui>{
    private int age;

    public WuGui() {
    }

    public WuGui(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "乌龟年龄=" + this.age;
    }

    @Override
    public int compareTo(WuGui o) {
        return this.age - o.age;
    }
}

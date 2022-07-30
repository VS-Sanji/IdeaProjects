package test.Map.HashMap;

import java.util.*;

/**
 * HashMap集合：
 *      1.HashMap集合底层是哈希表/散列表数据结构
 *      2.哈希表数据结构：
 *          哈希表是一个数组和单向链表的结合体
 *          数组：在数据查询方面效率很高，但在随机增删方面效率很低
 *          单向链表：在随机增删方面效率很高，在数据查询方面效率很低
 *          哈希表将以上两种数据结构结合在一起，发挥他们各自的长处
 *      3.HashMap底层其实是一个一维的Node数组（Node<K,V>[] table;）
 *       transient Node<K,V>[] table;   Node数组
 *
 *       静态内部类HashMap.Node
 *       static class Node<K,V> implements Map.Entry<K,V> {
 *         final int hash;   哈希值：哈希值是key的hashCode()方法的执行结果。hash值通过哈希算法可以得到Node数组的下标
 *         final K key;   存储到Map集合中的那个key
 *         V value;    存储到Map集合中的那个value
 *         Node<K,V> next;   下一个节点的内存地址
 *
 *      哈希表/散列表：一维数组，这个数组中每一个元素都是一个单向链表 （数组和链表的结合体）
 *
 *      4.最重要的知识点：
 *          掌握向HashMap集合中存/取数据
 *          map.put(k,v)
 *          v = map.get(k)
 * map.put(k,v)实现原理：
 *      第一步：先将k，v封装到node对象中
 *      第二步：底层调用k的hashCode()方法得出hash值，然后通过哈希算法（函数）将hash值转换成Node数组的下标   (同一个单向链表上的节点，其hash值可能不同（哈希碰撞），但是通过哈希算法后得到的结果肯定是一样的，因为下标一样)
 *             如果该下标上没有元素，就将该node对象添加到该位置上
 *             如果该下表上有元素（有链表），那么就会拿该node的key与该链表上所有的node对象的key进行equals比对
 *             如果所有的equals方法返回值都是false，那么将该node添加到这个链表的末尾位置上；如果有equals方法返回true，则将对应节点中的value修改为当前node对象中的value（value被覆盖）
 *
 * v = map.get(k)实现原理：
 *      第一步：先用key的hashCode()方法将key转换成hash值，再通过哈希算法（函数）将hash值转换成Node数组的下标  (同一个单向链表上的节点，其hash值可能不同（哈希碰撞），但是通过哈希算法后得到的结果肯定是一样的，因为下标一样)
 *      第二步：通过下标快速定位到Node数组的某个位置上
 *             如果这个位置上什么也没有，那么返回null
 *             如果这个位置上有单向链表，那么就会拿该node的key与该链表上所有的node对象的key进行equals比对
 *             如果所有的equals方法返回值都是false，那么表示该集合中没有这个这个元素，get方法返回null；如果有equals方法返回true，则取出对应的value，get方法返回这个value（这个value就是我们要找的value）
 *
 * 为什么哈希表/散列表的随机增删，查询效率都挺高
 *      原因：1.增删是在链表上完成，发挥了链表结构的优势
 *           2.查询时并不需要全部扫描，只需要部分扫描
 *
 * 重点：HashMap集合的key，会先后调用hashCode()方法和equals()方法,这两个方法都需要进行重写，否则达不到使用的需求
 *
 * HashMap集合key部分特点：
 *      无序，不可重复
 *      无序：因为在添加元素时指不定加到那个链表上
 *      不可重复：equals方法来保证HashMap集合中的key不可重复，因为在添加的时候，有重复的话就会把对应的value覆盖，不会单独再加一个上去
 *      放在HashMap集合key部分的元素实际上是放到HashSet集合中了  所以HashSet集合中的元素也需要同时重写hashCode()方法和equals()方法
 *
 * 哈希表使用不当时无法充分发挥性能：
 *      假设将所有的hashCode()方法返回值固定为某个值，那么此哈希表就等同与一个单向链表了。这种情况称为：散列分布不均匀
 *      散列分布均匀指的是：假如有100个元素，10个单向链表，那么每个链表上挂10个元素这是最好的，散列分布均匀
 *
 *      假设将所有的hashCode()方法返回值设定都不相同，那么此哈希表就等同于一个纯数组了，没有链表的概念了，也是散列分布不均匀
 *      想要散列分布均匀，需要重写hashCode()方法有一定技巧
 *
 * HashMap集合的默认初始化容量是16，默认加载因子是0.75（这个默认加载因子指的是:当HashMap集合底层数组的容量占用达到75%的时候，数组开始扩容，扩容为原来的两倍）
 * 重点记住：HashMap集合初始化容量必须是2的倍数，这是官方推荐的。这是因为达到散列分布均匀，提高HashMap集合存取效率所必须的
 *
 * 终极结论：放在HashMap集合key部分的，以及放在HashSet集合中的元素，需要同时重写hashCode方法和equals方法
 *
 *
 * JDK8的新特性：在HashMap集合中，单向链表上节点数超过8之后，会转成红黑树结构；节点数小于6则会转回单向链表结构.这种做法也是为了提高检索效率，二叉树的检索会再次缩小检索范围，提高效率
 *
 * 对于哈希表数据结构：
 *      如果o1和o2的hash值相同，那肯定是放到同一个单向链表上
 *      如果o1和o2的hash值不同，但是由于哈希算法执行结束之后得到的数组下标可能相同，此时发生“哈希碰撞”
 */
public class HashMapTest {
    public static void main(String[] args) {
        //测试HashMap集合中key部分特点
        //这里Integer是key。其hashCode()方法和equals()方法都默认重写了
        Map<Integer, String> map = new HashMap<>();
        //添加元素
        map.put(1,"zhanhui");
        map.put(2, "yuhang");
        map.put(3, "feifei");
        map.put(3,"java");//key重复，value自动覆盖

        int i =  map.size();
        System.out.println(i);//3  这里存在key相同，所以"feifei"被"java"覆盖了，元素个数只有三个

        //遍历map集合
        //先转成Set集合
        Set<Map.Entry<Integer,String>> set = map.entrySet();
        for (Map.Entry<Integer,String> m : set){
            System.out.println(m);
            //1=zhanhui
            //2=yuhang
            //3=java

        }


        /**
         * 1.向Map集合中存，以及从Map集合中取，都是先调用key的hashCode()方法，然后调用equals()方法
         * equals()方法有可能调用，也有可能不调用，要是某个下标位置上，没有元素，那么就不用调用
         *      拿put(k，v)举例：首先，调用hashCode()方法返回哈希值，然后哈希值经过哈希算法转成下标，如果下标位置上是null，equals不用执行，直接放就行了
         *      get(k)也是一样的道理
         * 2.如果一个类的equals方法重写了，那么这个类的hashCode方法也要重写 ，防止equals证明是同一个对象后，还因为hash值不同（且未发生哈希碰撞）而存入Map集合; 或者equals()方法判定不是同一个对象，却又因为hashcode相同发生覆盖，只存进去一个
         * 3.用IDEA工具自动生成即可
         */
        Student student1 = new Student(1,"yuhang");
        Student student2 = new Student(1,"yuhang");

        //重写equals方法之前是false
        //System.out.println(student1.equals(student2));

        //重写equals方法之后是true
        System.out.println(student1.equals(student2));

        System.out.println(student1.hashCode());//460141958  重写hashCode方法之后 32
        System.out.println(student2.hashCode());//1163157884 重写hashCode方法之后 32

        //重写equals方法之后，二者是同一个，那么往HashSet集合中放的话，按道理只能放一个，因为HashSet集合无序不可重复
        HashSet<Student> hashSet = new HashSet<>();
        hashSet.add(student1);
        hashSet.add(student2);
        //System.out.println(hashSet.size());  重写hashCode方法之前是 2  重写之前，得到两个不同的hash值，通过哈希算法得到不一样的下标，在各自下标对应的单向链表中equals（下标位置上有单向链表，没有直接存），若equals全返回false，则各自存进去
        System.out.println(hashSet.size());  //重写hashCode方法之后是 1  重写之后，得到相同hash值，通过哈希算法得到一样的下标，通过equals方法比对得出是同一个，则只存进一个


        /**
         * HaspMap集合的key可以为空，但是只能有一个
         */
        Map map1 = new HashMap();
        map1.put(null,"yuhang");
        map1.put(null,"zhanhui");//key重复，value覆盖
        System.out.println(map1.size());//1
        System.out.println(map1.get(null));//zhanhui  也可以用null来获取值

    }
}

class Student {
    //学号
    private int no;
    //姓名
    private String name;

    public Student() {
    }

    public Student(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return no == student.no && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no);
    }
}

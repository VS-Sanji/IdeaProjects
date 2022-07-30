package lianxiti;
/*
通过方法重载、方法重复利用完成以下功能：
1、定义一个方法，该方法可以选出2个int类型较大的数据，返回值是较大的数据。

2、再定义一个方法，该方法可以选出3个int类型中较大的数据，返回值是较大的数据。

3、要求使用方法重载机制，要求代码体现出重复利用。

main方法中编写程序进行测试。
 */
public class LianXi7 {
    public static void method(int i,int j){
        if (i > j) {
            System.out.println(i);
        }else{
            System.out.println(j);
        }
    }


    public static void method(int i,int j,int k){
        if (i > j && i > k) {
            System.out.println(i);
        } else if (j > k) {
            System.out.println(j);
        }else{
            System.out.println(k);
        }
    }

    public static void main(String[] args) {
        method(5,9);
        method(3,5,6);
    }
}

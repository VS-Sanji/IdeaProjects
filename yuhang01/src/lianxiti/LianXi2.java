package lianxiti;
//编写程序，计算5的阶乘。
public class LianXi2 {
    public static int method(int i){
        int result;
        if (i == 1) {      //要有结束条件不然会一直递推下去
            return i=1;
        } else {
            result = i * method(i - 1);   //方法就是输入一个数，则作乘法运算
            return result;
        }

    }
    public static void main(String[] args) {
        method(5);
        System.out.println(method(5));
    }
}

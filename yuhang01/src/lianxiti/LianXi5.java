package lianxiti;
//使用递归方式计算N的阶乘。
public class LianXi5 {
    public static int method(int i){
        int result;
        if (i == 1) {
            return i;
        }else{
            result=i*method(i-1);
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(method(6));
    }
}

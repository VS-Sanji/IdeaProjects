package lianxiti;
//篮球从5米高的地方掉下来，每次弹起的高度是原来的30%，经过几次弹起，篮球的高度是0.1米。
public class LianXi4{
    public static void method(double i){
        int count=0;
      while(i>0.1){
          i*=0.3;
          count++;
      }
        System.out.println(count);
    }

    public static void main(String[] args) {
        method(5.0);
    }
}

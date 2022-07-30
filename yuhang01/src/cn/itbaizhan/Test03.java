package cn.itbaizhan;

public class Test03 {
    public static void main(String[] args) {
        int age=(int)(100*Math.random());
        System.out.println("年龄"+age+"，属于");

        if (age<15){
            System.out.println("儿童");
        }else if (age<25){
            System.out.println("青年");
        }else if (age<35){
            System.out.println("中年");
        }else if (age<45){
            System.out.println("老年");
        }else{
            System.out.println("老寿星");
        }
    }
}

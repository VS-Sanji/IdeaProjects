package cn.itbaizhan;
//测试if语句，一个简单的掷骰子游戏
public class TestIf01 {
    public static void main(String[] arg){
        double d=Math.random();  //Math.random()生成[0，1）之间的随机数
        int a=1+(int)(Math.random()*6);   //  生成[1,6]之间的随机数
        int b=1+(int)(Math.random()*6);   //  生成[1,6]之间的随机数
        int c=1+(int)(Math.random()*6);   //  生成[1,6]之间的随机数
        int count=a+b+c;

        if (count>15){
            System.out.println("手气不错，继续玩！");
        }

        if (10<count&&count<=15){         //不能写成: 10<count<=15
            System.out.println("手气一般，喝口水。");
        }

        if(count<10){
            System.out.println("手气很臭，回家吧！");
        }

        System.out.println("第一个骰子："+a+"\n第二个骰子：" +b+"\n第三个骰子："+c);

       /*
        if(a<=3){
            System.out.println("小");
        }else{
            System.out.println("大");
        }

        */
    }
}

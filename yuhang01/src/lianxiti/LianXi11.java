package lianxiti;
//定义猴子类，猴子有名字和性别等属性，并且定义猴子说话的方法，定义人类，人有名字和性别等属性，并且定义人说话的方法。
//使用继承，让代码具有复用性。
public class LianXi11 {
    public static void main(String[] args) {
        Man m=new Man();
        m.speak();
    }

}
 class Monkey{
     private String name;

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     private boolean sex;

     public boolean isSex() {
         return sex;
     }

     public void setSex(boolean sex) {
         this.sex = sex;
     }

     public void speak(){
        System.out.println("猴子在欢呼！！！");
    }
}


class  Man extends Monkey{
    private String name;
    private boolean sex;

    public void speak(){
        System.out.println("人们在聊天！！！");
    }
}


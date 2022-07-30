package lianxiti;

import com.sun.org.apache.xerces.internal.impl.dv.xs.AnyURIDV;

//定义动物类，动物有名字属性，并且定义动物移动的方法，定义鱼类，鱼有名字属性，有颜色属性，并且定义鱼移动的方法。
//使用继承，让代码具有复用性。
public class LianXi12 {
    public static void main(String[] args) {
        Fish fish=new Fish();
        fish.move();
    }
}
class Animal1{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void move(){
        System.out.println("动物在移动！！！");
    }
}

class Fish extends Animal1{
    public void move(){
        System.out.println("鱼儿在游动！！！");
    }

}
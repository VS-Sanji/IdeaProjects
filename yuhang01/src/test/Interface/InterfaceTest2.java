package test.Interface;
/**extends和implements可以共存，extends在前，implements在后。使用接口，写代码的时候，可以使用多态（父类型引用指向子类型对象）。*/
public class InterfaceTest2 {
    public static void main(String[] args) {
        Flyable f=new Cat();  //多态  父类引用指向子类对象
        f.fly();
    }
}

class Animal2{

}

interface Flyable{
    void fly();
}

class Cat extends Animal2 implements Flyable{
    @Override
    public void fly() {
        System.out.println("猫在飞！！！");
    }
}

class Pig implements Flyable{   //没写extends默认继承Object类

    @Override
    public void fly() {
        System.out.println("飞猪！！！");
    }
}
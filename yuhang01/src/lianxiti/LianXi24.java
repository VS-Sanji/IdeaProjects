package lianxiti;
//编写程序模拟“主人”喂养“宠物”的场景：
//提示1：
//	主人类：Master
//	宠物类：Pet
//	宠物类子类：Dog、Cat、YingWu
//提示2：
//	主人应该有喂养的方法：feed()
//	宠物应该有吃的方法：eat()
//	只要主人喂宠物，宠物就吃。
//
//要求：主人类中只提供一个喂养方法feed()，要求达到可以喂养各种类型的宠物。
//
//编写测试程序：
//	创建主人对象
//	创建各种宠物对象
//	调用主人的喂养方法feed()，喂养不同的宠物，观察执行结果。
//
//通过该案例，理解多态在开发中的作用。
//重要提示：feed方法是否需要一个参数，参数选什么类型！！！
public class LianXi24 {
    public static void main(String[] args) {
        Pet p1=new Dog();
        Pet p2=new Cat();
        Pet p3=new YingWu();
        Master m=new Master();
        m.feed(p1);
        m.feed(p2);
        m.feed(p3);

    }
}

class Master{
    //无参构造方法
    public Master() {
    }

    public void feed(Pet pet){
        pet.eat();
    }
}

class Pet{
    //无参构造方法
    public Pet() {
    }

    public void eat(){
        System.out.println("宠物在吃东西！！");
    }
}


class Dog extends Pet{
    //无参构造方法
    public Dog() {
    }

    @Override
    public void eat() {
        System.out.println("小狗在吃骨头！！！");
    }
}

class Cat extends Pet{
    //无参构造方法
    public Cat() {
    }

    @Override
    public void eat() {
        System.out.println("小猫在吃猫粮！！！");
    }
}

class YingWu extends Pet{
    //无参构造方法
    public YingWu() {
    }

    @Override
    public void eat() {
        System.out.println("鹦鹉在啄米！！！");
    }
}

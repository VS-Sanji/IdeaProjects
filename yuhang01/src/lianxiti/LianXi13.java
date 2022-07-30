package lianxiti;
//我们有很多种小鸟，每种小鸟都有飞的行为，还有一个弹弓，弹弓有一个弹射的行为，
//弹射时把小鸟弹出去，之后小鸟使用自己飞行的行为飞向小猪（不要求编写小猪的代码）。不同种类的小鸟有不同飞行的方式：
//
//红火：红色小鸟，飞行方式：正常
//
//蓝冰：蓝色小鸟，飞行方式：分成3个
//
//黄风：黄色小鸟，飞行方式：加速。
public class LianXi13 {
    public static void main(String[] args) {
        /**创建子类红色鸟儿对象赋给父类引用，再用父类引用去调用子类中已经重写的方法，就构成了多态机制*/
        Bird redBird=new RedBird();

        Bird blueBird=new BlueBird();

        Bird yellowBird=new YellowBird();

        SlingShot slingShot=new SlingShot();


        /**相当于把创建的红色鸟儿对象地址传递给了shot方法中的bird引用，再用bird引用去调了红色鸟儿对象的fly方法
         与直接用redBird.fly()去调用fly效果相同
         */
        slingShot.shot(redBird);   /**将创建的父类引用给到shot方法，再让其去执行fly方法，构成多态，达到不同的效果*/
        slingShot.shot(blueBird);
        slingShot.shot(yellowBird);


    }
}

/**相当于多加了一环，用弹弓的shot方法去调用鸟儿的fly方法*/
class SlingShot{
    public void shot(Bird bird){   /**弹弓的方法就是让鸟儿飞，但是不同的鸟飞的表现形式不同，所以考虑使用多态机制，所以传递一个父类引用做形参   */
        bird.fly();
    }
}
class Bird{
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void fly(){
        System.out.println("鸟儿在飞！！！");
    }
}

class RedBird extends Bird{

    public void fly() {
        System.out.println("红鸟在正常飞行！！！");
    }
}

class BlueBird extends Bird{

    public void fly() {
        System.out.println("蓝鸟分成了三个在飞！！！");
    }
}

class YellowBird extends Bird{

    public void fly() {
        System.out.println("黄鸟加速在飞！！！");
    }
}
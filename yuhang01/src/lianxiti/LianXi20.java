package lianxiti;

//（1）定义一个抽象类Weapon,该抽象类有两个抽象方法attack()，move()
//这两个方法分别表示武器的攻击方式和移动方式。
//
//（2）定义3个类：Tank,Flighter,WarShip都继承自Weapon,
//分别用不同的方式实现Weapon类中的抽象方法。
//
//编写测试程序main方法，创建多个不同武器的实例。并分别调用attack()和move()方法。
//能用多态尽量使用多态。
public class LianXi20 {
    public static void main(String[] args) {
        Weapon1 w_tank=new Tank();                    //父类引用指向子类对象  解耦合
        Weapon1 w_flighter=new Flighter();
        Warship w_warship=new Warship();
        w_tank.attack();
        w_tank.move();
        w_flighter.attack();
        w_flighter.move();
        w_warship.attack();
        w_warship.move();
    }
}


class Weapon1 {
    //无参构造方法
    public Weapon1() {
    }

    //攻击
    public void attack(){
    }

    //移动
    public void move(){
    }
}

class Tank extends Weapon1 {
    //无参构造方法
    public Tank() {
    }

    //攻击重写
    public void attack(){
        System.out.println("发射炮弹！！！");
    }

    //移动重写
    public void move(){
        System.out.println("坦克在前进！！！");
    }

}

class Flighter extends Weapon1 {
    //无参构造方法
    public Flighter() {
    }

    //攻击重写
    public void attack(){
        System.out.println("空投炸弹！！！");
    }

    //移动重写
    public void move(){
        System.out.println("战斗机在飞行！！！");
    }
}

class Warship extends Weapon1 {
    //无参构造方法
    public Warship() {
    }

    //攻击重写
    public void attack(){
        System.out.println("发射鱼雷！！！");
    }

    //移动重写
    public void move(){
        System.out.println("军舰在航行！！！");
    }
}
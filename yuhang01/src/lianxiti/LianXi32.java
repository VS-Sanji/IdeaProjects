package lianxiti;

import com.sun.org.apache.regexp.internal.RE;

//写一个类Army,代表一支军队，这个类有一个属性Weapon数组w（用来存储该军队所拥有的所有武器），
//	该类还提供一个构造方法，在构造方法里通过传一个int类型的参数来限定该类所能拥有的最大武器数量,
//	并用这一大小来初始化数组w。
//
//	该类还提供一个方法addWeapon(Weapon wa),表示把参数wa所代表的武器加入到数组w中。
//	在这个类中还定义两个方法attackAll()让w数组中的所有武器攻击；
//	以及moveAll()让w数组中的所有可移动的武器移动。
//
//	写一个主方法去测试以上程序。
//
//	提示：
//		Weapon是一个父类。应该有很多子武器。
//		这些子武器应该有一些是可移动的
public class LianXi32 {
    public static void main(String[] args) {
        Army army = new Army(8);
        Tank1 tank1 = new Tank1("沾灰一号");
        Tank1 tank2 = new Tank1("沾灰二号");
        Tank1 tank3 = new Tank1("沾灰三号");
        Plane plane = new Plane("送个一号");
        Plane plane1 = new Plane("送个二号");
        Plane plane2 = new Plane("送个三号");
        Plane plane3 = new Plane("送个四号");
        army.addWeapon(tank1);
        army.addWeapon(tank2);
        army.addWeapon(plane);
        army.addWeapon(plane1);
        army.addWeapon(tank3);
        army.attackAll();
        army.moveAll();
    }

}


class Army {
    //武器数组
    private Weapon[] w;

    //添加武器
    public void addWeapon(Weapon wa){
        for (int i = 0; i < w.length; i++) {
            if (w[i] == null) {
                System.out.println("第" + (i + 1) + "个仓库空闲，可以存放武器");
                System.out.println("正在存放");
                w[i] = wa;
                System.out.println("存放完成");
                break;
            }
            if (w[i] != null) {
                continue;
            }
        }
    }

    //武器全部攻击
    public void attackAll(){
        for (int i = 0; i < w.length; i++) {
            if (w[i] != null) {
                System.out.println(w[i].attack());
            }
        }
    }

    //武器全部移动
    public void moveAll(){
        for (int i = 0; i < w.length; i++) {
            if (w[i] != null) {
                System.out.println(w[i].move());
            }
        }
    }

    //无参构造方法
    public Army() {
    }

    //有参构造方法
    public Army(int i) {
        w = new Weapon[i];
    }

    //有参构造方法
    public Army(Weapon[] w) {
        this.w = w;
    }

    //setter / getter 方法
    public Weapon[] getW() {
        return w;
    }

    public void setW(Weapon[] w) {
        this.w = w;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Army)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Army am = (Army)obj;
        return this.w.equals(am.w);
    }

    @Override
    public String toString() {
        return "该军队有" + this.w.length + "个武器库";
    }


}

class Weapon{
    //武器名称
    private String name;

    //攻击方式
    public String attack(){
        return null;
    }

    //移动方式
    public String move(){
        return null;
    }

    //无参构造方法
    public Weapon() {
    }

    //有参构造方法
    public Weapon(String name) {
        this.name = name;
    }

    //setter / getter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Weapon)){
            return false;
        }
        if (this == obj) {
            return true;
        }
        Weapon wp = (Weapon)obj;
        return this.name.equals(wp.name);
    }

    @Override
    public String toString() {
        return "武器：" + this.name;
    }
}





class Tank1 extends Weapon{
    //坦克名称
    private String name;

    @Override
    public String attack() {
        return this.name + "发射炮弹";
    }

    @Override
    public String move() {
        return this.name + "在前进";
    }

    //无参构造方法
    public Tank1() {
    }

    //有参构造方法
    public Tank1(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Tank1)){
            return false;
        }
        if (this == obj) {
            return true;
        }
        Tank1 t = (Tank1)obj;
        return this.name.equals(t.name);
    }

    @Override
    public String toString() {
        return this.name + "坦克";
    }
}



class Plane extends Weapon{
    //飞机名称
    private String name;

    @Override
    public String attack() {
        return this.name + "在轰炸";
    }

    @Override
    public String move() {
        return this.name + "在飞行";
    }

    //无参构造方法
    public Plane() {
    }

    //有参构造方法
    public Plane(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Plane)){
            return false;
        }
        if (this == obj) {
            return true;
        }
        Plane p= (Plane)obj;
        return this.name.equals(p.name);
    }

    @Override
    public String toString() {
        return this.name + "飞机";
    }
}
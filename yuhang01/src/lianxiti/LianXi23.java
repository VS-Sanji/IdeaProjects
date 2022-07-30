package lianxiti;
//编写程序实现乐手弹奏乐器。乐手可以弹奏不同的乐器从而发出不同的声音。
//可以弹奏的乐器包括二胡、钢琴和琵琶。
//	实现思路及关键代码：
//		1)定义乐器类Instrument，包括方法makeSound()
//		2)定义乐器类的子类：二胡Erhu、钢琴Piano和小提琴Violin
//		3)定义乐手类Musician，可以弹奏各种乐器play(Instrument i)
//		4)定义测试类，给乐手不同的乐器让他弹奏
public class LianXi23 {
    public static void main(String[] args) {
        Instrument i1=new Erhu();
        Instrument i2=new Piano();
        Instrument i3=new violin();
        Musician musician=new Musician();
        musician.play(i1);
        musician.play(i2);
        musician.play(i3);
    }
}


class Instrument{
    public void makeSound(){
        System.out.println("乐器在发出声音!!!");
    }
}


class Erhu extends Instrument{
    //无参构造方法
    public Erhu() {
    }

    @Override
    public void makeSound() {
        System.out.println("二胡的声音！！！");
    }
}


class  Piano extends Instrument{
    //无参构造方法
    public Piano() {
    }

    @Override
    public void makeSound() {
        System.out.println("钢琴的声音！！！");
    }
}

class violin extends Instrument{
    //无参构造方法
    public violin() {
    }

    @Override
    public void makeSound() {
        System.out.println("小提琴的声音！！！");
    }
}

class Musician{
    //无参构造方法
    public Musician() {
    }

    public void play(Instrument i){
        i.makeSound();
    }
}

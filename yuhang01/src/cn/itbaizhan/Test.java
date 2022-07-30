package cn.itbaizhan;



//定义一个计算机类
class Computer{
    String brand;   //品牌
    int xinhao;     //型号
    String color;    //颜色
}


//定义一个学生类
class Student{
    int id;   //学号
    String name;  //姓名
    Computer diannao;
}



public class Test {
    public static void main(String[] args) {
        Computer s1 = new Computer();
        Student songyuhang = new Student();
        songyuhang.id = 957949;
        songyuhang.name = "宋宇航";
        s1.brand = "惠普";
        s1.xinhao = 123456789;
        s1.color = "红色";
        songyuhang.diannao = s1;
        System.out.println(songyuhang.diannao.brand);
        System.out.println(songyuhang.diannao.xinhao);
        System.out.println(songyuhang.diannao.color);
        System.out.println(songyuhang.id);
        System.out.println(songyuhang.name);
    }
}
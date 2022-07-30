package lianxiti;
//定义一个学生类
//属性包括：学生编号、学生姓名、学生年龄
//属性私有化，对外提供公开的set和get方法
//提供无参构造和有参数构造方法
//提供一个display()方法，通过该方法打印学生信息。
//编写测试程序创建对象，调用display()方法。
public class LianXi17 {
    public static void main(String[] args) {
        Student student=new Student(0523,"沾灰",22);
        student.display();
        student.setBianhao(2635);
        student.setName("宋宇航");
        student.setAge(22);
        student.display();
    }
}


class Student{
    //编号
    private int bianhao;
    //姓名
    private String name;
    //年龄
    private int age;


    public int getBianhao() {
        return bianhao;
    }

    public void setBianhao(int bianhao) {
        this.bianhao = bianhao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //无参构造方法
    public Student() {
    }

    //有参构造方法
    public Student(int bianhao, String name, int age) {
        this.bianhao = bianhao;
        this.name = name;
        this.age = age;
    }

    public void display(){
        System.out.println("编号是："+getBianhao()+"\t"+"姓名是："+getName()+"\t"+"年龄是："+getAge());
    }
}
package lianxiti;
//（1）定义接口A，里面包含值为3.14的常量PI和抽象方法double area()。
//（2）定义接口B，里面包含抽象方法void setColor(String c)。
//（3）定义接口C，该接口继承了接口A和B，里面包含抽象方法void volume()。
//（4）定义圆柱体类Cylinder实现接口C，该类中包含三个成员变量：底圆半径radius、
//圆柱体的高height、颜色color。
//（5）创建主类来测试类Cylinder。
public class LianXi27 {
    public static void main(String[] args) {
        Cylinder cylinder=new Cylinder(25.0,3.4,"yellow");
        System.out.println("面积是"+cylinder.area());
        System.out.println(cylinder.getColor());
        cylinder.volume();
    }
}


interface A{
    double PI=3.14;
    double area();
}

interface B{
    void setColor(String c);
}

interface C extends A,B{
    void volume();
}

class Cylinder implements C{
    //底面圆半径
    private double radius;
    //高
    private double height;
    //颜色
    private String color;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public Cylinder() {
    }

    public Cylinder(double radius, double height, String color) {
        this.radius = radius;
        this.height = height;
        this.color = color;
    }

    @Override
    public double area() {
        return PI*radius*radius+2*PI*radius*height;
    }

    @Override
    public void setColor(String c) {
        this.color=c;
    }

    @Override
    public void volume() {
        System.out.println("体积是："+PI*radius*radius*height);
    }
}
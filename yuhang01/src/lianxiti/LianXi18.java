package lianxiti;
//定义一个人类
//属性包括：姓名、年龄、性别
//属性私有化，对外提供公开的set和get方法
//提供无参数构造和有参数构造
//提供一个生病方法：ill()，在该方法中打印信息，例如：张三，男，29岁生病了
public class LianXi18 {
    public static void main(String[] args) {
        Person Person=new Person("詹辉",25,true);
        Person.setName("宋宇航");
        Person.setAge(23);
        Person.ill();
    }
}


class Person{
    //姓名
    private String name;
    //年龄
    private int age;
    //性别
    private boolean sex;

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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }


    //无参构造方法
    public Person() {
    }

    //有参构造方法
    public Person(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public void ill(){
        System.out.println("姓名："+getName()+"\t"+"性别："+(isSex() ? "男" : "女" )+"\t"+"年龄："+getAge()+"\t"+"发癫了！！！");
    }
}

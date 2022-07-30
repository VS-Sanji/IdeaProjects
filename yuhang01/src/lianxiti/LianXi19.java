package lianxiti;
//定义一个病毒类
//属性包括：病毒名称name、病毒体积size、病毒类型type
//属性私有化，对外提供公开的set和get方法
//提供无参数构造和有参数构造
//提供一个攻击attack()方法，该方法的参数是“人”，例如：attack(Person p)
//在attack()方法中调用人对象的生病方法。
//
//编写测试程序，创建病毒对象，创建人对象，模拟病毒攻击人。
public class LianXi19 {
    public static void main(String[] args) {
        Virus virus=new Virus("流感",0.2,"DNA病毒");
        Person person=new Person("沾灰",25,false);
        person.setName("卢宇晨");
        person.setAge(22);
        person.setSex(false);
        person.setName("程龙");
        person.setAge(22);
        person.setSex(true);
        System.out.println("名称："+ virus.getName()+"\t"+"面积："+virus.getSize()+"\t"+ virus.getType());
        virus.attack(person);
    }
}


class Virus{
    //姓名
    private String name;
    //面积
    private double size;
    //类型
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //无参
    public Virus() {
    }

    //有参
    public Virus(String name, double size, String type) {
        this.name = name;
        this.size = size;
        this.type = type;
    }


    public void attack(Person person){
        person.ill();
    }
}

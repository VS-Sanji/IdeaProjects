package lianxiti;
//定义“人”类，“人”类包括这些属性：姓名、性别、年龄等。
// 使用封装的方式编写程序，要求对年龄进行安全控制，合法的年龄范围为[0~100]，其他值表示不合法。
public class LianXi10 {
    public static void main(String[] args) {
        People people=new People();
        people.setName("宋宇航");
        people.setAge(225);
        people.setSex(true);
        System.out.println("姓名是："+people.getName());
        System.out.println("年龄是："+ people.getAge());
        System.out.println("年龄是："+ (people.isSex() ? "男" : "女"));
        System.out.println("性别 =" + (people.isSex() ? "男" : "女"));
    }
}
  class People{
    private String name;
    private boolean sex;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) {
            System.out.println("输入的值不合法!!!");
        }else{
            this.age = age;
        }
    }
}


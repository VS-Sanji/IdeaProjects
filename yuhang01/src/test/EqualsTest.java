package test;
/**JDK类库的根类：Object

 2.1、这个老祖宗类中的方法我们需要先研究一下，因为这些方法都是所有子类通用的。
 任何一个类默认继承Object。就算没有直接继承，最终也会间接继承。

 2.2、Object类当中有哪些常用的方法？
 我们去哪里找这些方法呢？
 第一种方法：去源代码当中。（但是这种方式比较麻烦，源代码也比较难）
 第二种方法：去查阅java的类库的帮助文档。

 什么是API？
 应用程序编程接口。（Application Program Interface）
 整个JDK的类库就是一个javase的API。
 每一个API都会配置一套API帮助文档。
 SUN公司提前写好的这套类库就是API。（一般每一份API都对应一份API帮助文档。）

 目前为止我们只需要知道这几个方法即可：
 protected Object clone()   // 负责对象克隆的。
 int hashCode()	// 获取对象哈希值的一个方法。
 boolean equals(Object obj)  // 判断两个对象是否相等
 String toString()  // 将对象转换成字符串形式
 protected void finalize()  // 垃圾回收器负责调用的方法

 2.3、toString()方法
 以后所有类的toString()方法是需要重写的。
 重写规则，越简单越明了就好。

 System.out.println(引用); 这里会自动调用“引用”的toString()方法。

 String类是SUN写的，toString方法已经重写了。

 2.4、equals()方法
 以后所有类的equals方法也需要重写，因为Object中的equals方法比较的是两个对象的内存地址，我们应该比较内容，所以需要重写。

 重写规则：自己定，主要看是什么和什么相等时表示两个对象相等。

 基本数据类型比较实用：==
 对象和对象比较：调用equals方法

 String类是SUN编写的，所以String类的equals方法重写了。
 以后判断两个字符串是否相等，最好不要使用==，要调用字符串对象的equals方法。

 注意：重写equals方法的时候要彻底。

 2.5、finalize()方法。
 这个方法是protected修饰的，在Object类中这个方法的源代码是？
 protected void finalize() throws Throwable { }
 */
public class EqualsTest {
    public static void main(String[] args) {
        User user=new User("宋宇航",22213,new Address("武汉","洪山区","黎明新居"));
        User user1=new User("宋宇航",22213,new Address("武汉","洪山区","黎明村"));
        boolean b=user.equals(user1);
        System.out.println(b);
        String s=user.toString();
        System.out.println(s);
        System.out.println(user);  //直接用引用，默认调用引用的toString方法

    }
}

class User{   //equals方法重写要彻底，比如这里比较addr时，要比较当中的city，area，town，那么在Address中也要把equals方法重写，不然的话调用的还是Object中的equals方法，从而比较的是两者的内存地址
    //姓名
    private String name;
    //身份证号
    private int idCard;
    //住址
    private Address addr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public User() {
    }

    public User(String name, int idCard, Address addr) {
        this.name = name;
        this.idCard = idCard;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "姓名："+this.name+"身份证号："+this.idCard;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null && !(obj instanceof User)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        User user=(User)obj;
        return this.name.equals(user.name) && this.idCard==user.idCard && this.addr.equals(user.addr);
    }
}

class Address{                             //String类中的equals方法SUN公司已经重写了，所以直接调用就好了
    //城市
    private String city;
    //区
    private String area;
    //镇
    private String town;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Address() {
    }

    public Address(String city, String area, String town) {
        this.city = city;
        this.area = area;
        this.town = town;
    }

    @Override
    public String toString() {
        return "城市"+this.city+"区"+this.area+"镇"+this.town;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null && !(obj instanceof Address)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Address address=(Address)obj;                     //传进来的对象已经向上转型为Object类，而父类中没有city，area，town等属性，所以需要进行向下转型再进行判断
        return this.city.equals(address.city) && this.area.equals(address.area) && this.town.equals(address.town);
    }
}
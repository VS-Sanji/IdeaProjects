package lianxiti;
//设计一个笔记本电脑类，属性随意，并且进行属性私有化，对外提供公开的set和get方法。
//
//	设计一个可插拔的接口：InsertDrawable，该接口有什么方法自行定义。
//
//	设计一个鼠标类，实现InsertDrawable接口，并实现方法。
//	设计一个键盘类，实现InsertDrawable接口，并实现方法。
//	设计一个显示器类，实现InsertDrawable接口，并实现方法。
//	设计一个打印机类，实现InsertDrawable接口，并实现方法。
//
//	在“笔记本电脑类”中有一个InsertDrawable接口属性，可以让笔记本电脑可插拔鼠标、键盘、显示器、打印机等。
//
//	编写测试程序，创建多个对象，演示接口的作用。
public class LianXi26 {
    public static void main(String[] args) {
        InterDrawable shuBiao=new ShuBiao();
        InterDrawable jianPan=new JianPan();
        InterDrawable xianShiQi=new JianPan();
        InterDrawable dayingji=new DaYingJi();
        Notebook notebook1=new Notebook("宋宇航",123123,shuBiao);
        Notebook notebook2=new Notebook("宋宇航",123123,jianPan);
        Notebook notebook3=new Notebook("宋宇航",123123,xianShiQi);
        Notebook notebook4=new Notebook("宋宇航",123123,dayingji);
        notebook1.order();
        notebook2.order();
        notebook3.order();
        notebook4.order();
    }
}


class Notebook{                                   //一个模块，调用者
    //用户名
    private String userName;                      //三个属性，封装，然后提供set/get方法给外界，
    //密码
    private int password;
    //接口
    private InterDrawable interDrawable;//常与多态联合满足扩展性，所以传进来一个父类引用，指向的是一个子类对象，运行的时候调用子类对象中的方法

    //无参构造函数
    public Notebook() {                           //提供无参构造方法，用来创建对象并给属性赋默认值
    }

    //有参构造函数
    public Notebook(String userName, int password, InterDrawable interDrawable) {          //提供无参构造方法，用来创建对象并给属性手动赋值
        this.userName = userName;
        this.password = password;
        this.interDrawable = interDrawable;
    }

    public String getUser() {                       //set/get方法，给外界提供窗口读取或者修改属性的数据
        return userName;
    }

    public void setUser(String user) {
        this.userName = user;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public InterDrawable getInterDrawable() {
        return interDrawable;
    }

    public void setInterDrawable(InterDrawable interDrawable) {
        this.interDrawable = interDrawable;
    }

    public void order(){                       //提供一个方法，用来调用该引用指向的对象的imputOrOutput()方法
        this.interDrawable.imputOrOutput();
    }
}

interface InterDrawable{                       //一个模块，接口。完全抽象，无法实例化，需要有类来实现它
    void imputOrOutput();
}

class ShuBiao implements InterDrawable{        //一个模块，实现者。类来实现完全抽象的接口，实现其中的方法  （下面几个一样的道理）
    @Override
    public void imputOrOutput() {
        System.out.println("鼠标点击");
    }
}

class JianPan implements InterDrawable{
    @Override
    public void imputOrOutput() {
        System.out.println("键盘打字");
    }
}

class XianShiQi implements InterDrawable{
    @Override
    public void imputOrOutput() {
        System.out.println("显示器显示");
    }
}

class DaYingJi implements InterDrawable{
    @Override
    public void imputOrOutput() {
        System.out.println("打印机打印");
    }
}


//public class Master{
//    public void feed(Animal a){
//        // 面向Animal父类编程，父类是比子类更抽象的。
//        //所以我们叫做面向抽象编程，不要面向具体编程。
//        //这样程序的扩展力就强。
//    }
//}

/**接口在开发中的作用

    注意：接口在开发中的作用，类似于多态在开发中的作用。

    多态：面向抽象编程，不要面向具体编程。降低程序的耦合度。提高程序的扩展力。
        public class Master{
            public void feed(Dog d){}
            public void feed(Cat c){}
            //假设又要养其它的宠物，那么这个时候需要再加1个方法。（需要修改代码了）
            //这样扩展力太差了，违背了OCP原则（对扩展开放，对修改关闭。）
        }

接口在开发中的作用？
    接口是不是完全抽象的？是。
    而我们以后正好要求，面向抽象编程。
    面向抽象编程这句话以后可以修改为：面向接口编程。
    有了接口就有了可插拔。可插拔表示扩展力很强。不是焊接死的。

    主板和内存条之间有插槽，这个插槽就是接口，内存条坏了，可以重新
    买一个换下来。这叫做高扩展性。（低耦合度。）

    接口在现实世界中是不是到处都是呢？
    螺栓和螺母之间有接口
    灯泡和灯口之间有接口
    笔记本电脑和键盘之间有接口（usb接口，usb接口是不是某个计算机协会制定的协议/规范。）
    接口有什么用？扩展性好。可插拔。
    接口是一个抽象的概念。

    分析：
    中午去饭馆吃饭，这个过程中有接口吗？

    接口是抽象的。

    菜单是一个接口。（菜单上有一个抽象的照片：西红柿炒鸡蛋）

    谁面向接口调用。（顾客面向菜单点菜，调用接口。）

    谁负责实现这个接口。（后台的厨师负责把西红柿鸡蛋做好！是接口的实现者。）

    这个接口有什么用呢？
    这个饭馆的“菜单”，让“顾客”和“后厨”解耦合了。
    顾客不用找后厨，后厨不用找顾客。他们之间完全依靠这个抽象的菜单沟通。

    总结一句话：三个字“解耦合”
    面向接口编程，可以降低程序的耦合度，提高程序的扩展力。符合OCP开发原则。
    接口的使用离不开多态机制。（接口+多态才可以达到降低耦合度。）

    接口可以解耦合，解开的是谁和谁的耦合！！！
    任何一个接口都有调用者和实现者。
    接口可以将调用者和实现者解耦合。
    调用者面向接口调用。
    实现者面向接口编写实现。

    以后进行大项目的开发，一般都是将项目分离成一个模块一个模块的，
    模块和模块之间采用接口衔接。降低耦合度。

    1.4、类型和类型之间的关系：

    is a（继承）、has a（关联）、like a（实现）

    is a：
    Cat is a Animal（猫是一个动物）
    凡是能够满足is a的表示“继承关系”
    A extends B

    has a：
    I has a Pen（我有一支笔）
    凡是能够满足has a关系的表示“关联关系”
    关联关系通常以“属性”的形式存在。
    A{
    B b;
    }

    like a:
    Cooker like a FoodMenu（厨师像一个菜单一样）
    凡是能够满足like a关系的表示“实现关系”
    实现关系通常是：类实现接口。
    A implements B

    1.5、抽象类和接口有什么区别？
    在这里我们只说一下抽象类和接口在语法上的区别。
    至于以后抽象类和接口应该怎么进行选择，通过后面的项目去体会/学习。

    抽象类是半抽象的。
    接口是完全抽象的。

    抽象类中有构造方法。
    接口中没有构造方法。

    接口和接口之间支持多继承。
    类和类之间只能单继承。

    一个类可以同时实现多个接口。
    一个抽象类只能继承一个类（单继承）。

    接口中只允许出现常量和抽象方法。

    这里先透露一个信息：
    以后接口使用的比抽象类多。一般抽象类使用的还是少。
    接口一般都是对“行为”的抽象。
*/

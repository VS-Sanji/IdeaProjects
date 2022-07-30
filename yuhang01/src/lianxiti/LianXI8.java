package lianxiti;
/*
定义丈夫类Husband和妻子类Wife，丈夫类的属性包括：身份证号，姓名，出生日期，妻子。
妻子类的属性包括：身份证号，姓名，出生日期，丈夫。分别给这两个类提供构造方法（无参数构造方法和有参数构造方法都要提供），
编写测试程序，创建丈夫对象，然后再创建妻子对象，丈夫对象关联妻子对象，妻子对象关联丈夫对象，
要求能够输出这个“丈夫对象”的妻子的名字，或者能够输出这个“妻子对象”的丈夫的名字。
要求能够画出程序执行过程的内存图。并且要求在程序中演示出空指针异常的效果。
 */
public class LianXI8 {
    public static class Husband{
        int idCard;
        String name;
        String birthday;
        Wife w;
        public Husband(){
        }

        public Husband(int idCard,String name,String birthday){
            this.idCard=idCard;
            this.name=name;
            this.birthday=birthday;
        }

    }


    public static class Wife{
        int idCard;
        String name;
        String birthday;
        Husband h;
        public Wife(){
        }

        public Wife(int idCard,String name,String birthday){
            this.idCard=idCard;
            this.name=name;
            this.birthday=birthday;
        }
    }

    public static void main(String[] args){
        Husband zhangsan=new Husband(01,"zhangsan","1998年12月1日");
        Wife lisi=new Wife(02,"lisi","1999年6月20日");
        zhangsan.w=lisi;
        lisi.h=zhangsan;
        System.out.println(zhangsan.name+"的妻子是："+zhangsan.w.name);
        System.out.println(lisi.name+"的丈夫是："+lisi.h.name);
        zhangsan=null; //空指针异常 打印的内容需要访问对象zhangsan中的数据，此时将null赋給变量（引用）使得zhangsan无法指向对象zhangsan，出现空指针异常
        lisi=null;
        System.out.println(zhangsan.name+"的妻子是："+zhangsan.w.name);
        System.out.println(lisi.name+"的丈夫是："+lisi.h.name);


        /*
        zhangsan=null;
        空指针异常 打印的内容需要访问对象zhangsan中的数据，此时将null赋給变量（引用）使得zhangsan无法指向对象zhangsan，出现空指针异常
        或者
        zhangsan.w=null;
        空指针异常 打印的内容需要访问对象zhangsan中zhangsan.w的数据，此时将null赋給变量（引用）使得zhangsan.w无法指向对象lisi，出现空指针异常
         */

    }
}

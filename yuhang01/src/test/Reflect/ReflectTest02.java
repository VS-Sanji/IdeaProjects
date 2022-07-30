package test.Reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 反射Studentx类中的所有Field
 */
public class ReflectTest02 {
    public static void main(String[] args) {
        try {
            //获取整个类
            Class studentClass = Class.forName("test.Studentx");

            //获取完整类名
            String absoluteName = studentClass.getName();
            System.out.println(absoluteName);//test.Studentx  以类路径为起点

            //获取简要类名
            String simpleName = studentClass.getSimpleName();
            System.out.println(simpleName);///Studentx

            //getFields()方法，获取类中所有被public修饰的Field
            Field[] fields = studentClass.getFields();
            System.out.println(fields.length);//1 测试数组中有几个元素
            //取出Field
            Field f = fields[0];
            //取出这个Field的名字
            String fieldName = f.getName();
            System.out.println(fieldName);//no

            //想要获取类中所有的Field，可以调用getDeclaredField()方法
            Field[] fields1 = studentClass.getDeclaredFields();
            System.out.println(fields1.length);//4

            //遍历数组
            for (Field field : fields1){
                //获取属性的修饰符列表 修饰符可能有多个
                //getModifiers这个方法返回的是一个数字，每个数字是修饰符的代号，可以用Modifiers.toString(数字)将其转成对应的修饰符字符串
                int i = field.getModifiers();
                //将 代号数字 转成 字符串
                String mofifierName = Modifier.toString(i);
                System.out.println(mofifierName);

                //获取属性的类型
                Class fieldType = field.getType();
                System.out.println(fieldType.getName());

                //获取属性的名字
                System.out.println(field.getName());
                //public
                //int
                //no
                //private
                //java.lang.String
                //name
                //protected
                //int
                //age
                //              这里是默认，default
                //boolean
                //sex

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
        /**
         * 通过反射机制访问对象属性
         *      给属性赋值：set
         *      获取属性值：get
         */
        //不使用反射机制，访问对象属性
        Studentx studentx = new Studentx();
        //获取值
        int age = studentx.age;
        //修改值
        //三要素：给studentx对象的age属性赋值
        //      1.对象studentx
        //      2.age属性
        //      3.值12
        studentx.age = 12;

        //使用反射机制，访问对象属性（set get）
        try {
            //获取类
            Class studentxClass = Class.forName("test.Studentx");
            //实例化对象
            Object obj = studentxClass.newInstance();

            //获取no属性，根据属性的名称来获取Field
            Field noField = studentxClass.getDeclaredField("no");

            //给obj对象（Studentx对象）的no属性赋值
            /*
            虽然使用了反射机制，但三要素还是缺一不可
                要素1.obj对象
                要素2.no属性
                要素3.值333
                反射机制代码多了点，但是灵活性强了，值得
             */
            noField.set(obj,333);//给obj对象的no属性赋值333，属性自身调用set方法，明确了操作目标是no属性，要求传入参数为  对象，值，以精确定位到对象的该属性，并赋值

            //读取属性得值
            //两要素：获取obj对象no属性的值
            System.out.println(noField.get(obj));//用属性自身去调用get方法，明确了操作目标的是no属性，传入 对象，以明确获取的是哪个对象的no属性的值

            //可以访问私有的属性？
            /**
             * Field nameField = studentxClass.getDeclaredField("name");
             * nameField.set(obj,"zhanhui");
             * System.out.println(nameField.get(obj));//java.lang.IllegalAccessException,以上方式会出现非法访问异常
             */

            Field nameField = studentxClass.getDeclaredField("name");
            //硬要访问私有属性，我们可以打破封装（可能给不法分子机会）
            //这样设置完，在外部也是可以访问private的
            nameField.setAccessible(true);
            nameField.set(obj,"zhanhui");
            System.out.println(nameField.get(obj));//zhanhui



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
class Studentx {

    //Field翻译为字段，其实就是属性/成员
    //4个Field，分别采用不同的访问控制权限修饰符

    public int no;    //public int no 整个这一行是一个Field对象
    private String name;
    protected int age;
    boolean sex;

}
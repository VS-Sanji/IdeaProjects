package test.Array;
/**
 1、数组    引用数据类型   父类是Object   数组中可以存储基本数据类型，也能存储引用数据类型（存的java对象的内存地址），主要看是个什么数组
           数组本身是引用数据类型，创建的数组对象存在堆内存当中，Java中数组一旦创建，其长度不可变
           数组的分类：一维数组、二维数组、三维数组等，（一维数组用的最多，二维数组偶尔使用）
           所有数组都有length属性，java自带的，用来获取数组中元素的个数
           数组中的元素统一，int类只能存int类型数据，person类只能存person类数据
           数组中元素的所占的内存空间其地址是连续的，如：0x1111，0x1112，0x1113，内存地址连续这是数组存储元素的特点，数组实际上是一种简单的数据结构
           数组的内存地址通常都是拿数组中第一个元素的内存地址来表示整个数组对象的内存地址
           数组中每一个元素都有下标，下表从0开始，以1递增，最后一个元素的下标为：length-1    下标非常重要，因为我们对数组中的元素进行“存取“的时候，都需要通过下标来进行
 1.1、数组的优点和缺点，并且要理解为什么。
 第一：空间存储上，内存地址是连续的。
 第二：每个元素占用的空间大小相同。
 第三：知道首元素的内存地址。
 第四：通过下标可以计算出偏移量。
 通过一个数学表达式，就可以快速计算出某个下标位置上元素的内存地址，直接通过内存地址定位，效率非常高。所以数组的检索效率非常高，可以说是最高

 优点：检索效率高。
 缺点：随机增删效率较低，因为为了保持内存地址连续，在随机增删时需要后面的元素统一向前或向后位移
      数组无法存储大数据量，因为很难在内存空间上找到一块连续的内存空间
 注意：数组最后一个元素的增删效率不受影响。

 1.2、一维数组的静态初始化和动态初始化
 静态初始化：
 int[] arr = {1,2,3,4};
 Object[] objs = {new Object(), new Object(), new Object()};  new对象，地址存数组里
 动态初始化：
 int[] arr = new int[4]; // 4个长度，每个元素默认值0
 Object[] objs = new Object[4]; // 4个长度，每个元素默认值null

 1.3、一维数组的遍历
 for(int i = 0; i < arr.length; i++){
 System.out.println(arr[i]);
 }

 1.4、二维数组的静态初始化和动态初始化           二维数组是特殊的一维数组，只不过其中的元素是个一维数组（也是存着一维数组的地址）
 静态初始化：
 int[][] arr = {
 {1,2,34},
 {54,4,34,3},
 {2,34,4,5}
 };

 Object[][] arr = {
 {new Object(),new Object()},
 {new Object(),new Object()},
 {new Object(),new Object(),new Object()}
 };
 动态初始化：
 int[][] arr = new int[3][4];       表示二维数组中存了三个一维数组，且一维数组的长度为4，即一维数组中存4个元素
 Object[][] arr = new Object[4][4]; Object类型数组，里面可以存储Object类型对象，以及Object类型的子类型都可以。又由于Object是所有类的父类，所以所有子类都能存在Object数组中
 Animal[][] arr = new Animal[3][4];
 // Person类型数组，里面可以存储Person类型对象，以及Person类型的子类型都可以。
 Person[][] arr = new Person[2][2];
 ....

 1.5、二维数组的遍历
 for(int i = 0; i < arr.length; i++){ // 外层for循环负责遍历外面的一维数组。
 // 里面这个for循环负责遍历二维数组里面的一维数组。
 for(int j = 0; j < arr[i].length; j++){
 System.out.print(arr[i][j]);
 }
 // 换行。
 System.out.println();
 }

 1.6、main方法上“String[] args”参数的使用（非重点，了解一下，以后一般都是有界面的，用户可以在界面上输入用户名和密码等参数信息。）

 1.7、数组的拷贝：System.arraycopy()方法的使用
 数组有一个特点：长度一旦确定，不可变。
 所以数组长度不够的时候，需要扩容，扩容的机制是：新建一个大数组，
 将小数组中的数据拷贝到大数组，然后小数组对象被垃圾回收。

 1.8、对数组中存储引用数据类型的情况，要会画它的内存结构图。
 */
public class ArrayTest01 {
    public static void main(String[] args) {
        //一维数组，静态初始化
        int[] array={1,23,4,4,4,5,5,5};              //长度为8，初始值为手动所赋
        String[] str={"pubg","沾灰nmsl","java"};   //长度为3，初始值为手动所赋

        //一维数组，动态初始化
        int[] array1=new int[4];   //中括号中的数字4表示这个一维数组有4个元素，长度为4，默认值为0
        String[] str1=new String[4];   //中括号中的数字4表示这个一维数组有4个元素，长度为4，默认值为null

        //访问数组长度
        System.out.println("数组中的元素个数为："+array.length);

    //访问数组中某个元素
        System.out.println("数组中第一个元素为："+array[0]+"数组中第二个元素为："+array[1]+"数组中最后一个元素为："+array[array.length-1]);  //最后一个就用array.length-1来访问

        //修改数组中某个元素
        array[2]=234;      //表示将数组中第三个元素改为234
        array[array.length-1]=6;   //表示将数组中最后一个元素改为6

        //一维数组的遍历，从第一个开始
        for (int i = 0; i < array.length ; i++) {  //判断条件为i < array.length 即可
            System.out.println(array[i]);         //打印第i个元素
        }

        //一维数组的遍历，从最后一个开始
        for (int i = array.length-1; i > 0 ; i--) {  //倒着打印输出
            System.out.println(array[i]);
        }

        //下标越界会出现java.lang.ArrayIndexOutOfBoundsException异常，比较著名的异常，尽量避免
        System.out.println(array[array.length]);    //下标为array.length已经越界了

        //动态初始化
        int[] a=new int[4];//分两步，先创建数组，再将引用赋给方法的形参
        printArray(a);
        printArray(new int[4]);//直接一步到位，在方法的形参位置new数组对象，效果相同，也将所创建的数组对象引用传给形参

        //静态初始化
        int[] a1={2,34,45,5};
        printArray(a1);              //将引用传进去可行
       //printArray({2,34,45,5});  静态初始化时，java不支持这种语法，可以改成下列形式
        printArray(new int[]{2,34,45,5}); //这样是可行的，要注意
    }
    public static void printArray(int[] array){              //传一个数组引用进来，将该数组的各个元素打印
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}

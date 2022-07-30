package lianxiti;
//1、这个栈可以存储java中的任何引用类型的数据。
//		2、在栈中提供push方法模拟压栈。（栈满了，要有提示信息。）
//		3、在栈中提供pop方法模拟弹栈。（栈空了，也有有提示信息。）
//		4、编写测试程序，new栈对象，调用push pop方法来模拟压栈弹栈的动作。
//
//		public class MyStack{ // 栈类
//
//			// 提供一个数组来存储栈中的元素
//			Object[] elements;
//
//			// 栈帧（永远指向栈顶部的元素）
//			// 每加1个元素，栈帧+1
//			// 每减1个元素，栈帧-1
//			int index;
//
//			// 构造方法
//			// 构造方法是不是应该给一维数组一个初始化容量。
//
//			// push方法（push方法压栈）
//			// 压栈表示栈中多一个元素。
//			// 但是栈如果已满，压栈失败。
//			// 这个方法的参数以及返回值类型自己定义。
//
//			// pop方法（pop方法弹栈）
//			// 弹栈表示栈中少一个元素。
//			// 但是栈如果已空，弹栈失败。
//			// 这个方法的参数以及返回值类型自己定义。
//
//		}
//
//		main(){
//			测试...
//		}
public class LianXi30 {
    public static void main(String[] args) {
        MyStack ms = new MyStack(3);
        ms.push(new String());
        ms.push(new String());
        ms.push(new String());
        ms.push(new String());
        ms.pop();
        ms.pop();
        ms.pop();
        ms.pop();
        ms.pop();
        //压栈成功
        //压栈成功
        //压栈成功
        //栈已满，压栈失败
        //弹栈成功
        //弹栈成功
        //弹栈成功
        //栈已空，弹栈失败
        //栈已空，弹栈失败
    }
}

class MyStack{
    //栈帧
    private int index;
    //数组长度
    private int length;
    //Object数组
    private Object[] elements ;
    /**函数（方法）和变量的初始化先于构造函数（构造方法）的运行
     * 就是讲new对象的时候，类中的所有属性，实例方法，静态方法先初始化，再来执行构造方法当中的指令
     * 对于属性private Object[] elements = new Object[length];
     *  对于有参构造方法
     *  public MyStack(int length) {
     *         this.length = length;
     *     }
     * 执行的时候
     * MyStack ms = new MyStack(3);
     * 一、执行new MyStack();   创建对象  这时先一步把类中的属性index、length、 Object[] elements = new Object[length]初始化，则index=0  length=0  Object[] elements = new Object[0];
     * 二、再执行赋值length=3，这时候创建的数组对象长度已经是0了，对于属性private Object[] elements = new Object[length];语句，已经完成了数组对象的初始化，后赋值的length不起作用，只是将length从0改成了3
     *
     * 要想构造方法中传入的length在创建数组的时候起作用，可以改为以下写法
     * 定义属性时不写= new Object[length];  在构造方法的方法体当中写
     * private Object[] elements ;
     * public MyStack(int length) {
     *         this.length = length;
     *         this.elements = new Object[this.length];
     *     }
     * 这样执行流程就是
     * 一、执行new MyStack();   创建对象  这时先一步把类中的属性index、length、 Object[] elements = new Object[length]初始化，则index=0  length=0  Object[] elements =null
     * 二、再执行赋值length=3，随后执行this.elements = new Object[this.length];语句，创建数组对象，长度为传进来的length，符合需求
     */


    //无参构造方法
    public MyStack() {
        //this.elements = new Object[length];
    }
    //有参构造方法
    public MyStack(int length) {
        this.length = length;
        this.elements = new Object[this.length];
    }
    //压栈方法
    public void push(Object element){
        if (this.index == this.length){
            System.out.println("栈已满，压栈失败");

        }
        if (this.index < this.length) {
            elements[index] = element;
            index++;
            System.out.println("压栈成功");
        }
    }
    //弹栈方法
    public void pop(){
        if (this.index >0) {
            elements[index-1] = null;
            index--;
            System.out.println("弹栈成功");

        }else {
            System.out.println("栈已空，弹栈失败");
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Object[] getElements() {
        return elements;
    }

    public void setElements(Object[] elements) {
        this.elements = elements;
    }



}
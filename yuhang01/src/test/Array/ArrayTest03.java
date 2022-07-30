package test.Array;
/**
 * 数组扩容
 * 在java开发中，数组长度一旦确定不可变，那么数组满了怎么办？
 * 数组满了，需要扩容
 * java中对数组的扩容是：先建一个大容量的数组，然后将小容量数组中的数据一个一个的拷贝到大数组中
 * 结论：数组扩容效率较低。因为涉及到拷贝的问题，所以在以后的开发中要注意：尽可能少的进行数组的拷贝，
 *      可以在创建数组对象的时候预估一下多长合适，最好预估准确，这样可以减少扩容次数，提高效率
 * 数组在扩容时调用的时arrayCopy方法，有五个参数，1.拷贝源  2.源的起点下标  3.拷贝目标  4.目标的起始下标  5.拷贝长度
 */
public class ArrayTest03 {
    public static void main(String[] args) {
        //拷贝源，src 从这个数组拷贝数据
        int[] src = {1,3,4,4,5};

        //拷贝目标，dest 拷贝到这个数组上
        int[] dest = new int[21];   //初始化长度为21的数组，元素默认值0

        //调用JDK System类中的arrayCopy方法，来完成数组拷贝
        System.arraycopy(src,3,dest,13,2);//表示从源数组中的第4个元素开始拷贝两个元素，拷贝到目标数组dest中第14个元素，15个元素位置上

        for (int i = 0; i < dest.length; i++) {  //遍历
            System.out.println(dest[i]);
        }
    }
}

package test.SuanFa;

/**
 *  查找数组中的元素我们可以遍历数组中的所有元素，这种方式称为线性查找。
 *  线性查找适合与小型数组，大型数组效率太低。
 *  如果一个数组已经排好序，那么我们可以采用效率比较高的二分查找或叫折半查找算法。
 */
public class BinarySearchTest {
    public static void main(String[] args) {
        int[] data = {11,12,13,14,15,16,17,18,19,20};
        int index = binarySearch(data, 18);
        System.out.println(index);
    }
    //采用折半法查询，必须建立在排序的基础上
    private static int binarySearch(int[] data, int value) {
        //开始下标
        int beginPos = 0;
        //结束下标
        int endPos = data.length - 1;
        while (beginPos <= endPos) {
            int midPos = (beginPos + endPos) / 2;
            if (value == data[midPos]) {
                return midPos;
            }else if (value > data[midPos]) {
                beginPos = midPos + 1;
            }else if (value < data[midPos]) {
                endPos = midPos - 1;
            }
        }
        return -1;
    }

}

package lianxiti;
//打印2到10000的所有素数，每行显示8个素数。
public class LianXi1 {
    public static void main(String[] args){
        int count=0;
        for(int i=2;i<=1000;i++){
            if (i == 2) {
                System.out.print(i+" ");
                count++;
            }else{
                for(int j=2;j<i;j++){

                    if (i % j == 0) {  //如果除的尽就直接结束循环
                        break;
                    } else if (j < i - 1) {  //除不尽并且小于i-1，则继续下一个除数
                        continue;
                    }else{                   //最后一个除数也除不尽 则输出
                        System.out.print(i+" ");
                        count++;
                    }
                }
            }
            if (count == 8) {
                System.out.println();
                count=0;
            }
        }
    }
    /**
     *     public static void main(String[] args) {
     *         int count = 0;
     *         for(int i=2;i<=10000;i++){
     *             boolean isPrimeNum = true;
     *             for(int j=2;j<i;j++){
     *                 if(i % j == 0){
     *                     isPrimeNum = false;
     *                     break;
     *                 }
     *             }
     *             if(isPrimeNum){
     *                 System.out.print(i + " ");
     *                 count++;
     *                 if(count == 8){
     *                     System.out.println();
     *                     count = 0;
     *                 }
     *             }
     *         }
     *     }
     */
}

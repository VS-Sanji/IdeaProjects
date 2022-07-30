package cn.itbaizhan;

public class TestSwitch01 {
    public static void main(String[] args) {
        //grade表示大学年级
        int grade=1;

        if (grade==1){
            System.out.println("大学一年级，可以玩耍！");
        }else if (grade==2){
            System.out.println("大学二年级，该读书了！");
        }else if (grade==3){
            System.out.println("大学三年级，准备找工作");
        }else{
            System.out.println("大学四年级，毕业啦！");
        }

        switch (grade){
            case 1:
                System.out.println("大学一年级");
                break;
            case 2:
                System.out.println("大学二年级");
                break;
            case 3:
                System.out.println("大学三年级");
                break;
            default:
                System.out.println("大学四年级，毕业了！！！");
        }

        System.out.println("##########################################################");
        int month=5;//5表示五月

        if (month==1||month==2||month==3){
            System.out.println("春季");
        }else if (month==4||month==5||month==6){
            System.out.println("夏季");
        }else if (month==7||month==8||month==9){
            System.out.println("秋季");
        }else{
            System.out.println("冬季");
        }

        System.out.println("使用switch语句改造上面代码");

        switch (month){
            case 1:
            case 2:
            case 3:
                System.out.println("春季");
                break;
            case 4:
            case 5:
            case 6:
                System.out.println("夏季");
                break;
            case 7:
            case 8:
            case 9:
                System.out.println("秋季");
                break;
            default:
                System.out.println("冬季");
        }
    }
}

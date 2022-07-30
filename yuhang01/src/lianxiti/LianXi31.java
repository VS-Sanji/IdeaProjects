package lianxiti;

//（java软件工程师人生路上第一个小型项目。锻炼一下面向对象。）
//	为某个酒店编写程序：酒店管理系统，模拟订房、退房、打印所有房间状态等功能。
//	1、该系统的用户是：酒店前台。
//	2、酒店使用一个二维数组来模拟。“Room[][] rooms;”
//	3、酒店中的每一个房间应该是一个java对象：Room
//	4、每一个房间Room应该有：房间编号、房间类型、房间是否空闲.
//	5、系统应该对外提供的功能：
//		可以预定房间：用户输入房间编号，订房。
//		可以退房：用户输入房间编号，退房。
//		可以查看所有房间的状态：用户输入某个指令应该可以查看所有房间状态。

import java.util.Scanner;

/**
 *分析：对于这个酒店管理系统，首先要有Hotel对象，所以需要Hotel类，按此类来创建酒店对象
 * Hotel对象又包括 酒店名字  以及  一个房间二维数组（模拟现实世界的酒店有楼层，每层有房间），该数组用来存房间对象，因此又需要创建Room类，来创建房间对象
 * Room对象包含的属性为，房间编号、房间类型、房间状态（是否空闲）
 */

public class LianXi31 {
}


class HotelManagerSystem{
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        System.out.println("欢迎使用酒店管理系统");
        System.out.println("请输入功能编号：[1]查询房间列表  [2]订房  [3]退房");
        while (true) {             //加一个死循环，达到反复使用的目的
            //用户输入
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            if (num == 1) {
                hotel.print();
            }
            if (num == 2) {
                System.out.println("输入房间编号");
                int roomNum = scanner.nextInt();
                hotel.order(roomNum);
                System.out.println("订房成功");
            }
            if (num == 3) {
                System.out.println("输入房间编号");
                int roomNum = scanner.nextInt();
                hotel.equals(roomNum);
                System.out.println("退房成功");
            }

        }
    }

}





class Hotel{
    //酒店名字
    private String name;
    //二维数组存储酒店房间对象          酒店有二维数组，模拟酒店大厦所有房间
    private Room[][] rooms;

    //无参构造方法
    //创建酒店对象时所有的房间就会有，所以将数组定义出来
    public Hotel() {
        //四行十列，模拟四层楼，每层十个房间
        //动态初始化，所有房间（存储空间）还是null，需要创建房间对象，存进去
        this.rooms = new Room[4][10];

        //采用二维数组遍历，放进去
        //i表示数组的下标（这里是 0 1 2） 那么 i+1 可以表示楼层（1 2 3）
        //房间编号
        /*
        1楼：101 102 103 104...
        2楼：201 202 203 204...
        3楼：301 302 303 304...
        ...
         */
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                //调用有参构造 crtl + p 查看形参列表      (i+1)*100+i+1 表示房间号 通过数学计算来实现
                //this.rooms[i][j] = new Room((i+1)*100+j+1,"标准间",true);  表示将创建的房间对象存在二维数组的第i+1行 第j+1列 上，模拟i+1层，j+1房为某一类型及状态的房间
                //其中(i+1)*100 将楼层确定下来， 再加上j+1 就可以确定是那个房间
                    //一层
                if (i == 0) {
                    this.rooms[i][j] = new Room((i+1)*100+j+1,"标准间",true);           //把二维数组的第一个一维数组的全部空间都存入roon对象，模拟一楼所有的房间
                    //二层
                } else if (i == 1) {
                    this.rooms[i][j] = new Room((i+1)*100+j+1,"单人间",true);           //把二维数组的第二个一维数组的全部空间都存入roon对象，模拟二楼所有的房间
                    //三层
                } else if (i == 2) {
                    this.rooms[i][j] = new Room((i+1)*100+j+1,"双人间",true);           //把二维数组的第三个一维数组的全部空间都存入roon对象，模拟三楼所有的房间
                    //四层
                } else if (i == 3) {
                    this.rooms[i][j] = new Room((i+1)*100+j+1,"豪华套间",true);          //把二维数组的第四个一维数组的全部空间都存入roon对象，模拟四楼所有的房间
                }

            }
        }

    }

    /**
     * 打印所有房间状态 换成代码就是遍历二维数组，调用toString方法将所有房间对象转换成字符串输出
     */
    //打印所有房间信息的方法
    public void print(){
        for (int i = 0; i < rooms.length; i++) {    //某一层
            for (int j = 0; j < rooms[i].length; j++) {
                //将遍历的所有房间赋给Room类型引用
                Room room = rooms[i][j];       //Room room = rooms[i][j];  表示将二维数组中的第i+1个一维数组中的第j+1个位置上的room对象的内存地址赋给Room room引用，通过引用调用该room对象的toString方法，输出房间信息
                System.out.print(room);   //等同System.out.println(room.toString()); 自动调用toString方法
            }
            //打完一层换行
            System.out.println();
        }
    }

    //预定房间  输入房间编号，将其状态改成占用
    //空闲的房间可以预定
    public void order(int roomNum){
        //用传进来的房间编号，计算出其在数组当中的存储位置，并将该位置的内存地址赋给Room引用，通过该引用来找到该房间
        Room room = rooms[roomNum / 100 -1][roomNum % 100 -1];
        if (room.isCondition() == true) {
            room.setCondition(false);
        }else {
            System.out.println("该房间已被预定，请选择其他房间");
        }
    }

    //退房  输入房间编号，将其状态改成空闲
    public void exit(int roomNum){
        //用传进来的房间编号，计算出其在数组当中的存储位置，并将该位置的内存地址赋给Room引用，通过该引用来找到该房间
        Room room = rooms[roomNum / 100 -1][roomNum % 100 -1];
        room.setCondition(true);

    }
    //有参构造方法
    public Hotel(String name, Room[][] rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    //setter and getter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room[][] getRooms() {
        return rooms;
    }

    public void setRooms(Room[][] rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "酒店名称是：" + this.name + "该酒店有" + this.rooms.length + "层";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Hotel)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Hotel hotel = (Hotel)obj;
        return this.name.equals(hotel.name);


    }
}






class Room{
    //房间编号
    /*
    1楼：101 102 103 104...
    2楼：201 202 203 204...
    3楼：301 302 303 304...
    ...
     */
    private int roomNum;

    //房间类型
    /*标准间  单人间  双人间  豪华套间*/
    private String type;

    //房间状态（是否空闲）
    /*
    true表示空闲  不能被预定
    false表示占用  可以被预定
     */
    private boolean condition;

    //无参构造方法
    public Room() {
    }

    //有参构造方法
    public Room(int roomNum, String type, boolean condition) {
        this.roomNum = roomNum;
        this.type = type;
        this.condition = condition;
    }

    //setter and getter 方法
    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //IDEA工具生成的boolean类型的get方法是isXxx  可以修改成getXxx
    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    @Override
    /**
     * equals方法用来对比两个对象是否是同一个。要比对的是 信息   而不是  内存地址，所以需要将继承自Object的equals方法进行重写
     */
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Room)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Room room = (Room)obj;
        return this.roomNum == room.roomNum && this.type.equals(room.type) && this.condition == room.condition;
    }

    @Override
    /**
     * toString方法的目的是将一个对象转化成字符串形式，同样需要重写，不然调用的就是继承自Object的toString方法，输出的就是  类名@内存地址  了，这不是我们想要的，所以需要重写
     *这里对于Room对象，我们可以将其转换成  房间编号 + 房间类型 + 状态（是否空闲）  来表示这个房间对象（简洁明了），不用内存地址来表示这个对象
     *
     * println（引用）  会自动调用  引用的toString方法
     * 查看一个类的方法快捷键  crtl + F12
     */
    public String toString() {
        //return “101 单人间 空闲”  这样就写死了，不管对于哪个对象，输出都一样，而不是随着房间对象的改变而随着改变，不满足需求，可以写成动态的形式

        //动态    （把一个变量塞到一个字符串当中：加一个双引号，双引号中加两个+号，两个加号中间加变量名）
        //"字" + this.name + "符" + this.id + "串"

        return "[" + this.roomNum + " " + this.type + " " + (this.condition ? "空闲" : "占用") + "]";

    }
}
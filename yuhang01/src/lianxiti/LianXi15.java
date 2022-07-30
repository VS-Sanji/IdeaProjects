package lianxiti;
//某汽车租赁公司有多种汽车可以出租，计算汽车租金
//
//Vehicle是所有车的父类，属性：品牌、车牌号，有返回总租金的方法：public double getSumRent(int days){}
//
//小轿车类Car是Vehicle的子类，属性：车型（两厢、三厢、越野），两厢每天300，三厢每天350，越野每天500。
//
//多座汽车类Bus是Vehicle的子类，属性：座位数，座位数<=16的每天400，座位数>16的每天600。
//
//编写测试类，根据用户选择不同的汽车，计算总租金。
public class LianXi15 {
    public static void main(String[] args) {
        Car car=new Car();
        car.setBrand("思域");
        car.setCarId("赣E 99999");
        car.setCarType("越野");
        System.out.println("汽车的品牌是:"+car.getBrand()+"\t"+"车牌号是："+car.getCarId()+"\t"+"类型是："+car.getCarType()+"\t"+"租金是："+ car.getSunRent(5));

        Bus bus=new Bus();
        bus.setBrand("宇通");
        bus.setCarId("沪A 88888");
        bus.setSeatNum(25);
        System.out.println("汽车的品牌是:"+bus.getBrand()+"\t"+"车牌号是："+bus.getCarId()+"\t"+"类型是："+bus.getSeatNum()+"\t"+"租金是："+ bus.getSunRent(5));





    }
}


class Vehicle{
    private String brand;

    private String carId;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public double getSunRent(int days){
        return 0.0;
    }
}


class Car extends Vehicle{
    private String carType;

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public double getSunRent(int days) {
        if (carType == "两厢") {
            return 300*days;
        }
        if (carType == "三厢") {
            return 350*days;
        }else {
            return 500*days;
        }
    }
}

class Bus extends Vehicle{
    private int seatNum;

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    @Override
    public double getSunRent(int days) {
        if (seatNum <= 16) {
            return 400*days;
        }else {
            return 600*days;
        }
    }
}
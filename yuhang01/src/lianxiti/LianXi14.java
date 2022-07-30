package lianxiti;
//计算不同类型的员工薪资
//
//定义员工类Employee，员工包含姓名name、出生月份birthMonth两个属性，员工有获取指定月份工资的方法（money(int month)），如果该月员工生日，公司补助250元。
//
//定义有固定工资的员工类SalariedEmployee，有月薪monthlySalary属性。
//
//定义小时工类HourlyEmployee，包含工作小时数hours和每小时的工资hourlySalary属性，如果每月工作超过160小时，超过的部分按1.5倍工资发放。
//
//定义销售人员类SalesEmployee，包含月销售额sales和提成比例comm属性。
public class LianXi14 {
    public static void main(String[] args) {
        SalariedEmployee employee1=new SalariedEmployee();
        employee1.setName("zhanhui");
        employee1.setBirthMonth(6);
        employee1.setMonthlySalary(2323.0);
        System.out.println(employee1.getName()+"6月份的工资为："+employee1.money(6));

        HourlyEmployee employee2=new HourlyEmployee();
        employee2.setName("songyuhang");
        employee2.setBirthMonth(12);
        employee2.setHoursSalary(20.0);
        System.out.println(employee2.getName()+"7月份的工资为："+employee2.money(7));

        SalesEmployee employee3=new SalesEmployee();
        employee3.setName("nimasile");
        employee3.setComm(1.5);
        employee3.setSales(2000.0);
        employee3.setBirthMonth(5);
        System.out.println(employee3.getName()+"5月份的工资为："+employee2.money(5));

    }
}


class Employee{
    private String name;
    private int birthMonth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public double money(int month){
        return 0.0;                  /**创建对象时完成属性的初始化，实例方法中的需要手动初始化*/
    }

}
class SalariedEmployee extends Employee{
    private double monthlySalary;    /**创建对象时完成属性的初始化，成员变量默认初始化*/

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }


    @Override
    public double money(int month) {
        if (this.getBirthMonth() == month) {
            return monthlySalary+250;
        }
        return monthlySalary;
    }
}

class HourlyEmployee extends Employee{
    private int hours;
    private double hoursSalary;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public double getHoursSalary() {
        return hoursSalary;
    }

    public void setHoursSalary(double hoursSalary) {
        this.hoursSalary = hoursSalary;
    }


    @Override
    public double money(int month) {           /**计算拿多少钱的方法，运行完成后把数值返回   如：employee1.money()   结果是一个值*/
        double money=0.0;
        if (hours <= 160) {
            return money=160*hoursSalary;      /**返回值只返回一个*/
        }
        if (hours > 160) {
            return money=160*hoursSalary+1.5*(hours-160)*hoursSalary;
        }
        if (this.getBirthMonth() == month) {
            return money+250;
        }
        return money;
    }

}


class SalesEmployee extends Employee{
    private double sales;
    private double comm;

    public double getComm() {
        return comm;
    }

    public void setComm(double comm) {
        this.comm = comm;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    @Override
    public double money(int month) {
        if (this.getBirthMonth() == month) {
            return sales*comm+250;
        }
        return sales*comm;
    }
}
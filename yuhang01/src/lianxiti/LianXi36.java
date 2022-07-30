package lianxiti;

import java.util.Objects;

/**
 * java多线程模拟实现12306售票
 * 业务描述
 * 	假设有200张票，用4个线程去订票，不能有两个或者以上的线程订到同一个票（原因就不说了），当最后一张票卖掉的时候结束，再订就抛异常提示出票卖完了。
 * 业务分析,要实现以上功能，
 *
 * 	1、需要创建一个车票类，初始化票，卖票的接口saleTicket()
 *
 * 	2、自定义异常的一个类。
 *
 * 	3、创建卖票线程类，在run方法中卖车票。
 *
 * 	4、初始化车票的线程,负责初始化车票,也就是初始化Ticket类中的数组。
 *
 * 	5、创建主方法进行测试。
 */
public class LianXi36 {

}

class Ticket {
    private int no;

    private double price;

    public Ticket() {
    }

    public Ticket(int no, double price) {
        this.no = no;
        this.price = price;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return no == ticket.no && Double.compare(ticket.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, price);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "no=" + no +
                ", price=" + price +
                '}';
    }

}

class SaleTicketException extends RuntimeException {
    public SaleTicketException() {
    }

    public SaleTicketException(String message) {
        super(message);
    }
}

class SaleTicketThread implements Runnable {

    @Override
    public void run() {

    }
}


package lianxiti;

/**
 * 1、使用生产者和消费者模式实现，交替输出：
 * 	假设只有两个线程，输出以下结果：
 * 		t1-->1
 * 		t2-->2
 * 		t1-->3
 * 		t2-->4
 * 		t1-->5
 * 		t2-->6
 * 		....
 *
 * 		要求：必须交替，并且t1线程负责输出奇数。t2线程负责输出偶数。
 * 		两个线程共享一个数字，每个线程执行时都要对这个数字进行：++
 *
 * 		public class Num {
 * 			int i;
 *                }
 *
 * 		synchronized(num){
 * 			if(num是奇数){
 * 				num.wait();
 *            }
 * 			// 输出偶数
 * 			// notifyAll()
 *        }
 *
 * 		synchronized(num){
 * 			if(num是偶数){
 * 				num.wait();
 *            }
 * 			// 输出奇数
 * 			// notifyAll();
 *        }
 */
public class LianXi37 {
    public static void main(String[] args) {
        Num num = new Num(1);
        Thread t1 = new Thread(new Thread1(num));
        Thread t2 = new Thread(new Thread2(num));
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();

    }
}

class Num {
    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Num() {
    }

    public Num(int i) {
        this.i = i;
    }

}

class Thread1 implements Runnable {
    private Num num1;

    public Thread1(Num num1) {
        this.num1 = num1;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (num1) {
                if (num1.getI() % 2 == 0) {
                    try {
                        num1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "--->" + num1.getI());
                int i = num1.getI() + 1;
                if (i > 50){
                    break;
                }
                num1.setI(i);
                num1.notify();
            }
        }

    }
}

class Thread2 implements Runnable {
    private Num num2;

    public Thread2(Num num2) {
        this.num2 = num2;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (num2) {
                if (num2.getI() % 2 != 0) {
                    try {
                        num2.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "--->" + num2.getI());
                int i = num2.getI() + 1;
                if (i > 50) {
                    break;
                }
                num2.setI(i);
                num2.notify();
            }
        }
    }
}
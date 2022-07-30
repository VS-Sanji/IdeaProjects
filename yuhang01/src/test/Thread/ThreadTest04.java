package test.Thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 关于Object类中的wait和notify方法。（生产者和消费者模式！）
 *
 * 	第一：wait和notify方法不是线程对象的方法，是java中任何一个java对象都有的方法，因为这两个方式是Object类中自带的。
 * 		 wait方法和notify方法不是通过线程对象调用，不是这样的：t.wait()，也不是这样的：t.notify()..不对。
 * 		 它们是由java对象本身来调用的
 *
 * 	第二：wait()方法作用？
 * 		Object o = new Object();
 * 		o.wait();
 * 		表示：
 * 			让正在o对象上活动的线程进入等待状态，无期限等待，直到被唤醒为止。o.wait();方法的调用，会让“当前线程（正在o对象上活动的线程）”进入等待状态。
 * 		自己的理解：线程对java对象进行操作，java对象也应当有自主权，有权利让线程们等待，不要着急（别那么卑微，不是任由线程们摆布的）
 * 	第三：notify()方法作用？
 * 		Object o = new Object();
 * 		o.notify();
 * 		表示：
 * 			唤醒正在o对象上等待的线程。
 * 		还有一个notifyAll()方法：
 * 			这个方法是唤醒o对象上处于等待的所有线程。
 * 		自己的理解：线程对java对象进行操作，java对象也应当有自主权，有权利让线程们等待，同时呢，也有必要通知线程们重新对自身进行操作
 */
public class ThreadTest04 {
    public static void main(String[] args) {
        /**
         * 生产者模式 和 消费者模式 是为了专门解决某些特定需求的
         *      比如说：一个线程负责生产，一个线程负责消费，最终要达到生产和消费的均衡。生产满了就不能再继续生产了，必须让消费线程进行消费；消费完了就不能再继续消费了，必须让生产线程进行生产；
         * 生产和消费的对象存放在同一个仓库中，由多线程共享，且都需要修改数据，所以需要考虑线程安全问题；仓库对象最终调用wait 和 notify方法，wait 和 notify方法都是建立在 线程同步的基础之上
         * wait方法作用：o.wait()让正在o对象上活动的线程进入等待状态，并且释放该线程之前占有的o对象的对象锁
         * notify方法作用：o.notify()让正在o对象上等待的线程苏醒，只是通知，不会让当前占有o对象对象锁的线程释放掉对象锁
         *
         * 模拟需求：仓库采用List集合，假设List集合只能存一个元素，有一个元素就表示仓库满了，如果List集合中元素个数是0，就表示仓库空了，保证仓库最多只能存储一个元素
         * 必须做到：生产一个消费一个
         */
        //创建仓库对象
        List list = new ArrayList();
        //创建生产线程对象
        Thread t1 = new Producer(list);
        //创建消费线程对象
        Thread t2 = new Consumer(list);
        //改名
        t1.setName("生产者线程");
        t2.setName("消费者线程");
        //启动线程
        t1.start();
        t2.start();



    }
}

//生产者线程
class Producer extends Thread {
    //共享仓库
    private List list;

    public Producer(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        //用死循环来让内部代码无限重复，在集合为空就保证生产能补上
        while (true) {
            //生产和消费过程在同一个时间点，只能进行一个，不然在生产线程执行到准备添加元素时（还没添加），这时候消费线程进来了，那么此时元素是0，再消费就会出现线程安全问题，所以要让他们线程同步
            synchronized (list){
                //进来先判断，在仓库为空的时候就生产，需要生产再生产，不要就等待
                if (list.size() > 0) {//集合中元素个数大于0，说明已经有一个元素了，集合已满
                    //集合满了就由它自身调用 wait方法，让当前线程（生产者线程）进入等待状态，并且释放当前list集合对象的对象锁
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //代码能执行到这里就说明集合是空，需要生产,填补到集合中
                Object obj = new Object();
                list.add(obj);
                System.out.println("已经重新补货，可以继续消费" + " " + obj);
                //唤醒消费者消费
                list.notify();
                /**这里调用 notifyAll 也是可以的，因为就算是唤醒了生产者线程，让它们抢夺对象锁，就算生产者线程又抢到了，还会重新进入判断的环节，因为已经添加了元素，所以其还会再次进入等待，一直重复直到消费者线程拿到对象锁进行消费*/
            }
        }
    }
}

//消费者线程
class Consumer extends Thread {
    //共享仓库
    private List list;

    public Consumer(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            //生产和消费过程在同一个时间点，只能进行一个，不然在消费线程执行到准备消费元素时（还没消费），这时候生产线程进来了，那么此时元素是1，再生产就会出现线程安全问题，所以要让他们线程同步
            synchronized (list){
                //进来先判断，在仓库为满的时候就消费，需要消费再消费，不要就等待
                if (list.size() == 0) {//集合中元素个数等于0，说明没有元素了，集合已空
                    //集合空了就由它自身调用 wait方法，让当前线程（消费者线程）进入等待状态，并且释放当前list集合对象的对象锁
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //代码能执行到这里就说明集合是满，需要消费
                list.remove(0);
                System.out.println("已经消费，可以继续生产");
                //唤醒消费者消费
                list.notify();
                /**这里调用 notifyAll 也是可以的，因为就算是唤醒了消费者线程，让它们抢夺对象锁，就算消费者线程又抢到了，还会重新进入判断的环节，因为已经添加了元素，所以其还会再次进入等待，一直重复直到生产者线程拿到对象锁进行消费*/
            }
        }
    }
}
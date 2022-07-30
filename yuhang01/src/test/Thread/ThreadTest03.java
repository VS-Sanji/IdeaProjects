package test.Thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 7、线程这块还有那些内容呢？列举一下
 * 	7.1、守护线程
 * 	7.2、定时器
 * 	7.3、实现线程的第三种方式：FutureTask方式，实现Callable接口。（JDK8新特性。）
 * 	7.4、关于Object类中的wait和notify方法。（生产者和消费者模式！）
 *
 * 1、线程这块还有那些内容呢？列举一下
 * 	1.1、守护线程
 * 		java语言中线程分为两大类：
 * 			一类是：用户线程
 * 			一类是：守护线程（后台线程）其中具有代表性的就是：垃圾回收线程（守护线程）。
 * 		守护线程的特点：
 * 			一般守护线程是一个死循环，所有的用户线程只要结束，守护线程自动结束。
 * 		注意：主线程main方法是一个用户线程。
 *
 * 		守护线程用在什么地方呢？
 * 			假设每天00:00的时候系统数据自动备份。
 * 			这个需要使用到定时器，并且我们可以将定时器设置为守护线程。一直在那里看着，每到00:00的时候就备份一次。所有的用户线程如果结束了，守护线程自动退出，没有必要进行数据备份了。
 *
 * 	1.2、定时器
 * 		定时器的作用：
 * 			间隔特定的时间，执行特定的程序。
 * 			    每周要进行银行账户的总账操作。每天要进行数据的备份操作。
 * 			    在实际的开发中，每隔多久执行一段特定的程序，这种需求是很常见的。那么在java中其实可以采用多种方式实现：
 * 				    1.可以使用sleep方法，睡眠，设置睡眠时间，每到这个时间点醒来，执行任务。这种方式是最原始的定时器。（比较low）
 * 				    2.在java的类库中已经写好了一个定时器：java.util.Timer，可以直接拿来用。不过，这种方式在目前的开发中也很少用，因为现在有很多高级框架都是支持定时任务的。
 * 				    3.在实际的开发中，目前使用较多的是Spring框架中提供的SpringTask框架，这个框架只要进行简单的配置，就可以完成定时器的任务。
 *
 *
 * 	1.3、实现线程的第三种方式：实现Callable接口。（JDK8新特性。）
 * 		这种方式实现的线程可以获取线程的返回值。
 * 		之前讲解的那两种方式是无法获取线程返回值的，因为run方法返回void。
 *
 * 		思考：
 * 			系统委派一个线程去执行一个任务，该线程执行完任务之后，可能会有一个执行结果，我们怎么能拿到这个执行结果呢？
 * 				使用第三种方式：实现Callable接口方式。
 */
public class ThreadTest03 {
    public static void main(String[] args) {
        /**
         * 守护线程：一般守护线程是一个死循环，所有的用户线程只要结束，守护线程自动结束。
         */
        //创建线程对象
        Thread thread = new BakDataThread();
        //要想让这个线程变成守护线程，非常简单，只需要在启动之前调用 它的setDaemon方法,并传一个boolean值true 就行了
        thread.setDaemon(true);
        //启动线程，现在这个线程就是守护线程了
        thread.start();

        //主线程的执行代码，主线程也是一个用户线程，当所有的用户线程结束了，守护线程自动结束
        for (int i = 0; i < 100; i++) {
            System.out.println("这里是主线程，正在执行" + i);
        }

        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        /**
         * 定时器：java.util.Timer
         */
        //创建定时器对象
        Timer timer = new Timer();
        //Timer timer = new Timer(true); 在定时器的构造方法中传一个boolean值true表示这个定时器以守护线程的方式创建

        //指定定时器的任务,这个schedule方法可以传三个参数，分别是 定时任务对象 ，第一次执行时间 ，间隔多久执行一次
        //定时任务类TimerTask是一个抽象类，实现了Runnable方法，我们可以编写一个类去继承TimerTask，然后重写run方法，在run方法中去实现定时任务所需要完成的事情 / 或者我们也可以直接采用匿名内部类的形式去实现TimerTask，重写run方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date firstTime = null;
        try {
            //指定第一次执行的时间
            firstTime = sdf.parse("2021-11-15 20:29:11");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //这里采用匿名内部类的写法了
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //这里可以编写一些具体的需求代码
                System.out.println("定时器任务执行");
            }
        } , firstTime , 1000);

        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        /**
         * 实现线程的第三种方式：实现Callable接口
         *      这种方式的优点：可以拿到线程的执行结果
         *      这种方式的缺点：效率比较低，在获取其他线程执行结果的时候，当前线程会受到阻塞
         */
        //第一步：创建一个 ”未来任务类“ 对象，构造方法中传一个实现了Callable接口的对象
        FutureTask futureTask = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {//call方法就相当于run方法，只不过这个方法是有返回值的，返回值是Object。而run方法没有返回值
                //线程执行一个任务，可能有返回值
                System.out.println("call方法执行");
                int i = 100;
                int j = 200;
                return i + j;//自动装箱（300——>Integer），返回Object，多态
            }
        });

        //创建线程对象,将上面的未来任务类对象作为参数传进来
        Thread t = new Thread(futureTask);
        //启动线程
        t.start();

        //这里是在main方法当中，怎么获取t线程的中call方法的返回值呢：很简单，使用 未来任务类对象的get方法
        //值得注意的是：get方法的执行会导致当前线程的阻塞 （这里当前线程就是主线程）
        try {
            Object obj = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //main方法中这里往下的代码想要执行，必须要等待get方法拿到t线程中call方法的执行结果，而get方法有可能需要比较长的时间才能拿到结果，因为t线程执行是需要时间的
        System.out.println("hello world");

        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        /**
         * 死锁:死锁代码要会写，只有会写的，才会在以后的开发中注意这个问题
         */
        Object obj1 = new Object();
        Object obj2 = new Object();
        Thread t1 = new MyThreadx(obj1,obj2);
        Thread t2 = new MyThready(obj1,obj2);
        t1.start();
        t2.start();
        /**
         * 分析：这以上代码表示t1线程，t2线程都共享obj1，obj2对象，而在他们各自的run方法中，都需要对obj1和obj2进行操作，才能完成run方法.
         * 然而他们分别先后对obj1，obj2同步，当t1拿到obj1的对象锁，在不释放锁的前提下还要去拿到obj2的对象锁才能执行完，但是此时obj2可能被t2拿到了，那么对于t1线程来说他就不能执行完
         * 于此同时，相对应的，t2占有obj2对象的对象锁，在不释放锁的前提下还要去拿obj1的对象锁，但是此时obj1被t1拿到了，t2线程也不能执行完，两个线程就僵持住了，这就是死锁现象
         * 为了防止死锁现象的出现，我们尽量不在使用synchronized关键字的同时还对其进行嵌套。
         */


    }
}

class MyThreadx extends Thread {
    //线程类MyThreadx具有两个Object属性，到时候只需要new对象传进来就实现了对对象的操作
    Object o1;
    Object o2;

    public MyThreadx(Object o1,Object o2){
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void run() {
        //先对o1同步，再对o2同步
        synchronized (o1){
            synchronized (o2){

            }
        }
    }
}

class MyThready extends Thread {
    //同理，线程类MyThready具有两个Object属性，到时候只需要new对象传进来就实现了对对象的操作
    Object o1;
    Object o2;

    public MyThready(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void run() {
        //先对o2同步，再对o1同步
        synchronized (o2){
            synchronized (o1){

            }
        }
    }
}
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

class BakDataThread extends Thread {
    @Override
    public void run() {
        //虽然这是个死循环，但是当他被设置为守护线程来执行时，当所有用户线程结束它就自动结束
        while (true) {
            System.out.println("守护线程：可以是用来实现定时备份数据");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
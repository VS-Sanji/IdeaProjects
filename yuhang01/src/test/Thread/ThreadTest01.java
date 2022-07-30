package test.Thread;

/**
 * 4、多线程
 *
 * 	4.1、什么是进程？什么是线程？
 * 		进程是一个应用程序（1个进程是一个软件）。
 * 		线程是一个进程中完成某部分功能的执行场景/执行单元。
 * 		一个进程可以启动多个线程。
 *
 * 	4.2、对于java程序来说，当在DOS命令窗口中输入：
 * 		java HelloWorld 回车之后。
 * 		会先启动JVM，而JVM就是一个进程。
 * 		JVM再启动一个主线程调用main方法。
 * 		同时再启动一个垃圾回收线程负责看护，回收垃圾。
 * 		最起码，现在的java程序中至少有两个线程并发，
 * 		一个是垃圾回收线程，一个是执行main方法的主线程。
 *
 * 	4.3、进程和线程是什么关系？
 *
 * 		进程可以看做是现实生活当中的公司。
 * 		线程可以看做是公司当中的某个员工。
 *
 * 		注意：
 * 			进程A和进程B的内存独立不共享。（阿里巴巴和京东资源不会共享的！）
 * 				魔兽游戏是一个进程
 * 				酷狗音乐是一个进程
 * 				这两个进程是独立的，不共享资源。
 *
 * 			线程A和线程B呢？
 * 				在java语言中：
 * 					线程A和线程B，堆内存和方法区内存共享。
 * 					但是栈内存独立，一个线程一个栈。
 *
 * 				假设启动10个线程，会有10个栈空间，每个栈和每个栈之间，互不干扰，各自执行各自的，这就是多线程并发。
 *
 * 			火车站，可以看做是一个进程。
 * 			火车站中的每一个售票窗口可以看做是一个线程。
 * 			我在窗口1购票，你可以在窗口2购票，你不需要等我，我也不需要等你。
 * 			所以多线程并发可以提高效率。
 *
 * 			java中之所以有多线程机制，目的就是为了提高程序的处理效率。
 *
 * 	4.4、思考一个问题：
 * 		使用了多线程机制之后，main方法结束，是不是有可能程序也不会结束。
 * 		main方法结束只是主线程结束了，主栈空了，其它的栈(线程)可能还在
 * 		压栈弹栈。
 *
 * 	4.5、分析一个问题：对于单核的CPU来说，真的可以做到真正的多线程并发吗？
 *
 * 		对于多核的CPU电脑来说，真正的多线程并发是没问题的。
 * 			4核CPU表示同一个时间点上，可以真正的有4个进程并发执行。
 *
 * 		什么是真正的多线程并发？
 * 			t1线程执行t1的。
 * 			t2线程执行t2的。
 * 			t1不会影响t2，t2也不会影响t1。这叫做真正的多线程并发。
 *
 * 		单核的CPU表示只有一个大脑：
 * 			不能够做到真正的多线程并发，但是可以做到给人一种“多线程并发”的感觉。
 * 			对于单核的CPU来说，在某一个时间点上实际上只能处理一件事情，但是由于
 * 			CPU的处理速度极快，多个线程之间频繁切换执行，跟人来的感觉是：多个事情
 * 			同时在做！！！！！
 * 				线程A：播放音乐
 * 				线程B：运行魔兽游戏
 * 				线程A和线程B频繁切换执行，人类会感觉音乐一直在播放，游戏一直在运行，
 * 				给我们的感觉是同时并发的。
 *
 * 		电影院采用胶卷播放电影，一个胶卷一个胶卷播放速度达到一定程度之后，
 * 		人类的眼睛产生了错觉，感觉是动画的。这说明人类的反应速度很慢，就像
 * 		一根钢针扎到手上，到最终感觉到疼，这个过程是需要“很长的”时间的，在
 * 		这个期间计算机可以进行亿万次的循环。所以计算机的执行速度很快。
 *
 * 5、java语言中，实现线程有两种方式，那两种方式呢？
 *
 * 	java支持多线程机制。并且java已经将多线程实现了，我们只需要继承就行了。
 *
 * 	第一种方式：编写一个类，直接继承java.lang.Thread，重写run方法。
 * 		// 定义线程类
 * 		public class MyThread extends Thread{
 * 			public void run(){
 *          ......
 *          }
 *      }
 * 		// 创建线程对象
 * 		MyThread t = new MyThread();
 * 		// 启动线程。
 * 		t.start();
 *
 * 	第二种方式：编写一个类，实现java.lang.Runnable接口，实现run方法。
 * 		// 定义一个可运行的类
 * 		public class MyRunnable implements Runnable {
 * 			public void run(){
 * 		    .....
 *          }
 * 		}
 * 		// 创建线程对象
 * 		Thread t = new Thread(new MyRunnable());
 * 		// 启动线程
 * 		t.start();
 *
 * 	注意：第二种方式实现接口比较常用，因为一个类实现了接口，它还可以去继承其它的类，更灵活。（面向接口编程）
 *
 *
 * 	JDK新特性：实现线程的第三种方式：FutureTask方式，实现Callable接口。（JDK8新特性。）
 * 		这种方式实现的线程可以获取线程的返回值。
 * 		之前讲解的那两种方式是无法获取线程返回值的，因为run方法返回void。
 *
 * 		思考：
 * 			系统委派一个线程去执行一个任务，该线程执行完任务之后，可能会有一个执行结果，我们怎么能拿到这个执行结果呢？
 * 				使用第三种方式：实现Callable接口方式。
 *
 * 6、关于线程对象的生命周期？
 * 	新建状态
 * 	就绪状态
 * 	运行状态
 * 	阻塞状态
 * 	死亡状态
 */
public class ThreadTest01 {
    public static void main(String[] args) {
        //这里是main方法，这里的代码属于主线程，在主栈中执行
        //新建分支线程对象
        MyThread01 myThread = new MyThread01();
        //启动这个分支线程
        //start()方法的作用是：启动一个分支线程，在JVM中开辟出一个新的栈空间，这个方法在执行后，开辟了新的栈空间，瞬间就结束了，
        //这段代码的任务只是为了开启一个新的栈空间，只要新的栈空间开辟出来，start()方法就结束了，线程就启动成功了
        //新启动的线程会自动去调用run方法(JVM调度的)，并且run方法在新的栈空间的底部，压栈
        //run方法在分支栈的底部，main方法在主栈的底部，run和main是平级的
        myThread.start();//这个方法是在主线程中（主栈中）执行的，只有它执行完了，下面的代码才会执行 这个方法结束的很快，很快就把新的线程启动了，然后他就弹栈了，下面的代码就会继续执行了

        //获取线程对象名字


        System.out.println(myThread.getName());//默认是这种形式：Thread-0
        //修改线程对象的名字
        myThread.setName("t1");

        //获取当前线程对象，这个currentThread()是一个静态方法，用 类名. 的形式去调用
        Thread main = Thread.currentThread();//这个方法出现在哪，获取的就是对应的线程对象，这里出现在main方法中，意味着这是主线程，获取的就是主线程对象
        System.out.println(main.getName());//当前线程名字：main

        //如果直接调用线程对象的run方法，是不能开辟出新的栈空间的，此时run方法就被视为一个普通的实例方法，还是运行在主线程当中
        //myThread.run();  不启动新的线程

        //先创建一个可运行的对象
        MyThread2 myThread2 = new MyThread2();
        //将可运行的对象封装成一个线程对象
        Thread thread = new Thread(myThread2);
        //合并以上代码    Thread thread = new Thread(new MyThread());
        //启动线程
        thread.start();

        //采用匿名内部类去创建线程对象
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("分支线程" + "--->" + i);
                }
            }
        });

        //这里的代码还是运行在主栈中的（主线程中的）
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程" + "--->" + i);

        }


        /**
         * 关于线程的sleep方法：
         *      static void sleep(long millis)  参数是一个毫秒数
         *      1.静态方法：Thread.sleep(1000)
         *      2.参数是毫秒数
         *      3.作用：让当前线程进入休眠，进入线程的 ”阻塞状态“ ，放弃占有的CPU时间片，让给其他线程使用。
         *          这行代码出现在A线程中，就让A线程休眠
         *          这行代码出现在B线程中，就让B线程休眠
         */
        try {
            //出现在主线程当中，执行到这，主线程休眠5秒钟，然后执行下方的打印语句
            Thread.sleep(5000);
            /**假如这里用 引用. 的方式去调用sleep方法，那也是看他出现在哪个线程中，实质上还是 转成 Thread.sleep()方法来执行，因为它是一个静态方法*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //上述代码执行完，五秒后执行这个打印语句
        System.out.println("线程休眠结束");

        //新建一个线程
        Thread thread2 = new Thread(new MyThread2());
        thread2.start();//启动线程

        try {
            //主线程睡眠5秒
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**5秒后唤醒 thread2 线程，让他从睡眠中醒来 终断了thread2现在的睡眠，这种终断睡眠的方式依靠了java的异常处理机制，让他报异常，然后catch到InterruptedException e，这个睡眠就结束掉了，继续执行后续代码*/
        thread2.interrupt();//调用interrupt方法


        /**
         * 在java中终止一个线程的执行，杀死这个线程 (不是将线程从休眠状态唤醒，而是直接杀死)
         *      1.stop()方法，但是这种方法已经过时，因为这种方式有很大的缺点：容易丢失数据。这种方式直接将线程杀死，线程没有保存的数据将会丢失，（不建议使用）
         *      2.打布尔标记：合理，比较适用
         */
        //下面两行代码就不要合并写了，因为关闭线程时需要用 r调用属性run，将其修改为false
        MyThread2 r = new MyThread2();
        Thread thread3 = new Thread(r);
        //启动线程
        thread3.start();
        //关闭线程
        r.run = false;
    }
}

/**java中实现多线程的方式*/

//第一种方式，编写一个类，继承Thread，重写run方法
class MyThread01 extends Thread{
    //重写run方法
    /**
     * 重点：run()方法中的异常不能上抛throws，只能try...catch
     * 因为run()方法在父类中没有抛出任何异常，而子类又不能抛出比父类更宽泛的异常
     */
    @Override
    public void run() {
        Thread t = Thread.currentThread();//出现在这个对象中，到时候返回的就是这个线程对象

        for (int i = 0; i < 10; i++) {
            System.out.println("分支线程" + "--->" + i);
        }

        try {
            //以下表示睡眠一年
            Thread.sleep(1000 * 60 * 60 * 24 * 365);//这里的sleep方法会报异常，但是只能用try...catch去捕捉，而不能上抛，这是因为run方法在父类中没有抛出任何异常，子类不能比父类抛出更宽泛的异常
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //这行代码就只能一年后执行了
        System.out.println("我是一年后才执行的");
    }
}


//第二种方式，编写一个类，实现java.lang.Runnable接口，实现run方法 （这并不是一个线程类，而是一个可运行的类，他实例化出来还不是一个线程）   以面向接口的形式
//这种方式实现接口比较常用，因为一个类实现了一个接口，它还可以去继承其他的类，更加灵活  面向接口编程
class MyThread2 implements Runnable {
    //合理结束线程的方式，打布尔标记
    boolean run = true;

    //重写run方法
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (run) {
                Thread t = Thread.currentThread();//出现在这个对象中，到时候返回的就是这个线程对象
                System.out.println("分支线程" + "--->" + i);
            }else {
                //在这里可以对数据进行保存的操作，这样结束线程数据就不会丢失了
                //.........
                //终止当前线程
                return;
            }
        }
    }
}
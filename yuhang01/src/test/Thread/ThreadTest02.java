package test.Thread;

/**
 * 1、(这部分内容属于了解)关于线程的调度
 *
 * 	1.1、常见的线程调度模型有哪些？
 * 		抢占式调度模型：
 * 			那个线程的优先级比较高，抢到的CPU时间片的概率就高一些/多一些。java采用的就是抢占式调度模型。
 * 		均分式调度模型：
 * 			平均分配CPU时间片。每个线程占有的CPU时间片时间长度一样。平均分配，一切平等。有一些编程语言，线程调度模型采用的是这种方式。
 *
 * 	1.2、java中提供了哪些方法是和线程调度有关系的呢？
 * 		实例方法：
 * 			void setPriority(int newPriority) 设置线程的优先级
 * 			int getPriority() 获取线程优先级
 * 			最低优先级1
 * 			默认优先级是5
 * 			最高优先级10
 * 			优先级比较高的获取CPU时间片可能会多一些。（但也不完全是，大概率是多的。）
 *
 * 		静态方法：
 * 			static void yield()  让位方法  暂停当前正在执行的线程对象，并执行其他线程
 * 			yield()方法不是阻塞方法。让当前线程让位，让给其它线程使用。
 * 			yield()方法的执行会让当前线程从“运行状态”回到“就绪状态”。而sleep()方法的执行是让当前线程从“运行状态”变成“阻塞状态”
 * 			注意：在回到就绪之后，有可能还会再次抢到CPU时间片。
 *
 * 		实例方法：
 * 			void join()
 * 			合并线程
 * 			class MyThread1 extends Thread {
 * 				public void doSome(){
 * 					MyThread2 t = new MyThread2();
 * 					t.join(); // 当前线程进入阻塞，t线程执行，直到t线程结束，当前线程才可以继续。 不是栈合并了，而是线程之间发生了等待关系 相当于“插队”
 *              }
 *          }
 *
 * 			class MyThread2 extends Thread{
 *
 * 			}
 *
 * 2、关于多线程并发环境下，数据的安全问题。
 *
 * 	2.1、为什么这个是重点？
 * 		以后在开发中，我们的项目都是运行在服务器当中，而服务器已经将线程的定义，线程对象的创建，线程的启动等，都已经实现完了。这些代码我们都不需要编写。
 * 		最重要的是：你要知道，你编写的程序需要放到一个多线程的环境下运行，你更需要关注的是这些数据在多线程并发的环境下是否是安全的。（重点：*****）
 *
 * 	2.2、什么时候数据在多线程并发的环境下会存在安全问题呢？
 * 		三个条件：
 * 			条件1：多线程并发。
 * 			条件2：有共享数据。
 * 			条件3：共享数据有修改的行为。
 * 		满足以上3个条件之后，就会存在线程安全问题。
 *
 * 	2.3、怎么解决线程安全问题呢？
 * 		当多线程并发的环境下，有共享数据，并且这个数据还会被修改，此时就存在线程安全问题，怎么解决这个问题？
 * 			线程排队执行。（不能并发）。
 * 			用排队执行解决线程安全问题。
 * 			这种机制被称为：线程同步机制。
 * 			专业术语叫做：线程同步，实际上就是线程不能并发了，线程必须排队执行。
 *
 * 		怎么解决线程安全问题呀？
 * 			使用“线程同步机制”。
 * 		线程同步就是线程排队了，线程排队了就会牺牲一部分效率，没办法，数据安全第一位，只有数据安全了，我们才可以谈效率。数据不安全，没有效率的事儿。
 *
 * 	2.4、说到线程同步这块，涉及到这两个专业术语：
 *
 * 		异步编程模型：
 * 			线程t1和线程t2，各自执行各自的，t1不管t2，t2不管t1，谁也不需要等谁，这种编程模型叫做：异步编程模型。
 * 			其实就是：多线程并发（效率较高） 异步就是并发。
 *
 * 		同步编程模型：
 * 			线程t1和线程t2，在线程t1执行的时候，必须等待t2线程执行结束，或者说在t2线程执行的时候，必须等待t1线程执行结束，两个线程之间发生了等待关系，这就是同步编程模型。效率较低。线程排队执行。同步就是排队。
 *
 * 3、Java中有三大变量？【重要的内容。】
 * 	    实例变量：在堆中。
 * 	    静态变量：在方法区。
 *  	局部变量：在栈中。
 * 	以上三大变量中：
 * 		局部变量永远都不会存在线程安全问题。因为局部变量不共享。（一个线程一个栈）,局部变量在栈中,所以局部变量永远都不会共享。
 * 	    实例变量在堆中，堆只有1个。
 * 	    静态变量在方法区中，方法区只有1个。
 * 	    堆和方法区都是多线程共享的，所以可能存在线程安全问题。
 *
 * 	局部变量+常量：不会有线程安全问题。
 * 	成员变量：可能会有线程安全问题。
 *
 * 4、如果使用局部变量的话：
 * 	建议使用：StringBuilder。
 * 	因为局部变量不存在线程安全问题。选择StringBuilder.StringBuffer效率比较低。
 * 	ArrayList是非线程安全的。
 * 	Vector是线程安全的。
 * 	HashMap HashSet是非线程安全的。
 * 	Hashtable是线程安全的。
 *
 * 5、总结：
 * 	synchronized有三种写法：
 * 		第一种：同步代码块.灵活
 * 			synchronized(线程共享对象){
 * 				同步代码块;
 * 			}
 * 		第二种：在 实例方法 上使用synchronized
 * 			表示共享对象一定是this,并且同步代码块是整个方法体。
 * 		第三种：在 静态方法 上使用synchronized
 * 			表示找 类锁。
 * 			类锁永远只有1把,就算创建了100个对象，那类锁也只有一把。
 * 		    只要操作对象是同一个类的线程，在其中一个线程拿到类锁时，其他线程都需要等待
 *
 * 		对象锁：1个对象1把锁，100个对象100把锁。
 * 		类锁：100个对象，也可能只是1把类锁。
 *
 * 6、聊一聊，我们以后开发中应该怎么解决线程安全问题？是一上来就选择线程同步吗？synchronized
 * 		不是，synchronized会让程序的执行效率降低，用户体验不好。系统的用户吞吐量降低。用户体验差。在不得已的情况下再选择线程同步机制。
 * 	第一种方案：尽量使用局部变量代替“实例变量和静态变量”。
 * 	第二种方案：如果必须是实例变量，那么可以考虑创建多个对象，这样实例变量的内存就不共享了。（一个线程对应1个对象，100个线程对应100个对象，对象不共享，就没有数据安全问题了。）
 * 	第三种方案：如果不能使用局部变量，对象也不能创建多个，这个时候就只能选择synchronized了。线程同步机制。
 */
public class ThreadTest02 {
    public static void main(String[] args) {
        //创建账户对象
        Account act = new Account("act1",10000);
        //创建线程对象
        //以下这两个线程对象所共享的账户是同一个
        Thread t1 = new Thread(new AccountThread(act));
        Thread t2 = new Thread(new AccountThread(act));
        t1.setName("t1");
        t2.setName("t2");
        //启动线程
        t1.start();
        t2.start();

    }
}

class AccountThread implements Runnable{
    //将来以该可运行类创建出来的线程对象所启动的线程，均共享账户对象,对这个账户进行操作
    private Account account;

    public AccountThread(Account account) {
        this.account = account;
    }

    //以这个run方法来进行取款操作
    @Override
    public void run() {
        double money = 5000;
        //取款5000
        account.withDraw(money);
    }
}

class Account {
    //账户名
    private String actno;

    //余额
    private double balance;

    //obj对象
    private Object obj;

    public Account() {
    }

    public Account(String actno, double balance) {
        this.actno = actno;
        this.balance = balance;
    }

    public String getActno() {
        return actno;
    }

    public void setActno(String actno) {
        this.actno = actno;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //取款的方法
    //解决线程安全问题，采取线程同步（排队）
    public synchronized void withDraw(double money){//synchronized可以出现在实例方法上，但是此时共享对象是 this-当前对象，只有是共享了当前对象的线程，来执行这个方法才需要进行线程同步 ，并且整个方法体都是同步代码块。
                                                    //如果共享的对象就是this，并且需要同步的代码块是整个方法体，那么建议使用这种方式
        //以下这三行代码必须是线程排队的，不能并发。只有当一个线程把这三行代码全部执行完了，其他线程才能进来执行
        /**
         * 线程同步机制语法：
         *      synchronized(){
         *          要进行线程同步（排队）的代码
         *      }
         *      synchronized后面小括号中传的 “数据” ，是相当关键的，这个数据必须是多个线程所共享的 “数据”，才能达到多线程排队执行
         *      （）中写什么，要看你想让哪些线程同步
         *      假设有 t1 t2 t3 t4 t5 五个线程，如果只希望t1 t2 排队，那就填一个只由t1 t2 线程共享而t3 t4 t5不共享的对象
         *      这里的 账户对象 是两个线程共享的，所以填它就让这两个线程排队。在这里恰好是当前对象，所以可以填 this，但是其他情况下不一定是this，只要是多线程共享的对象就行
         *      当遇到synchronized关键字，就触发线程同步模式。在java中，每个对象都有一把对象锁，t1先进来就会拿到账户对象的对象锁，这时t2再进来就拿不到了，只有等待t1线程执行完，锁才会释放（归还），t2才能获取
         *      在java语言中，任何对象都有一把“锁”，其实这把锁就是个标记，只是把他叫做 锁。100个对象，100把锁，1个对象1把锁
         *
         *      以下代码执行原理：
         *          1.假设t1、t2线程并发，开始执行以下代码的时候，肯定有一个先，有一个后。
         *          2.假设t1先执行了，遇到了synchronized，这个时候就自动找“后面共享对象”的对象锁，找到之后占有这把锁，然后执行同步代码块中的代码，在代码执行过程中一直都是占有这把锁的，直到同步代码块中的代码执行结束，这把锁才会释放
         *          3.假设t1已经占有这把锁，此时t2也遇到了synchronized关键字，也会去占有后面共享对象的对象锁，结果这把锁被t1占有，t2只能在同步代码块外面等待t1的结束，直到t1把同步代码块执行结束，t1会归还这把锁，此时t2终于等到这把锁，然后t2占有这把锁去执行同步代码块中的代码
         */
        synchronized (obj){   //这里obj是个引用指向一个对象，Account只有这一个obj对象，Account被t1 t2共享，那么obj也被共享，obj的对象锁也只有一个，谁拿到谁执行同步代码块。
                              //总之只要是这两个线程所共享的对象，都行，让线程们去抢这个锁，而锁只有一把，就起到了区分先后执行的效果。 而同时这个对象也指定了这个同步代码块对于哪些线程是需要排队执行的
            //取款之前的余额
            double before = this.getBalance();
            //取款之后的余额
            double after = before - money;
            //更新余额
            //假设t1先进来执行代码，上面两行执行完了，但是这最后一行的更新余额没有执行，此时保存的余额数据还是10000；如果这个时候t2也进来执行了，也取了5000，那这样计算余额就还是5000，但是一共取了10000，这时就出现了线程安全问题
            //余额 最初10000，t1进来取5000，没更新，就还剩10000，t2进来取5000，t1更新余额变成5000，t2更新余额还是5000
            this.setBalance(after);
            System.out.println(Thread.currentThread().getName() + "取款" + money + " " + "余额" + this.getBalance());
        }

    }
}


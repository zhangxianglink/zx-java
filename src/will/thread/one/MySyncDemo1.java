package will.thread.one;

public class MySyncDemo1 implements Runnable{

    Object lock = new Object();

    public synchronized void method(){
        String name = Thread.currentThread().getName();
        try {
            System.out.println("方法（默认当前对象）🔒：线程" + name + "开始执行。");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("方法（默认当前对象）🔒：线程" + name + "结束。");
    }


    @Override
    public void run() {
        method();
        synchronized (this) {
            String name = Thread.currentThread().getName();
            try {
                System.out.println("代码(当前对象)🔒：线程" + name + "开始执行。");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("代码（当前对象）🔒：线程" + name + "结束。");
        }
        synchronized (lock) {
            String name = Thread.currentThread().getName();
            try {
                System.out.println("同步代码块（指定对象）🔒：线程" + name + "开始执行。");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("同步代码块（指定对象）🔒：线程" + name + "结束。");
        }
    }

    public static void main(String[] args) {
        MySyncDemo1 mySyncDemo1 = new MySyncDemo1();
        Thread t1 = new Thread(mySyncDemo1);
        Thread t2 = new Thread(mySyncDemo1);
        t1.start();
        t2.start();
        while (t1.isAlive()|| t2.isAlive()){

        }
        System.out.println("同步代码块🔒：是为了弥补更细粒度的锁操作才产生的。");
    }
}

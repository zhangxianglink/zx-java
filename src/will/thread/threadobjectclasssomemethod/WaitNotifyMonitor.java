package will.thread.threadobjectclasssomemethod;

/**
 * 死锁问题证明了，wait() 只会锁住当前对象
 * wait期间什么都做不了,只有被唤醒后才能继续操作
 * https://www.cnblogs.com/kongzhongqijing/articles/3630264.html
 */
public class WaitNotifyMonitor {

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();

        Thread thread0 = new Thread(() -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "获取到a🔒");
                synchronized (b){
                    System.out.println(Thread.currentThread().getName() + "获取到b🔒，不释放");
                    System.out.println(Thread.currentThread().getName() + "释放a🔒，waiting");
                    try {
                        a.wait();
                        //
//                        a.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("over");
        });


        Thread thread1 = new Thread(() -> {
            synchronized (a) {
                // 关于程序退出：只唤醒A是不够的，因为唤醒时，线程2还持有锁A，要等线程2释放后，线程1才能拿到锁并退出
//                a.notify();
//                try {
//                    a.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(Thread.currentThread().getName() + "获取到a🔒");
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + "想要获取b🔒");
                }

            }

        });

        thread0.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
    }
}

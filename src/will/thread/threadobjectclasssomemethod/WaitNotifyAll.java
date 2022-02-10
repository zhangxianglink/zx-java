package will.thread.threadobjectclasssomemethod;

/**
 * 同时唤醒两个线程
 */
public class WaitNotifyAll implements Runnable{

    private static Object object = new Object();

    @Override
    public void run() {
        synchronized (object){
            System.out.println(Thread.currentThread().getName() + "获取到🔒，进入waiting");
            try {
                object.wait();
                System.out.println(Thread.currentThread().getName() + "结束阻塞，继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyAll all = new WaitNotifyAll();
        Thread thread0 = new Thread(all);
        Thread thread1 = new Thread(all);
        Thread thread2 = new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + " notifyAll");
                object.notifyAll();
            }
        });

        thread0.start();
        thread1.start();
        Thread.sleep(100);
        thread2.start();
    }
}

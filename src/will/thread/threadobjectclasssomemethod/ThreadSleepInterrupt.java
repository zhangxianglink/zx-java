package will.thread.threadobjectclasssomemethod;

import java.util.Map;

public class ThreadSleepInterrupt {
    public static final Object obj = new Object();

    static class SleepSyn implements Runnable {

        @Override
        public void run() {
            synchronized (obj){
                System.out.println(Thread.currentThread().getName() + "in----------------");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("sleep 不释放锁");
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "out----------------");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new SleepSyn(),"first");
        Thread thread2 = new Thread(new SleepSyn(),"second");

        thread.start();
        thread2.start();

        Thread.sleep(1000);
        thread.interrupt();
    }
}

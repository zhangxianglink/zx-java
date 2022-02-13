package will.thread.threadobjectclasssomemethod;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 针对OddAndEvenBunmber 进行优化，减少判断，减少loop
 */
public class OddAndEvenImprove {

    private static AtomicInteger count  = new AtomicInteger(1);
    private static final Object object = new Object();

    static class OutNumber implements Runnable {

        @Override
        public void run() {
            synchronized (object){
                while (count.get() < 101) {
                    System.out.println(Thread.currentThread().getName() + " 输出： " + count.getAndAdd(1));
                    object.notify();
                    try {
                        if (count.get() < 101) {
                            object.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread odd = new Thread(new OutNumber(), "奇数---");
        Thread even = new Thread(new OutNumber(), "偶数---");

        odd.start();
        Thread.sleep(100);
        even.start();
    }

}

package will.thread.threadobjectclasssomemethod;

import java.util.concurrent.atomic.AtomicBoolean;

public class OddAndEvenBunmber implements Runnable{

    private AtomicBoolean aBoolean;
    private final Object object;

    public OddAndEvenBunmber(AtomicBoolean aBoolean, Object object) {
        this.aBoolean = aBoolean;
        this.object = object;
    }


    @Override
    public void run() {
        synchronized (object) {
            for (int i = 1; i <= 100; i++) {
                if (aBoolean.get() && (i & 1) == 1) {
                    // 输出奇数
                    try {
                        System.out.println(Thread.currentThread().getName()+"奇数：" + i);
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    object.notify();
                }
                if (!aBoolean.get() && (i & 1) == 0){
                    // 输出偶数
                    System.out.println(Thread.currentThread().getName()+"偶数：" + i);
                    object.notify();
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();

        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        AtomicBoolean atomicBoolean2 = new AtomicBoolean(false);

        Thread odd = new Thread(new OddAndEvenBunmber(atomicBoolean,o));
        Thread even = new Thread(new OddAndEvenBunmber(atomicBoolean2,o));

        odd.start();
        Thread.sleep(10);
        even.start();


    }
}

package will.thread.threadobjectclasssomemethod;

public class Wait {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();

        Thread release = new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "get🔒");
                object.notify();
                System.out.println(Thread.currentThread().getName() + "调用notify");
            }
        });

        Thread waiting = new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "get🔒");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "阻塞结束，运行完毕");
            }
        });

        waiting.start();
        Thread.sleep(100);
        release.start();
    }
}

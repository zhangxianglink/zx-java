package will.thread.threadobjectclasssomemethod;

/**
 * 嵌套问题
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
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("over");
        });

        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "获取到a🔒");
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + "想要获取b🔒");
                }
            }

        });

        thread0.start();
        thread1.start();
    }
}

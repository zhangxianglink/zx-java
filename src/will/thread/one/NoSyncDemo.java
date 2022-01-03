package will.thread.one;

public class NoSyncDemo implements Runnable{

    public static NoSyncDemo noSyncDemo = new NoSyncDemo();

    private static int count = 0;


    @Override
    public synchronized void run() {
        for (int i = 0; i < 100000; i++) {
            count ++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(noSyncDemo);
        Thread thread1 = new Thread(noSyncDemo);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(count);
    }
}

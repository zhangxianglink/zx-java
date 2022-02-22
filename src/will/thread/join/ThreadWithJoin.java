package will.thread.join;

public class ThreadWithJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" 运行完成");
        },"子线程");
        thread.start();
        System.out.println(Thread.currentThread().getName()+" waiting");
//        thread.join();
        synchronized (thread){
            // main 调用wait, 等待thread执行完成后，唤醒锁，继续执行下一步
            thread.wait();
            System.out.println(Thread.currentThread().getName()+ " 唤醒");
        }
        System.out.println(Thread.currentThread().getName()+ " over");

    }
}

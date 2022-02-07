package will.thread.core.create.stop;

/**
 * @author nuc
 */
public class StopThreadWithSleep implements Runnable{

    @Override
    public void run() {
        int num = 0;
        try {
        while (num < 300){
            System.out.println(num+" "+Thread.currentThread().isInterrupted() );
            num ++;
            Thread.sleep(5);
           }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("thread over!" + Thread.currentThread().isInterrupted() );
        }
    }

    /**
     * 阻塞情况下，线程抛出异常中断
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThreadWithSleep());
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}

package will.thread.core.create.stop;

/**
 * @author nuc
 */
public class StopThread implements Runnable{

    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE/2){
            if (num % 10000 == 0){
                System.out.println(num+" "+Thread.currentThread().isInterrupted() );
            }
            num ++;
        }
        System.out.println("thread over!" + Thread.currentThread().isInterrupted() );
    }

    /**
     * 普通情况下interrupt判断停止线程
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThread());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}

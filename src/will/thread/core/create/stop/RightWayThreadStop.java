package will.thread.core.create.stop;

/**
 * @author nuc
 */
public class RightWayThreadStop implements Runnable{
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && true){
            try {
                System.out.println("running");
                methodOutException();
            } catch (InterruptedException e) {
                System.out.println("异常捕获成功！");
                e.printStackTrace();
            }finally {
                System.out.println("处理完成，结束运行✅");
                Thread.currentThread().interrupt();
            }
        }
    }

    // 传递中断（default）
    private void methodOutException() throws InterruptedException {
        Thread.sleep(1000);
    }

    // 恢复中断
    private void reInterrupt(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // 恢复中断
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayThreadStop());
        thread.start();
        Thread.sleep(700);
        thread.interrupt();
    }
}

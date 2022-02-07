package will.thread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;

public class VolatileCanStop {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        Producer1 producer = new Producer1(queue);
        Thread thread = new Thread(producer);
        thread.start();
        Thread.sleep(20);

        Consumer1 consumer = new Consumer1(queue);
        while (consumer.getCount()){
            System.out.println(consumer.blockingDeque.take() + "被消费了");
        }
        // 状态设置为 true
        thread.interrupt();
        // 改变执行者（main）状态
//        thread.isInterrupted();
        // 改变执行者（main）状态
//        Thread.interrupted();

        System.out.println("无需生产了");
    }

}

class Producer1 implements Runnable{

    ArrayBlockingQueue blockingDeque;

    public Producer1(ArrayBlockingQueue blockingDeque){
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        int num = 1;
        try {
            // 正确停止的方式
            while (num <= 1000 && !Thread.currentThread().isInterrupted()){
                blockingDeque.put(num);
                System.out.println("生产数据放入队列：" + num + " 队列现状：" + blockingDeque.size());
                num ++;
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("生产结束");
        }
    }
}

class Consumer1 {
    ArrayBlockingQueue blockingDeque;

    public Consumer1(ArrayBlockingQueue blockingDeque){
        this.blockingDeque = blockingDeque;
    }

    private  int num = 0;

    public boolean getCount(){
        num ++;
        if (num < 100){
            return true;
        }else {
            return false;
        }
    }

}
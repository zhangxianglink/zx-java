package will.thread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

public class VolatileCanotStop {

    public static void main(String[] args) throws InterruptedException {
        // 满了加不进去，没数据拿不到，都会造成阻塞
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Thread thread = new Thread(producer);
        thread.start();
        Thread.sleep(20);

        Consumer consumer = new Consumer(queue);
        while (consumer.getCount()){
            System.out.println(consumer.blockingDeque.take() + "被消费了");
        }
        producer.flag = true;
        System.out.println("无需生产了");
    }

}

class Producer implements Runnable{
    public  volatile  boolean flag = false;
    ArrayBlockingQueue blockingDeque;

    public Producer(ArrayBlockingQueue blockingDeque){
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        int num = 1;
        try {
            while (num <= 10000 && !flag){
                // 消费者不消费了，线程阻塞在此无法结束, 无法执行到flag判断volatile失效
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

class Consumer {
    ArrayBlockingQueue blockingDeque;

    public Consumer(ArrayBlockingQueue blockingDeque){
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
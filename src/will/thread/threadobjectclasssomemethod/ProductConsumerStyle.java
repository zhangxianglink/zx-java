package will.thread.threadobjectclasssomemethod;

import java.time.LocalDateTime;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProductConsumerStyle {

    public static void main(String[] args) {
        Deque deque = new Deque();
        Producter producter = new Producter(deque);
        Consumer consumer = new Consumer(deque);
        Thread thread = new Thread(producter);
        Thread thread1 = new Thread(consumer);
        thread.start();
        thread1.start();

    }
}

class Deque {

    private int maxSize;
    private CopyOnWriteArrayList<LocalDateTime> list;

    public Deque()  {
        maxSize = 10;
        list = new CopyOnWriteArrayList<>();
    }

    public synchronized void put() throws InterruptedException {
        // 数据量满足 挂起
        if (list.size() == maxSize){
            wait();
        }
        list.add(LocalDateTime.now());
        System.out.println("product： 生产最新数据，当前数据量：" + list.size());
        // 放开锁，通知消费
        notify();
    }

    public synchronized void task() throws InterruptedException {
        // 没数据 挂起
        if (list.size() == 0){
            wait();
        }
        System.out.println("consumer： 获取最新数据，当前数据量：" + list.size());
        list.remove(0);
        // 放开锁，通知生产
        notify();
    }
}

class Producter implements Runnable{

    private Deque deque;

    Producter(Deque deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                deque.put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{

    private Deque deque;

    Consumer(Deque deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                deque.task();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
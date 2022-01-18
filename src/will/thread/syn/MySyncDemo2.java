package will.thread.syn;

/**
 * 类🔒
 * @author nuc
 */
public class MySyncDemo2 implements Runnable{



    public static synchronized void method(){
        String name = Thread.currentThread().getName();
        try {
            System.out.println("静态方法类🔒：线程" + name + "开始执行。");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("静态方法类🔒：线程" + name + "结束。");
    }

    public void method2(){
        synchronized(MySyncDemo2.class) {
            String name = Thread.currentThread().getName();
            try {
                System.out.println("类🔒：线程" + name + "开始执行。");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("类🔒：线程" + name + "结束。");
        }
    }

    @Override
    public void run() {
        method2();
    }

    public static void main(String[] args) {
        MySyncDemo2 mySyncDemo1 = new MySyncDemo2();
        MySyncDemo2 mySyncDemo2 = new MySyncDemo2();
        Thread t1 = new Thread(mySyncDemo1);
        Thread t2 = new Thread(mySyncDemo2);
        t1.start();
        t2.start();
        while (t1.isAlive()|| t2.isAlive()){

        }
        System.out.println("类🔒：针对类本身而非实例生效");
    }
}

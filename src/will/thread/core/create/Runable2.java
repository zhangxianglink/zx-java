package will.thread.core.create;

/**
 * @author nuc
 */
public class Runable2 implements Runnable{

    @Override
    public void run() {
        System.out.println("2。实现Runnable创建线程");
    }

    public static void main(String[] args) {
        new Thread(new Runable2()).start();
    }

}

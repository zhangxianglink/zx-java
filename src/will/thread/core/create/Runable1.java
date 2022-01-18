package will.thread.core.create;

public class Runable1 extends Thread{
    @Override
    public void run() {
        System.out.println("1.继承Thread方式实现线程创建");
    }

    public static void main(String[] args) {
        new Runable1().start();
    }
}

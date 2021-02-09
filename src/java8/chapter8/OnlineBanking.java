package java8.chapter8;

/**
 * @Author zx
 * @Date 2021/2/9
 **/
public abstract class OnlineBanking {

    public void processCustomer(int id ){
        // 根据id获取内容
        String userId = id + "";
        //子类继承实现不同逻辑
        doSomething(userId);
    }

    abstract void doSomething(String userId);



}

package java8.chapter8;

import java.util.function.Function;

/**
 * @Author zx
 * @Date 2021/2/9
 **/
public  class OnlineBankingLambda {


    public int processCustomer(int id , Function<String, Integer> function){
        // 根据id获取内容
        String userId = id + "";
        return function.apply(userId);
    }
}

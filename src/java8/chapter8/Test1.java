package java8.chapter8;

/**
 * @Author zx
 * @Date 2021/2/9
 **/
public class Test1 {

    /**
     * lambda表达式简化设计模式
     * 1.策略模式
     * 2.模板模式
     * 3.观察者模式
     * 4.责任链模式
     * 5.工厂模式
     */
    public static void main(String[] args) {
        one();
        two();
    }


    /**
     * 一个函数式接口  ValidationStrategy
     * 多种接口实现 lambda
     * 一个或多个使用的用户 Validator
     */
    private static void one(){
        Validator validator = new Validator((String s) -> s.matches("[a-z]+"));
        boolean abcd = validator.validate("abcd");

        Validator validator2 = new Validator((String s) -> s.matches("[1-9]+"));
        boolean bbbb = validator2.validate("09245");

        System.out.println("策略模式1：" + abcd );
        System.out.println("策略模式2：" + bbbb );
    }


    /**
     * 模板模式，使用这个方法，但是需要对某些行进行修改
     */
    private static void two(){
        int i = new OnlineBankingLambda().processCustomer(123523, String::length);
        System.out.println("模板模式：" + i );
    }
}

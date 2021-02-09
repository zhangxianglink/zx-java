package java8.chapter8;

/**
 * @Author zx
 * @Date 2021/2/9
 **/
public class Validator {
    private final ValidationStrategy strategy;

    public  Validator(ValidationStrategy s){
        this.strategy = s;
    }

    public boolean validate(String str){
        return this.strategy.execute(str);
    }
}

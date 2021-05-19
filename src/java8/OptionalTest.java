package java8;

import java8.pojo.Car;
import java8.pojo.Insurance;
import java8.pojo.Person;

import java.util.HashMap;
import java.util.Optional;

/**
 * @apiNote 应用optional的几种模式
 * @author xiang.z
 * @since 2021-05-19 10:38
 */
public class OptionalTest {

    public static void main(String[] args) {
        test3();
    }

    private static void test3() {
        HashMap<Object, Object> map = new HashMap<>(8);
        map.put("k","v");
        // 1 map.get("key") 取值判断
        Optional<Object> optional = Optional.ofNullable(map.get("l"));
        System.out.println(optional.orElse("value"));
        // 封装异常
        Optional<Integer> aaaa = stringToInt("aaaa");

    }

    private static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private static void test1(){
        Optional<Object> empty = Optional.empty();
        String value = "clear";
        Optional<String> clear = Optional.of(value);
        System.out.println("----------------------" + clear.get());
        String value2 = null;
        Optional<String> value21 = Optional.ofNullable(value2);
        String s = value21.orElseGet(String::new);
        System.out.println("----------------------" + s);
    }

    /**
     * empty       返回一个空的 Optional 实例
     * filter      如果值存在并且满足提供的谓词，就返回包含该值的 Optional 对象；否则返回一个空的Optional 对象
     * flatMap     如果值存在，就对该值执行提供的 mapping 函数调用，返回一个 Optional 类型的值，否则就返回一个空的 Optional 对象
     * get         如果该值存在，将该值用 Optional 封装返回，否则抛出一个 NoSuchElementException 异常
     * ifPresent   如果值存在，就执行使用该值的方法调用，否则什么也不做
     * isPresent   如果值存在就返回 true，否则返回 false
     * map         如果值存在，就对该值执行提供的 mapping 函数调用
     * of          将指定值用 Optional 封装之后返回，如果该值为 null，则抛出一个 NullPointerException
     * ofNullable  将指定值用 Optional 封装之后返回，如果该值为 null，则返回一个空的 Optional 对象
     * orElse      如果有值则将其返回，否则返回一个默认值
     * orElseGet   如果有值则将其返回，否则返回一个由指定的 Supplier 接口生成的值
     * orElseThrow 如果有值则将其返回，否则抛出一个由指定的 Supplier 接口生成的异常
     */
    private static void test2(){
        // map
        Optional<String> msi = Optional.of("msi ice land");
        System.out.println(
                msi.map(String::length).get()
        );

        Insurance insurance = new Insurance();
        insurance.setAge(2);
        insurance.setName("车险");
        Car car = new Car();
        car.setInsurance(Optional.of(insurance));
        Person person = new Person();
        person.setCar(Optional.of(car));

        // flatmap
        String s = Optional.of(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName).orElse("UnKnow");
        System.out.println(s);

        // ifPresent
        msi.ifPresent(System.out::println);

        // isPresent
        System.out.println(msi.isPresent());
    }

}

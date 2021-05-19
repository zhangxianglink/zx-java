package java8.pojo;

import java.util.Optional;

/**
 * @author xiang.z
 * @since 2021-05-19 11:19
 */
public class Person {
    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }

    private Optional<Car> car;
}

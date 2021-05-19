package java8.pojo;

import java.util.Optional;

/**
 * @author xiang.z
 * @since 2021-05-19 11:20
 */
public class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }
}

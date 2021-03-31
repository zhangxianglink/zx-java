package java8.factory;

public class PhoneFactory extends Factory{
    @Override
    Product createSelf() {
        return new PhoneProduct();
    }
}

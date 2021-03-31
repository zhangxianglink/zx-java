package java8.factory;

public class PcFactory extends Factory{
    @Override
    Product createSelf() {
        return new PcProduct();
    }
}

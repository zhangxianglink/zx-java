package java8.factory;

public class Main {
    public static void main(String[] args) {
        Product product = new PcFactory().create();
        product.use();

        Product product1 = new PhoneFactory().create();
        product1.use();
    }
}

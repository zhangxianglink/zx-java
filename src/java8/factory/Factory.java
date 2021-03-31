package java8.factory;

public abstract class Factory {
    Product create(){
        Product self = createSelf();
        // do something
        return self;
    }

    abstract Product createSelf();
}

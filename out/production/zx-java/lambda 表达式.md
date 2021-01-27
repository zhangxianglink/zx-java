## lambda 表达式

通过行为参数化(策略模式)，我们自定义接口方法去表示行为，然后通过实现类传递。为了简化代码，我们采用匿名类去实现行为，但是仍然十分的繁琐，所以采用lambda传递参数。

1. 基本概念

   > lambda 来源于学术界的一套用于描述计算的演算法，在我们的代码中，它没有名称，但它
   >
   > 有参数列表、函数主体、返回类型，可能还有一个可以抛出的异常列表。可以理解为一种简洁书写可传递匿名函数的方式。

```java
布尔表达式 				(List<String> list) -> list.isEmpty() 
创建对象				 () -> new Apple(10) 
消费一个对象			    (Apple a) -> {  System.out.println(a.getWeight()); } 
从一个对象中选择/抽取 	 (String s) -> s.length() 
组合两个值 				(int a, int b) -> a * b 
比较两个对象	 			(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
```

2. 使用Lambda

   > 函数式接口，就是只有一个抽象方法的接口（可以定义default 方法），java中我们常见的函数式接口  Comparator<T> ,  Runnable ,都会添加注解@FunctionalInterface进行提醒。

```java
lambda可以为函数式接口提供一个具体实现实例，在此之前是通过匿名内部类实现。
 Runnable r1 = () -> System.out.println("Hello World 1");
 Runnable r2 = new Runnable(){ 
     public void run(){ 
     System.out.println("Hello World 2"); 
 	} 
};
```

> Java8新加入的函数式接口 （免去自定义接口）

```java
常用函数式接口列表：
    
Predicate<T> T->boolean					 (String s) -> !s.isEmpty();
    									IntPredicate,LongPredicate, DoublePredicate 
                                            
Consumer<T> T->void 					(Integer i) -> System.out.println(i)
    									IntConsumer,LongConsumer, DoubleConsumer 
                                            
Function<T,R> T->R						 (String s) -> s.length()
    									IntFunction<R>, 
                                            IntToDoubleFunction, 
                                            IntToLongFunction, 
                                            LongFunction<R>, 
                                            LongToDoubleFunction, 
                                            LongToIntFunction, 
                                            DoubleFunction<R>, 
                                            ToIntFunction<T>, 
                                            ToDoubleFunction<T>, 
                                            ToLongFunction<T>
                                                
Supplier<T> ()->T  						 () -> new Apple(10)
                                                BooleanSupplier,IntSupplier,                                                             LongSupplier, 
									         DoubleSupplier 
                                            
UnaryOperator<T> T->T 						  IntUnaryOperator, 
                                                LongUnaryOperator, 
                                                DoubleUnaryOperator 
                                                    
BinaryOperator<T> (T,T)->T 						(int a, int b) -> a * b
                                                    IntBinaryOperator, 
                                                    LongBinaryOperator, 
                                                    DoubleBinaryOperator 
                                                        
BiPredicate<L,R> (L,R)->boolean 
                                                        
BiConsumer<T,U> (T,U)->void 					ObjIntConsumer<T>, 
                                                    ObjLongConsumer<T>, 
                                                    ObjDoubleConsumer<T> 
                                                        
BiFunction<T,U,R> (T,U)->R 							ToIntBiFunction<T,U>, 
                                                    ToLongBiFunction<T,U>, 
                                                    ToDoubleBiFunction<T,U>                                                                                        
```

3 . 代码简化

```java
方法引用： 
List.sort((Apple a1, Apple a2) 
 -> a1.getWeight().compareTo(a2.getWeight()) 
);
简化 ： java.util.Comparator.comparing
	   inventory.sort(comparing(Apple::getWeight));

比较器链：
    inventory.sort(comparing(Apple::getWeight) 
 		.reversed()                               逆序
 		.thenComparing(Apple::getCountry));       当第一次比较后一致，进一步排序
谓词复合
    Predicate.negate();  非
             .and();     且
    		.or();		或      多个predicate复合，优先级按照从左到右的顺序
函数复合
    Function<Integer, Integer> f = x -> x + 1; 
	Function<Integer, Integer> g = x -> x * 2; 
	Function<Integer, Integer> h = f.andThen(g); 
	int result = h.apply(1);        g(f(x)) ---> 4
        
	Function<Integer, Integer> h = f.compose(g);
	int result = h.apply(1);	   f(g(x)) ---> 3
        
    可以用在工具类一系列方法的流水线形式调用上：
        Function<String, String> transformationPipeline 
				 = addHeader.andThen(Letter::checkSpelling) .andThen(Letter::addFooter);
```


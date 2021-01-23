package java8.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Test1 {

	
	/**
	 * 谓词结合
	 */
	public void wei() {
		List<Dish> menu = Arrays.asList( 
				 new Dish("pork", false, 800, Dish.Type.MEAT), 
				 new Dish("beef", false, 700, Dish.Type.MEAT), 
				 new Dish("french fries", true, 530, Dish.Type.OTHER), 
				 new Dish("rice", true, 350, Dish.Type.OTHER), 
				 new Dish("season fruit", true, 120, Dish.Type.OTHER), 
				 new Dish("pizza", true, 550, Dish.Type.OTHER), 
				 new Dish("chicken", false, 400, Dish.Type.MEAT), 
				 new Dish("prawns", false, 300, Dish.Type.FISH), 
				 new Dish("salmon", false, 450, Dish.Type.FISH) );
		
		Predicate<Dish> a = (Dish d) -> (d.getCalories() == 530);
		a = a.or(e -> e.getCalories() == 120);
		menu.stream().filter(a).forEach(e -> System.out.println(e.toString()));
	}
	
	
	public static void main(String[] args) {
		List<Dish> menu = Arrays.asList( 
				 new Dish("pork", false, 800, Dish.Type.MEAT), 
				 new Dish("beef", false, 700, Dish.Type.MEAT), 
				 new Dish("chicken", false, 400, Dish.Type.MEAT), 
				 new Dish("french fries", true, 530, Dish.Type.OTHER), 
				 new Dish("rice", true, 350, Dish.Type.OTHER), 
				 new Dish("season fruit", true, 120, Dish.Type.OTHER), 
				 new Dish("pizza", true, 550, Dish.Type.OTHER), 
				 new Dish("prawns", false, 300, Dish.Type.FISH), 
				 new Dish("salmon", false, 450, Dish.Type.FISH) );
		
		
		// 典型例子
		//流水线操作，针对源数据顺序加工，内部迭代，可以看作是数据库操作
		List<String> threeHighCaloricDishNames = 
				 menu.stream() 
				 .filter(d -> d.getCalories() > 300)
				 .map(Dish::getName)
				 .limit(3) 
				 .collect(Collectors.toList()); 
				System.out.println(threeHighCaloricDishNames);
				
				Stream<String> stream = threeHighCaloricDishNames.stream();
				stream.forEach(System.out::println);
				stream.forEach(System.out::println);
	}
	
	/**
	 * Collection与 Stream API 思想分析
	 * 先有集合，后有流；
	 * 集合代表全量数据，而流代表按需筛选的数据。也可以理解集合是空间数据，流是时间段内的数据。
	 * 集合可以遍历多次，流只能遍历一次，否则java.lang.IllegalStateException: stream has already been operated upon or closed
	 * 流操作内部迭代，在我看来最大的好处是不用自己管理并行问题；
	 */
	
	/**
	 * 流操作
	 * 1.中间操作，终端操作不触发就不执行。
	 * 2.短路优化
	 * 3.中间操作合并到一个循环，循环合并
	 * 
	 * 终端操作：返回值不是流
	 */
	
	
}

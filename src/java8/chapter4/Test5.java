package java8.chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Test5 {

	static List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
			new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
			new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
			new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));

	/**
	 * 实操：如筛选、切片、映射、查找、匹配和归约
	 */

	/**
	 * 筛选
	 */
	public void testFilter() {
		// 谓词筛选
		List<Dish> collect = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
		// 筛选不同数据，根据流所生成元素的hashCode和equals方法实现
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
		// 截断数据limit(), 跳过skip()
		numbers.stream().filter(i -> i % 2 == 0).limit(3).skip(2).collect(Collectors.toList());
	}

	/**
	 * 筛选
	 */
	public void testMap() {
		List<Integer> dishNameLengths = menu.stream().map(Dish::getName).map(String::length)
				.collect(Collectors.toList());
		String[] arrayOfWords = { "Goodbye", "World" };
		Stream<String> words = Arrays.stream(arrayOfWords);
		words.map(w -> w.split("")) // 返回一个个独立数组
				.flatMap(Arrays::stream).distinct().collect(Collectors.toList());

	}

	/**
	 * 映射
	 * 使用flatMap方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内容。所有使用map(Arrays::stream)时生成的单个流都被合并起来，即扁平化为一个流
	 * 给定列表[1, 2, 3]和列表[3, 4]，应 该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。
	 */
	public void test1() {
		List<Integer> one = Arrays.asList(1, 2, 3);
		List<Integer> two = Arrays.asList(4, 3);

		List<int[]> collect = one.stream().
				flatMap(j -> two.stream().map(e -> new int[] { e, j }))
				.filter(i -> (i[0] + i[1]) % 3 == 0)
				.collect(Collectors.toList());

		collect.forEach(e -> System.out.println(Arrays.toString(e)));
	}

	/**
	 * 查找 ，匹配
	 */
	public void test2() {
		boolean anyMatch = menu.stream().anyMatch(e -> e.getCalories() > 700);
		System.out.println(anyMatch);
		boolean allMatch = menu.stream().allMatch(e -> e.getCalories() > 700);
		System.out.println(allMatch);
		boolean noneMatch = menu.stream().noneMatch(e -> e.getCalories() > 700);
		System.out.println(noneMatch);
		
		// 找到第一个符合条件元素，并行流使用findFirst()
		Optional<Dish> findAny = menu.stream().findAny();
		findAny.ifPresent(e -> System.out.println(e.toString()));
	}
	
	/**
	 * 规约
	 */
	public void test3() {
		List<Integer> L1 = Arrays.asList(1,2,3,4,5,6);
		List<String> L2 = Arrays.asList("1","32");
		// 求和
		Integer reduce = L1.stream().reduce(0, (a,b) -> a + b);
		String reduce2 = L2.stream().reduce("", (a,b) -> a + b);
		System.out.println(reduce2);
		System.out.println(reduce);
		
		Integer max = L1.stream().reduce((a,b) -> a > b ? a : b).get();
	}
	
}

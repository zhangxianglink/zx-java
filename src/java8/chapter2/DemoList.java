package java8.chapter2;

import java.util.Arrays;
import java.util.List;

public class DemoList {
	
	public static void main(String[] args) {
		// java.util.Comparator
		List<Apple> apples = Arrays.asList(new Apple(80,"green"),
				 new Apple(155, "green"),
				 new Apple(120, "red"));
		apples.sort((Apple a , Apple b) -> {
			return b.getWeight().compareTo(a.getWeight());
		});
		
		apples.forEach(System.out::println);
		
		
		// java.lang.Runnable
		Thread thread = new Thread(() -> {
			System.out.println("runnable");
		});
		thread.start();
		
	}

}

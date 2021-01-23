package java8.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java8.chapter2.family.ApplePredicate;

public class One {
	
	/**
	 * filter green apple
	 */
	public static List<Apple> filterGreenApple(List<Apple> apples){
		ArrayList<Apple> arrayList = new ArrayList<>();
		for (Apple apple : arrayList) {
			if (apple.getColor().equals("green")) {
				arrayList.add(apple);
			}
		}
		return arrayList;
	}
	
	/**
	 * filter apple by color
	 */
	public static List<Apple> filterApple2(List<Apple> apples, String color){
		ArrayList<Apple> arrayList = new ArrayList<>();
		for (Apple apple : arrayList) {
			if (apple.getColor().equals(color)) {
				arrayList.add(apple);
			}
		}
		return arrayList;
	}
	
	/**
	 * filter apple by color or weight
	 */
	public static List<Apple> filterApple3(List<Apple> apples, String color,
			int weight, boolean flag){
		ArrayList<Apple> arrayList = new ArrayList<>();
		for (Apple apple : arrayList) {
			if ((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)) {
				arrayList.add(apple);
			}
		}
		return arrayList;
	}
	
	/**
	 * Don't Repeat Yourself， 行为参数化(策略模式)
	 */
	public static List<Apple> filterApple4(List<Apple> apples, ApplePredicate applePredicate){
		ArrayList<Apple> arrayList = new ArrayList<>();
		for (Apple apple : arrayList) {
			if (applePredicate.test(apple)) {
				arrayList.add(apple);
			}
		}
		return arrayList;
	}
	
	/**
	 * 匿名类简化行为参数
	 */
	public static void main(String[] args) {
		List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
				 new Apple(155, "green"),
				 new Apple(120, "red"));
		filterApple4(inventory, new ApplePredicate() {
			@Override
			public boolean test(Apple apple) {
				return apple.getColor().equals("red");
			}
		});
		
		List<Apple> filter = filter(inventory, (Apple apple) -> apple.getColor().equals("red"));
	}
	
	/**
	 * 行为参数化：lambda
	 */
	public static void filterApple5() {
		List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
				 new Apple(155, "green"),
				 new Apple(120, "red"));
		filterApple4(inventory, (Apple apple) -> {
			return apple.getColor().equals("red");
		});
	}
	
	/**
	 * 泛型：参数化类型
	 * T 是类型， ？是通配符
	 * ?是定义在引用变量上,T是类上或方法上
	 */
	public interface Predicate<T>{ 
		 boolean test(T t); 
	}
	
	public static<T> List<T> filter(List<T> apples, Predicate<T> predicate){
		List<T> arrayList = new ArrayList<>();
		for (T t : apples) {
			if (predicate.test(t)) {
				arrayList.add(t);
			}
		}
		return arrayList;
	}
	
	
	
	
	
	
	
}

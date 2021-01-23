package java8.chapter2.family;

import java8.chapter2.Apple;

public class AppleGreen implements ApplePredicate{

	@Override
	public boolean test(Apple apple) {
		return	apple.getColor().equals("green");
	}

}

package java8.chapter2.family;

import java8.chapter2.Apple;

public class AppleWeight implements ApplePredicate {

	@Override
	public boolean test(Apple apple) {
		return apple.getWeight() > 200;
	}

}

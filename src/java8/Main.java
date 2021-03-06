package java8;

import java8.annotation.Banana;
import java8.annotation.FruitInfoUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Comparator.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 5, 6, 82, 45, 789, 2, -1, 0, 9);
        List<Integer> collect = list.stream().sorted(Integer::compareTo).collect(Collectors.toList());
        List<Integer> collect1 = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect1);
        Collections.sort(list,new MyComparator());
        System.out.println(list);

        Banana banana = new Banana();
        FruitInfoUtil.getFruitInfo(banana.getClass());
    }
}

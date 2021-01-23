package java8.chapter6;

import java8.chapter4.Dish;
import java8.chapter4.Dish.Type;

import java.util.*;

import static java.util.stream.Collectors.*;

public class Test1 {


    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    /**
     * 以从Collectors 类提供的工厂方法（例如groupingBy）创建的收集器。它们主要提供了三大功能： 
     * 将流元素归约和汇总为一个值 
     * 元素分组
     * 元素分区
     */
    public static void main(String[] args) {

    }


    public void test1() {
        // 汇总
        long count = menu.stream().count();
        Long collect = menu.stream().collect(counting());
        System.out.println(count + " " + collect);

        Dish min = menu.stream().collect(minBy(Comparator.comparing(Dish::getCalories))).get();
        System.out.println("最小值：" + min.toString());
        Integer collect3 = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println("累加：" + collect3);
        IntSummaryStatistics collect2 = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("汇总：" + collect2);
        String collect4 = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(collect4);

        //以上操作均可使用reducing
        Integer collect5 = menu.stream().collect(reducing(0, Dish::getCalories, (a, b) -> a > b ? a : b));
        // reducing 优点 （相对于reduce） ：  对并行的支持力度，可变容器的规约
    }

    /**
     * 分组实例
     */

    public void test() {
        // 一层
        Map<Type, List<Dish>> collect = menu.stream().collect(groupingBy(Dish::getType));
        // 多层
        Map<Type, Map<String, List<Dish>>> map = menu.stream().collect(groupingBy(Dish::getType, groupingBy(e -> {
            if (e.getCalories() <= 400) return "low";
            else if (e.getCalories() <= 700) return "high";
            else return "fat";
        })));

        // 子集收集
        final Map<Type, Long> collect1 = menu.stream().collect(groupingBy(Dish::getType, counting()));
        Map<Type, Optional<Dish>> collect2 = menu.stream().collect(groupingBy(Dish::getType, minBy(Comparator.comparing(Dish::getCalories))));

        // 收集器结果转换
        Map<Type, Dish> collect3 = menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(minBy(Comparator.comparing(Dish::getCalories)), Optional::get)));

        // 收集器结合
        Map<Type, List<String>> collect4 = menu.stream().collect(groupingBy(Dish::getType, mapping(e -> e.getName(), toCollection(ArrayList::new))));

    }

    /**
     * 分区
     */
    public void test2(){

    }


}

package java8;

import java8.chapter2.Apple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MapTest {


    public static void main(String[] args) {
        test();
        System.out.println("------------");
        test2();
        System.out.println("------------");
        test3();
    }

    /**
     * Map 取值判断非空
     */
    public static void test() {
        HashMap<Object, Object> map = new HashMap<>(2);
        map.put("key","value");
        map.put("k2","v2");

        System.out.println(map.getOrDefault("k3","v3"));
    }

    /**
     * 当value是否存在
     */
    public static void test2() {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        ArrayList<String> list = map.computeIfAbsent("k1", k -> new ArrayList<>());
        list.add("a");
        list.add("b");
        list.add("c");
        ArrayList<String> strings = map.computeIfPresent("k1", (k, v) ->
                (ArrayList) v.stream().filter(e -> !e.equals("b")).collect(Collectors.toList())
        );
        System.out.println(strings);
    }

    /**
     * 叠加处理
     */
    public static void test3(){
        HashMap<String, Integer> map = new HashMap<>();
        List<Apple> apples = new ArrayList<>(8);
        apples.add(new Apple(100,"red"));
        apples.add(new Apple(100,"green"));
        apples.add(new Apple(100,"green"));
        apples.add(new Apple(100,"red"));
        apples.add(new Apple(100,"red"));
        apples.add(new Apple(100,"green"));
        apples.add(new Apple(100,"red"));
        apples.add(new Apple(100,"red"));

        apples.stream().forEach(e ->
                map.merge(e.getColor(),e.getWeight(),(n,o) -> o + n)
        );

        System.out.println(map.toString());


    }
}

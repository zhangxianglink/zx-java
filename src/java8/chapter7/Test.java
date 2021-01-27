package java8.chapter7;

import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @Author zx
 * @Date 2021/1/26
 *
 **/
public class Test {


    /**
     * 并行流内部使用了默认的ForkJoinPool（7.2节会进一步讲到分支/合并框架），它默认的
     * 线程数量就是你的处理器数量  , System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12");
     * 这是一个全局设置，因此它将影响代码中所有的并行流。
     * @param n
     * @return
     */
    public static int parallelSum(int n){
        // 并行，串行 同时使用 最后一个生效
        return Stream.iterate(0, i -> i+1).limit(n).sequential().parallel().collect(Collectors.reducing(Integer::sum)).get();
    }

    /**
     * 并行模式注意事项：
     * 原始数据是否利于分解：选择合适的数据结构，减少数据开装箱操作
     * 避免共享可变状态：选择原子操作，得到正确数据
     * 某些依赖顺序的操作，无需并行，findFirst, findAny 就没这个问题
     * 数据量小无需并行
     * ArrayList 极佳
     * LinkedList 差
     * IntStream.range 极佳
     * Stream.iterate 差
     * HashSet 好
     * TreeSet 好
     */
    public static void main(String[] args) {
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(LongStream.rangeClosed(1,100000000).reduce(Long::sum).getAsLong());
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(LongStream.rangeClosed(1,100000000).parallel().reduce(Long::sum).getAsLong());
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(testForkJoinSum(100000000));
    }

    public static long testForkJoinSum(long n){
        long[] longs = LongStream.rangeClosed(1, n).toArray();
        RecursiveTask<Long> task = new ForkJoinSumCalculator(longs);
        // ForkJoinPool 建议单例
        return new ForkJoinPool().invoke(task);
    }

    /**
     * Spliterator是Java 8中加入的另一个新接口；这个名字代表“可分迭代器”（splitable
     * iterator）。和Iterator一样，Spliterator也用于遍历数据源中的元素，但它是为了并行执行
     * 而设计的。
     */
}

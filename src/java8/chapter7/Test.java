package java8.chapter7;

import java.util.stream.Collectors;
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
}

package java8.chapter7;

import java.util.concurrent.RecursiveTask;

/**
 * @Author zx
 * @Date 2021/1/27
 * 分支，合并
 **/
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final  long[] numbers;
    private final  int start;
    private final  int end;

    public static final long THERSHOLD = 10000000;

    private ForkJoinSumCalculator(long[] numbers, int start, int end){
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    public ForkJoinSumCalculator(long[] numbers){
        this(numbers,0,numbers.length);
    }
    
    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THERSHOLD){
            return computeSequentially();
        }
        ForkJoinSumCalculator left = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        left.fork();

        ForkJoinSumCalculator right = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long compute = right.compute();//允许细分
        Long join = left.join();

        return join + compute;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}

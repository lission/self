package daytest.Calculator;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/13 11:52
 */
public class ForkJoinPoolCalculator implements Calculator {

    private ForkJoinPool pool;

    public ForkJoinPoolCalculator() {
        //可以使用公用的线程池,也可以使用new ForkJoinPool()创建自己的线程池
        this.pool = ForkJoinPool.commonPool();
    }

    private static class SumTask extends RecursiveTask<Long>{
        private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        /*
        * 此方法为ForkJoin的核心方法，对任务进行拆分，拆分的好坏决定了效率的高低
        * */
        @Override
        protected Long compute() {
            //当需要计算的数字个数小于6时，直接采用for loop方式计算结果
            if (to-from<6){
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
            }else {
                //否则将任务一分为二，递归拆分
                int middle = (from+to)/2;
                SumTask left = new SumTask(numbers,from,middle);
                SumTask right = new SumTask(numbers,middle+1,to);
                left.fork();
                right.fork();
                return left.join()+right.join();
            }
        }
    }

    @Override
    public long sumUp(long[] numbers) {
        long sum = pool.invoke(new SumTask(numbers,0,numbers.length-1));
        pool.shutdown();
        return sum;
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1,10000000).toArray();
        Instant start = Instant.now();
        Calculator calculator = new ForkJoinPoolCalculator();
        long sum = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("耗时："+Duration.between(start,end).toMillis());
        System.out.println("结果为"+sum);
    }
}

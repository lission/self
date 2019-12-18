package daytest.Calculator;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.LongStream;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/13 11:52
 */
public class ExecutorServiceCalculator implements Calculator {

    private int parallism;
    private ExecutorService pool;

    public ExecutorServiceCalculator() {
        this.parallism = Runtime.getRuntime().availableProcessors();//cpu核心数 默认使用cpu核心数
        this.pool = Executors.newFixedThreadPool(parallism);
    }

    //处理计算任务的线程
    private static class SumTask implements Callable<Long>{
        private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        @Override
        public Long call() {
            long total = 0;
            for (int i=from;i<=to;i++){
                total+=numbers[i];
            }
            return total;
        }
    }
    @Override
    public long sumUp(long[] numbers) {
        long sum =0;
        List<Future<Long>> results = new ArrayList<>();
        /*
        * 把任务分解为n份，交给n个线程处理，把每一份都交给一个SumTask进程进行处理
        * */
        int part = numbers.length/parallism;
        for (int i=0;i<parallism;i++){
            int from = i*part;//开始位置
            int to = (i==parallism-1)?numbers.length-1:(i+1)*part-1;//结束位置
            //交给线程池计算
            results.add(pool.submit(new SumTask(numbers,from,to)));
        }
        /*
        * 把每个线程结果相加，得到最终结果get()方法是阻塞的
        * 优化方案：可以采用CompletableFuture 优化（jdk1.8新特性）
        * */
        for (Future<Long> f:results){
            try {
                sum+=f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1,10000000).toArray();
        Instant start = Instant.now();
        Calculator calculator = new ExecutorServiceCalculator();
        long sum = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("耗时："+Duration.between(start,end).toMillis());
        System.out.println("结果为"+sum);
    }
}

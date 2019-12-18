package daytest.Calculator;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/16 11:19
 */
public class StreamCalculator {
    /** 采用并行流 */
    public static void main(String[] args) {
        Instant start = Instant.now();
        long sum = LongStream.rangeClosed(0,10000000L).parallel().reduce(0,Long::sum);
        Instant end = Instant.now();
        System.out.println("耗时："+ Duration.between(start,end).toMillis());
        System.out.println("结果为"+sum);
    }
}

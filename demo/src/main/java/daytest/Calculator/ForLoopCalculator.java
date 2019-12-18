package daytest.Calculator;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/13 11:52
 */
public class ForLoopCalculator implements Calculator {

    @Override
    public long sumUp(long[] numbers) {
        long sum =0;
        for (long l:numbers){
//            sum=sum+l;
            sum+=l;
        }
        return sum;
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1,10000000).toArray();
        Instant start = Instant.now();
        Calculator calculator = new ForLoopCalculator();
        long sum = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("耗时："+Duration.between(start,end).toMillis());
        System.out.println("结果为"+sum);
    }
}

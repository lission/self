package daytest;

import com.testTemp.Student;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/6 15:41
 */
public class LambdaTest {
    public static void main(String[] args) {
        /**
         * Lambda表达式，使用()->{}替换匿名类
         * */
        /*Thread t1 = new Thread(()->{System.out.println("测试");});
        t1.start();*/
        Predicate<Integer> predicate = x->x<175;
        Student student = new Student("小A",20,189);
        System.out.println("小A的身高小于175吗？"+predicate.test(student.getTall()));

        Consumer<Student> consumer = System.out::println;
        consumer.accept(student);

        Function<Student,String> function = Student::getName;
        System.out.println("我就是。。。"+function.apply(student));

        Supplier<Integer> supplier = ()->Integer.valueOf(BigDecimal.TEN.toString());
        System.out.println(supplier.get());

        UnaryOperator<Integer> unaryOperator = x->x+1;
        System.out.println(unaryOperator.apply(student.getAge()));

        BinaryOperator<Integer> binaryOperator =(x,y)->x+y;
        System.out.println(binaryOperator.apply(1,2));

        /*
        * 使用() -> {} 替代匿名类
        * */
        test(()->"函数式演示调用");
    }

    /**
     * 演示自定义函数式接口使用
     *
     * */
    public static void test(Worker worker){
        System.out.println(worker.say());
    }

    /**
     * 实际意义上的函数式接口
     * */
    public interface Worker{
        String say();
    }

    @Test
    public void test1(){
        List<Integer> integers = Arrays.asList(4, 5, 6,1, 2, 3,7, 8,8,9,10);
//        integers.sort((Integer::compareTo));
        integers.sort(Comparator.naturalOrder());
//        integers.sort(Comparator.reverseOrder());

        integers.forEach(System.out::println);
        integers.stream().sorted();
        integers.forEach(System.out::println);

    }

    @Test
    public void collectAndFilterTest(){
        List<Integer> integers = Stream.of(6,2,9,4,8,1).collect(Collectors.toList());
        //删选大于5的值
        List<Integer> list = integers.stream().filter(x->x>5).collect(Collectors.toList());
        list.forEach(System.out::println);
        //转换为String
        List<String> strings = list.stream().map(integer -> String.valueOf(integer)).collect(Collectors.toList());
        strings.forEach(System.out::println);
        System.out.println("————————————————————————————");
        List<Integer> list1 = Stream.of(integers,list).flatMap(list2 ->list2.stream()).collect(Collectors.toList());
        list1.forEach(System.out::println);

        Optional<Integer> max = list1.stream().max(Comparator.comparing(integer -> integer));
        Optional<Integer> min = list1.stream().min(Comparator.comparing(integer -> integer));
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(max.get());
        System.out.println(min.get());

        long sum = list1.stream().count();
        long filterSum = list1.stream().filter(y -> y < 5).count();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println(sum);
        System.out.println(filterSum);
        Integer reduce = Stream.of(1,2,3,4).reduce(1,(x,y) -> x+y);
        System.out.println(reduce);
    }

    @Test
    public void mathTest(){
        Integer[] integers = {1,2,3,4};
        List<Integer> list = Stream.of(integers).collect(Collectors.toList());
        List<Integer> list1 = Arrays.asList(integers);
        list.stream().map(x -> x/2).forEach(System.out::println);
        list1.stream().map(x -> x>>1).forEach(System.out::println);
    }
}

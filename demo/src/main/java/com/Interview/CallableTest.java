package com.Interview;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * @author lisong@mimidai.com
 * @date 2019/11/19 14:53
 */
public class CallableTest {
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50),new ThreadPoolExecutor.AbortPolicy());
    /**
     * 有三个等价方法，abc，执行效率不确定，请写一段代码，要求每次使用最少时间返回结果
     * */
    private String test = null;
   @Test
    public void test(){
       //模拟接口，main线程
       CountDownLatch count = new CountDownLatch(1);
        for (int i=0;i<3;i++){
            executor.execute(new CallFunc(i,count));
        }
        try {
            count.await();
            System.out.println("主线程唤醒");
            while (true){
                if (test != null){
                    System.out.println("---------------"+test);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class CallFunc extends Thread{
        private int i;
        private CountDownLatch count;
        public CallFunc() {
        }

        public CallFunc(int i, CountDownLatch count) {
            this.i = i;
            this.count = count;
        }

        @Override
        public void run() {
            System.out.println(currentThread().getName());
            System.out.println("!!!!!!!!"+i);
            if (i==0){
                test = a(count);
            }else if (i==1){
                test = b(count);
            }else if (i==2){
                test = c(count);
            }
        }
    }
    public String a(CountDownLatch count){
        System.out.println("a线程:");

        /*
        * 业务逻辑
        * */
        try {
            Long ran = randomLong();
            Thread.sleep(ran);
            System.out.println("a唤醒线程:"+ran);
            count.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "a";
    }
    public String b(CountDownLatch count){
        System.out.println("b线程:");

        /*
         * 业务逻辑
         * */
        try {
            Long ran = randomLong();
            Thread.sleep(ran);
            System.out.println("b唤醒线程:"+ran);
            count.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "b";
    }

    public String c(CountDownLatch count){
        System.out.println("c线程:");

        /*
         * 业务逻辑
         * */
        try {
            Long ran = randomLong();
            Thread.sleep(ran);
            System.out.println("c唤醒线程:"+ran);
            count.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "c";
    }

    private Long randomLong(){
        Random random = new Random();
        return Long.valueOf(random.nextInt(1000));
    }


}

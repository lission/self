package com.Interview;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lisong@mimidai.com
 * @date 2019/11/19 14:53
 */
public class CallableTestSynchrinize {
    /**
     * 有三个等价方法，abc，执行效率不确定，请写一段代码，要求每次使用最少时间返回结果
     * 不使用countdownLatch，通过synchronized自己实现锁计数
     *
     * */
    private String test = null;
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50),new ThreadPoolExecutor.AbortPolicy());
    @Test
    public void forTest(){
        for (int i = 0;i<50;i++){
            System.out.println("+++++++++++++++"+i+"+++++++++++++++++");
            test();
        }

    }

   @Test
    public void test(){
       Integer integer = 1;
        for (int i=0;i<3;i++){
            executor.execute(new CallFunc(i));
        }
        try {
            System.out.println("主线程唤醒");
            while (true){
                if (this.test != null){
                    System.out.println("---------------"+test);
                    System.out.println(executor.getActiveCount());
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static void countDown(Integer integer){
        if (integer >0){
            integer--;
        }
    }

    class CallFunc extends Thread{
        private int i;
        public CallFunc() {
        }

        public CallFunc(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(currentThread().getName());
            System.out.println("!!!!!!!!"+i);
            if (i==0){
                test = a();
            }else if (i==1){
                test = b();
            }else if (i==2){
                test = c();
            }
        }
    }
    public String a(){
        System.out.println("a线程:");

        /*
        * 业务逻辑
        * */
        try {
            Long ran = randomLong();
            Thread.sleep(ran);
            System.out.println("a唤醒线程:"+ran);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "a";
    }
    public String b(){
        System.out.println("b线程:");

        /*
         * 业务逻辑
         * */
        try {
            Long ran = randomLong();
            Thread.sleep(ran);
            System.out.println("b唤醒线程:"+ran);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "b";
    }

    public String c(){
        System.out.println("c线程:");

        /*
         * 业务逻辑
         * */
        try {
            Long ran = randomLong();
            Thread.sleep(ran);
            System.out.println("c唤醒线程:"+ran);
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

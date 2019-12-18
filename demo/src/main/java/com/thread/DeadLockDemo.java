package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lisong@mimidai.com
 * @date 2019/11/21 11:25 实现一段死锁代码
 */
public class DeadLockDemo {
    private  String str1 = "str1";
    private  String str2 = "str2";

    private ReentrantLock lock =new ReentrantLock();
    private ReentrantLock lock1 =new ReentrantLock();

    public static void main(String[] args) {
        new DeadLockDemo().synTest();
        System.out.println("main线程");
        Integer i=0;
        try {
            while (true){
                Thread.sleep(1000);
                System.out.println(i++);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void lockTest(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        lock.lock();
                        System.out.println(Thread.currentThread().getName()+
                            "锁住lock");
                        Thread.sleep(1000);
                        lock1.lock();
                        System.out.println(Thread.currentThread().getName()+
                            "lock1");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        lock1.lock();
                        System.out.println(Thread.currentThread().getName()+
                            "锁住lock1");
                        Thread.sleep(1000);
                        lock.lock();
                        System.out.println(Thread.currentThread().getName()+
                            "lock");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
        new Thread(runnable1).start();
    }


    public void synTest(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
//                    while (true){
                        synchronized (str1){
                            System.out.println(Thread.currentThread().getName()+
                                "锁住str1");
                            Thread.sleep(1000);
                            synchronized (str2){
                                System.out.println(Thread.currentThread().getName()+
                                    "锁住str2");
                                Thread.sleep(1000);

                            }
                        }
//                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                try {
//                    while (true){
                        synchronized (str2){
                            System.out.println(Thread.currentThread().getName()+
                                "锁住str2");
                            Thread.sleep(1000);
                            synchronized (str1){
                                System.out.println(Thread.currentThread().getName()+
                                    "锁住str1");
                                Thread.sleep(1000);

                            }
//                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
        new Thread(runnable1).start();
    }

}

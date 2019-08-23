package com.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lisong@mimidai.com
 * @date 2019/4/1 10:59
 */
public class MyService {
    private Lock lock = new ReentrantLock();
    //默认非公平锁
    //new ReentrantLock(true) 公平锁
    //new ReentrantLock(false) 非公平锁
    private Condition condition = lock.newCondition();
    public void testMyThread(){
        try {
            //加锁
            lock.lock();
            //等待
            condition.await();
            //signal 方法唤醒
            condition.signal();
            for (int i = 0;i<5;i++){
                System.out.println("ThreadName="+Thread.currentThread().getName()+(" "+(i+1)));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void semaphoreTest(String s){
        Semaphore semaphore = new Semaphore(2);
        try {
            //申请许可
            semaphore.acquire();
            //业务逻辑
            System.out.println(s);

        }catch (Exception e){

        }finally {
            //释放许可
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        MyService service = new MyService();
//        service.testMyThread();
        for (int i =0;i<10;i++){
            service.semaphoreTest(String.valueOf(i));

        }
    }
}

package com.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author lisong@mimidai.com
 * @date 2019/4/9 20:47
 */
public class CyclicTest {

    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N);
        for (int i = 0;i<N;i++){
            new Writer(cyclicBarrier).start();
        }
    }

    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run(){

            //模拟数据写入
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName()+"数据写入完成");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕");
        }
    }
}

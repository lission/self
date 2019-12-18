package daytest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.SynchronousQueue;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/9 19:05
 */
public class LambdaThreadTest {
    private String str1 = "str1";
    private String str2 = "str2";

    public static void main(String[] args) {
    //
        LambdaThreadTest lambdaThreadTest = new LambdaThreadTest();
        lambdaThreadTest.test();
    }

    public void test(){
        Thread thread1 =
            new Thread() {
              @Override
              public void run() {
                try {
                  synchronized (str1) {
                    Thread.sleep(1000);
                        System.out.println("thread1 获取str1锁");
                    synchronized (str2) {
                        System.out.println("thread1 获取str2锁");
                    }
                  }

                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            };
        Thread thread2 =
            new Thread() {
                @Override
                public void run() {
                    try {
                        synchronized (str2) {
                            Thread.sleep(1000);
                            System.out.println("thread2 获取str2锁");
                            synchronized (str1) {
                                System.out.println("thread2 获取str1锁");
                            }
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        thread1.start();
        thread2.start();
    }
    @Test
    public void test1(){
        int[] arr ={1,2,3,4};
        System.out.println(arr.length);
        resize1(arr);
        System.out.println(arr.length);
    }
    public int[] resize(int[] arr){
        int[] arrNew = new int[arr.length*2];
        System.arraycopy(arr,0,arrNew,0,arr.length);
        return arrNew;
    }

    public void resize1(int[] arr){
        int[] arrNew = new int[arr.length*2];
        System.arraycopy(arr,0,arrNew,0,arr.length);
    }
}

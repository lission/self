package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author lisong@mimidai.com
 * @date 2019/3/26 14:55
 */
public class MyCallableTest implements Callable {

    private String taskId;
    private MyCallableTest(String s){
        this.taskId =s;
    }
    @Override
    public Object call(){
        return taskId + "call test";
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future> list = new ArrayList<>();
        for (int i =0;i<10;i++){
            Callable c = new MyCallableTest(i+" ");
            Future f = pool.submit(c);
            list.add(f);
        }
        for (Future f:list){
            try {
                System.out.println(f.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

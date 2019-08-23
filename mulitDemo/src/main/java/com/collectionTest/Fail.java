package com.collectionTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lisong@mimidai.com
 * @date 2019/6/10 17:18
 */

/**
 * fail-fast事件示例
 * fail-fast机制是java集合（Collection）的错误机制，当多个线程对同一个集合内容进行操作时，可能产生fail-fast事件
 * */
public class Fail {
    public static void main(String[] args) {
        new ThreadOne().start();
        new ThreadTwo().start();
    }
    private static List<String> list = new ArrayList<>();

    private static void printAll(List l){
/*        for (int i = 0;i<l.size();i++){
            System.out.println(l.get(i)+",");
        }*/
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next()+",");
        }
    }

    private static class ThreadOne extends Thread{
        @Override
        public void run() {
            int i = 0;
            while (i<100){
                list.add(String.valueOf(i));
                printAll(list);
                i++;
            }
        }
    }
    private static class ThreadTwo extends Thread{
        @Override
        public void run() {
            int i = 100;
            while (i<200){
                list.add(String.valueOf(i));
                printAll(list);
                i++;
            }
        }
    }
}

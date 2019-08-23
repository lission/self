package com.collectionTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lisong@mimidai.com
 * @date 2019/6/5 16:47
 * Collection 一个接口，高度抽象的集合，包含集合基本操作和属性
 */
public class CollectionTest {

    @Test
    public void enumTest1(){
        WeekDay w = WeekDay.Fri;
        System.out.println(w);
    }

    /**
     *  Collection 包含了 List 和 Set 两大分支
     * */

    /**
     * List实现类
     * ArrayList、Vector、LinkList
     *
     * */

    /**
     * ArrayList 三种遍历方式效率比较
     * 测试程序
     * https://www.cnblogs.com/skywang12345/p/3308556.html#a4
     * */
    class ArrayListRandomAccessTest{

        /**
         * 1、通过遍历器Iterator遍历
         * */
        public void iteratorThroughIterator(List list){
            long startTime;
            long endTime;
            startTime = System.currentTimeMillis();
            Iterator iterator = list.iterator();
            for (int i=0; i<10000; i++){
                while (iterator.hasNext()){
                    iterator.next();
                }
            }

            endTime = System.currentTimeMillis();
            long interval = endTime - startTime;
            System.out.println("通过遍历器Iterator遍历,耗时："+interval);
        }
        /**
         * 2、随机访问，通过索引值遍历
         * */
        public void iteratorThroughRandomAccess(List list) {
            long startTime;
            long endTime;
            startTime = System.currentTimeMillis();
            for (int i=0; i<10000; i++){
                for (int j = 0;j<list.size();j++){
                    list.get(j);
                }
            }

            endTime = System.currentTimeMillis();
            long interval = endTime - startTime;
            System.out.println("随机访问，通过索引值遍历,耗时："+interval);
        }
        /**
         * 3、通过for循环遍历
         * */
        public void iteratorThroughFor(List list){
            long startTime;
            long endTime;
            startTime = System.currentTimeMillis();
            for (int i=0; i<10000; i++){
                for (Object object:list){
                }
            }

            endTime = System.currentTimeMillis();
            long interval = endTime - startTime;
            System.out.println("通过for循环遍历,耗时："+interval);
        }
    }


    @Test
    public void test(){
        List list = new ArrayList();
        ArrayListRandomAccessTest listRandomAccessTest = new ArrayListRandomAccessTest();
        for (int i=0; i<1000000; i++){
            list.add(i);
        }

        /**
         * 增加运行次数，明确三种方式执行时间
         * */
        listRandomAccessTest.iteratorThroughIterator(list);
        listRandomAccessTest.iteratorThroughRandomAccess(list);
        listRandomAccessTest.iteratorThroughFor(list);

    }

    @Test
    public void enumTest(){
        WeekDay w = WeekDay.Fri;
        System.out.println(w);
    }

    @Test
    public void moneyTest(){
        for (Money m:Money.values()){
            System.out.println(m);
            System.out.println(m.toString());
            switch (m){
                case ONE:
                    System.out.println("1元");
                    break;
                case FIVE:
                    System.out.println("5元");
                    break;
                case TEN:
                    System.out.println("10元");
                    break;
                case TWENTY:
                    System.out.println("20元");
                    break;
                case FIFTY:
                    System.out.println("50元");
                    break;
                case HANDRUD:
                    System.out.println("100元");
                    break;
                default:
                    System.out.println("错误");
            }
        }
    }
}

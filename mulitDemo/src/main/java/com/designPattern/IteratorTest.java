package com.designPattern;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lisong@mimidai.com
 * @date 2019/6/3 10:57
 * 迭代器模式
 * 主要角色：抽象聚合角色、具体聚合角色、抽象迭代器角色、具体迭代器角色
 *
 */
public class IteratorTest {

    /**
     * 抽象聚合角色
     * */
    interface Aggregate{
        void add(Object obj);
        void remove(Object obj);
        Iterator getIterator();
    }

    /**
     * 具体聚合角色
     * */
    class ConcreteAggregate implements Aggregate{
        private List<Object> list = new ArrayList<>();
        @Override
        public void add(Object obj) {
            list.add(obj);
        }

        @Override
        public void remove(Object obj) {
            list.remove(obj);
        }

        @Override
        public Iterator getIterator() {
            return new ConcreteIterator(list);
        }
    }

    /**
     * 抽象迭代器角色
     * */
    interface Iterator{
        Object first();
        Object next();
        boolean hasNext();
    }

    /**
     * 具体迭代器角色
     * */
    class ConcreteIterator implements Iterator{
        private List<Object> list;
        private int index = -1;
        public ConcreteIterator(List<Object> list) {
            this.list = list;
        }

        @Override
        public Object first() {
            return list.get(0);
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                return list.get(++index);
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            if (index < list.size()-1){
                return true;
            }else {
                return false;
            }
        }
    }

    @Test
    public void test(){
        Aggregate aggregate =new ConcreteAggregate();
        aggregate.add("中山");
        aggregate.add("南山");
        aggregate.add("北山");
        Iterator iterator = aggregate.getIterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(obj.toString());
        }
    }
}

package com.designPattern;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/30 17:30
 * 观察者模式
 * 主要角色：抽象主题角色、具体主题角色、抽象观察者角色、具体观察者角色
 */
public class ObserveTest {

    /**
     * 抽象观察者角色
     * 包含一个更新自己的抽象方法，当收到具体主题更新通知时，被调用
     * */
    interface Observer{
        void response();
    }

    /**
     * 具体观察者1
     *
     * */
    class ConcreteObserver1 implements Observer{
        @Override
        public void response() {
            System.out.println("具体观察者1,gengxin");
        }
    }

    /**
     * 具体观察者2
     *
     * */
    class ConcreteObserver2 implements Observer{
        @Override
        public void response() {
            System.out.println("具体观察者2,gengxin");
        }
    }

    /**
     * 抽象主题角色
     * */
    abstract class Subject{
        protected List<Observer> observerList = new ArrayList<>();
        public void add(Observer observer){
            observerList.add(observer);
        }
        public void remove(Observer observer){
            observerList.remove(observer);
        }

        public abstract void notifyAllObserver();
    }

    /**
     * 具体目标
     * */
    class ConcreteSubject extends Subject{
        @Override
        public void notifyAllObserver() {
            System.out.println("具体目标发生改变...");

            for (Observer observer:super.observerList){
                observer.response();
            }
        }
    }

    @Test
    public void test(){
        Subject subject=new ConcreteSubject();
        Observer obs1=new ConcreteObserver1();
        Observer obs2=new ConcreteObserver2();
        subject.add(obs1);
        subject.add(obs2);
        subject.notifyAllObserver();
    }
    @Test
    public void test1(){
    }
}

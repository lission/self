package com.designPattern;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lisong@mimidai.com
 * @date 2019/6/2 15:43
 * 中介者模式
 * 主要角色：抽象中介者，具体中介者、抽象同事类角色、具体同事类角色
 */
public class MediatorTest {

    /**
     * 抽象中介者角色，中介者接口，提供同事注册及转发同事信息的抽象方法
     * */

    abstract class Mediator{
        public abstract void redister(Colleague colleague);
        public abstract void relay(Colleague cl);
    }

    /**
     * 抽象同事类角色
     * 定义同事类接口，保存中介者对象，提供同事对象交互的抽象方法
     * */
    abstract class Colleague{
        protected Mediator mediator;

        public void setMediator(Mediator mediator) {
            this.mediator = mediator;
        }
        public abstract void receive();
        public abstract void send();
    }

    /**
     * 具体中介者角色
     *
     * */

    class ConcreteMediator extends Mediator{
        private List<Colleague> list = new ArrayList<>();
        @Override
        public void redister(Colleague colleague) {
            if (null == colleague){
                throw new NullPointerException();
            }
            if (!list.contains(colleague)){
                list.add(colleague);
                colleague.setMediator(this);
            }
        }

        @Override
        public void relay(Colleague cl) {
            for (Colleague colleague:list){
                if (!cl.equals(colleague)){
                    colleague.receive();
                }
            }
        }
    }

    /**
     * 具体同事类1
     * */
    class ConcreteColleague1 extends Colleague{
        @Override
        public void receive() {
            System.out.println("具体同事类1收到请求。");
        }

        @Override
        public void send() {
            System.out.println("具体同事类1发出请求。");
            mediator.relay(this);
        }
    }

    /**
     * 具体同事类2
     * */
    class ConcreteColleague2 extends Colleague{
        @Override
        public void receive() {
            System.out.println("具体同事类2收到请求。");
        }

        @Override
        public void send() {
            System.out.println("具体同事类2发出请求。");
            mediator.relay(this);
        }
    }

    @Test
    public void test(){
        Mediator mediator = new ConcreteMediator();
        Colleague colleague1 = new ConcreteColleague1();
        Colleague colleague2 = new ConcreteColleague2();
        mediator.redister(colleague1);
        mediator.redister(colleague2);
        colleague1.send();
    }
}

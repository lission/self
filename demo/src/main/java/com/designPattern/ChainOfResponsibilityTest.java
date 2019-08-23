package com.designPattern;

import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/30 11:11
 * 责任链模式
 * 主要角色：抽象处理者角色、具体处理者角色、客户类角色
 *
 */
public class ChainOfResponsibilityTest {

    /**
     * 抽象处理者
     * */

    abstract class Handler{
        private Handler next;

        public Handler getNext() {
            return next;
        }

        public void setNext(Handler next) {
            this.next = next;
        }

        abstract void handleRequest(String request);
    }

    /**
     * 具体处理者1
     * */
    class ConcreteHandle1 extends Handler{
        public ConcreteHandle1() {
            super.setNext(new ConcreteHandle2());
        }

        @Override
        void handleRequest(String request) {
            if ("one".equals(request)) {
                System.out.println("具体处理者1");
            }else {
                if (getNext() != null){
                    getNext().handleRequest(request);
                }else {
                    System.out.println("没人处理该请求");
                }
            }

        }
    }

    /**
     * 具体处理者1
     * */
    class ConcreteHandle2 extends Handler{
        @Override
        void handleRequest(String request) {
            if ("two".equals(request)) {
                System.out.println("具体处理者2");
            }else {
                if (getNext() != null){
                    getNext().handleRequest(request);
                }else {
                    System.out.println("没人处理该请求");
                }
            }

        }
    }

    @Test
    public void test(){
        Handler handler1 = new ConcreteHandle1();
        handler1.setNext(null);
        handler1.handleRequest("two");
    }
}

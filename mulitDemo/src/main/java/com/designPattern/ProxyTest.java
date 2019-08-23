package com.designPattern;

import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/15 11:13
 * 代理模式结构原型
 */
public class ProxyTest {

    public static void main(String[] args) {
    }
    @Test
    public void clientTest(){
        Proxy proxy = new Proxy();
        proxy.Request();
    }

    /**
     * 抽象主题（目标对象的抽象）
     * 包含访问主题的抽象方法
     * */
    interface Subject{
        void Request();
    }

    /**
     * 真实主题（目标对象）
     * 实现抽象主题中的具体业务
     * */
    class RealSubject implements Subject{
        @Override
        public void Request() {
            System.out.println("访问真实主题……");
        }

    }
    /**
     * 代理
     * 访问对象与目标对象之间的中介，可以通过代理访问目标对象或对目标对象做功能扩展
     * */
    class Proxy implements Subject{
        private RealSubject realSubject;
        @Override
        public void Request() {
            if (realSubject == null){
                realSubject = new RealSubject();
            }
            preRequest();
            //预处理
            realSubject.Request();
            //后续处理
            postRequest();
        }

        public void preRequest(){
            System.out.println("访问真实主题前预处理……");
        }

        public void postRequest(){
            System.out.println("访问真实主题后续处理……");
        }
    }
}

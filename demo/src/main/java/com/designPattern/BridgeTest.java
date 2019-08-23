package com.designPattern;

import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/16 14:27
 * 桥接模式（Bridge）
 * 主要角色：抽象化角色、扩展抽象化角色、实现化角色、具体实现化角色
 * 抽象化角色：抽象类，包含对实现化角色的引用
 * 扩展抽象化角色：抽象化角色的子类，实现抽象化角色的业务方法，通过组合关系调用实现化角色中的业务方法
 * 实现化角色：接口，供扩展抽象化角色调用
 * 具体实现化角色：给出实现化角色的具体实现
 */
public class BridgeTest {

    /**
     * 实现化角色（Implementor）
     * */
    interface Implementor{
        void opreationImp();
    }

    /**
     * 具体实现化角色A
     * */
    class ConcreteImplementorA implements Implementor{
        @Override
        public void opreationImp() {
            System.out.println("具体实现化角色A");
        }
    }
    /**
     * 抽象化角色
     * */
    abstract class Abstraction{
        protected Implementor implementor;
        protected Abstraction(Implementor implementor){
            this.implementor = implementor;
        }
        public abstract void operation();

    }
    /**
     * 扩展抽象化角色
     * */
    class RefinedAbstraction extends Abstraction{
        protected RefinedAbstraction(Implementor implementor) {
            super(implementor);
        }

        @Override
        public void operation() {
            implementor.opreationImp();
        }
    }

    @Test
    public void test(){
        Implementor implementor = new ConcreteImplementorA();
        Abstraction abstraction = new RefinedAbstraction(implementor);
        abstraction.operation();
    }
}

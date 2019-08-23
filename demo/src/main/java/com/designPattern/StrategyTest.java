package com.designPattern;

import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/28 11:17
 * 策略模式
 * 主要角色：抽象策略类、具体策略类、环境类
 *
 */
public class StrategyTest {

    /**
     * 抽象策略类
     * 定义一个公共接口，各种不同算法以不同方式实现这个接口，环境角色使用这个接口调用不同算法
     * */

    interface Strategy{
        void strategyMethod();
    }

    /**
     * 具体策略类1
     * 实现抽象策略定义的接口，提供具体算法实现
     * */

    class ConcreteStrategy1 implements Strategy{
        @Override
        public void strategyMethod() {
            System.out.println("具体策略类1,具体算法实现");
        }
    }

    /**
     * 具体策略类2
     * 实现抽象策略定义的接口，提供具体算法实现
     * */

    class ConcreteStrategy2 implements Strategy{
        @Override
        public void strategyMethod() {
            System.out.println("具体策略类2,具体算法实现");
        }
    }

    /**
     * 环境类
     * 持有一个策略类的引用，最终给客户端调用
     * */
    class Context{
        private Strategy strategy;

        public Strategy getStrategy() {
            return strategy;
        }

        public void setStrategy(Strategy strategy) {
            this.strategy = strategy;
        }

        public void strategyMethod(){
            strategy.strategyMethod();
        }
    }

    @Test
    public void test(){
        Context context = new Context();
        context.setStrategy(new ConcreteStrategy1());
        Strategy strategy = context.getStrategy();
        strategy.strategyMethod();
    }
}

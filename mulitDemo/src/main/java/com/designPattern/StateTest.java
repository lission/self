package com.designPattern;

import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/30 14:34
 * 状态模式
 * 主要角色：环境角色、抽象状态角色、具体状态角色
 *
 */
public class StateTest {

    /**
     * 抽象状态类
     *
     * */
    abstract class State{
        abstract void Handle(Context context);
    }
    /**
     * 环境角色
     * */
    class Context{
        private State state;

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public Context() {
            this.state =new ConcreteStateA();
        }
        public void Handle(){
            state.Handle(this);
        }
    }

    /**
     * 具体状态A
     * */
    class ConcreteStateA extends State{
        @Override
        void Handle(Context context) {
            System.out.println("具体状态A");
            context.setState(new ConcreteStateB());
        }
    }

    /**
     * 具体状态B
     * */
    class ConcreteStateB extends State{
        @Override
        void Handle(Context context) {
            System.out.println("具体状态B");
            context.setState(new ConcreteStateA());

        }
    }

    @Test
    public void test(){
        Context context =new Context();
        context.Handle();
        context.Handle();
    }
}

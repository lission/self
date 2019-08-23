package com.designPattern;

import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/6/4 14:57
 * 备忘录模式
 * 主要角色：发起人，备忘录，管理者
 */
public class MementoTest {

    /**
     * 备忘录角色
     * */
    class Memento{
        private String state;

        public Memento(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }


    /**
     * 管理者角色
     * */
    class Caretaker{
        private Memento memento;

        public Memento getMemento() {
            return memento;
        }

        public void setMemento(Memento memento) {
            this.memento = memento;
        }
    }

    /**
     * 发起人
     * */
    class Originator{
        private String state;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
        public Memento createMemento(){
            return new Memento(state);
        }

        public void restoreMemento(Memento m){
            this.setState(m.getState());
        }
    }

    @Test
    public void test(){
        Originator o = new Originator();
        Caretaker c = new Caretaker();
        o.setState("S0");
        System.out.println("初始状态"+o.getState());
        c.setMemento(o.createMemento());
        o.setState("S1");
        System.out.println("当前状态"+o.getState());
        o.restoreMemento(c.getMemento());
        System.out.println("恢复状态"+o.getState());

    }
}

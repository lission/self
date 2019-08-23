package com.designPattern;

import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/28 16:19
 * 命令模式
 * 主要角色：抽象命令类、具体命令类、实现者/接收者、调用者/请求者
 */
public class CommandTest {

    /**
    *抽象命令类
    * */
    interface Command{
        void excute();
    }
    /**
     * 接收者1
     * 执行命令功能的相关操作
     * */
    class Receiver1 {
        public void action(){
            System.out.println("接收者1执行命令");
        }
    }
    /**
     * 具体命令类1
     * 抽象命令类的具体实现类，拥有接收者对象，通过调用接收者的功能完成命令要执行的操作
     * */
    class ConcreteCommand1 implements Command{
        private Receiver1 receiver1 = new Receiver1();

        /*public ConcreteCommand1(Receiver1 receiver1) {
            this.receiver1 = receiver1;
        }*/

        @Override
        public void excute() {
            receiver1.action();
        }
    }

    /**
     * 调用者
     * 请求的发送者，拥有很多的命令对象，通过访问命令对象来执行相关请求
     * */

    class Invoker{
        private Command command;

        public Invoker(Command command) {
            this.command = command;
        }

        public void setCommand(Command command) {
            this.command = command;
        }
        public void call(){
            command.excute();
        }
    }

    @Test
    public void test(){
        Invoker invoker = new Invoker(new ConcreteCommand1());
        invoker.call();
    }
}

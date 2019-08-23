package com.designPattern;

import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/27 17:55
 * 模板方法模式
 * 主要角色：抽象类、具体子类
 * 抽象类：包括模板方法、基本方法（抽象方法、具体方法、钩子方法）
 *
 */
public class TemplateMethodTest {

    /**
     * 抽象类 ：定义算法的骨架，按照某种顺序调用其包含的基本方法
     * */
    abstract class AbstractClass{
        public void templateMethod(){
            //模板方法
            specificMethod();
            abstractMethod1();
            abstractMethod2();
        }
        public void specificMethod(){
            //具体方法
            System.out.println("抽象类中的具体方法被调用...");

        }
        public abstract void abstractMethod1();//抽象方法1
        public abstract void abstractMethod2();//抽象方法1

    }

    /**
     * 具体子类
     * */
    class ConcrectClass extends AbstractClass{
        @Override
        public void abstractMethod1() {
            System.out.println("抽象方法1实现被调用...");
        }

        @Override
        public void abstractMethod2() {
            System.out.println("抽象方法2实现被调用...");
        }
    }

    @Test
    public void Test(){
        AbstractClass tm = new ConcrectClass();
        tm.templateMethod();
    }
}

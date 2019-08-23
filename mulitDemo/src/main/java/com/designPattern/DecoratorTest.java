package com.designPattern;

import org.testng.annotations.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/16 17:16
 * 装饰模式
 * 主要角色：抽象构件、具体构件、抽象装饰、具体装饰
 *
 */
public class DecoratorTest {

    /**
     * 抽象构件
     * */
    interface Component{
        void operation();
    }
    /**
     * 具体构件
     * */
    class ConcreteComponent implements Component{
        @Override
        public void operation() {
            System.out.println("访问具体构件");
        }
    }

    /**
     * 抽象装饰
     * */
    abstract class Decorator implements Component{
        private Component component;

        public Decorator(Component component) {
            this.component = component;
        }
        @Override
        public void operation(){
            component.operation();
        }
    }

    /**
     * 具体装饰
     * */
    class ConcreteDecorator1 extends Decorator{
        public ConcreteDecorator1(Component component) {
            super(component);
        }

        @Override
        public void operation() {
            super.operation();
            addFunction();
        }

        public void addFunction(){
            System.out.println("具体装饰1增加额外功能");
        }
    }

    @Test
    public void Test(){
        Component component = new ConcreteComponent();
        Decorator decorator = new ConcreteDecorator1(component);
        decorator.operation();
    }

    @Test
    public void exemapleTest(){
        /*BufferedReader in=new BufferedReader(new FileReader("filename.txtn"));
                String s=in.readLine();*/
    }
}

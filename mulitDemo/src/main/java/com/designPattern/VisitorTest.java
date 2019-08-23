package com.designPattern;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lisong@mimidai.com
 * @date 2019/6/3 17:54
 * 访问者模式
 * 主要角色：抽象访问者、具体访问者、抽象元素、具体元素、对象结构
 */
public class VisitorTest {

    /**
     * 抽象访问者
     * */
    interface Visitor{
        void visit(ConcreteElement1 element);
        void visit(ConcreteElement2 element);
    }
    /**
     * 具体访问者1
     * */

    class ConcreteVisitor1 implements Visitor{
        @Override
        public void visit(ConcreteElement2 element) {
            System.out.println("具体访问者1访问-->"+element.operation1());

        }

        @Override
        public void visit(ConcreteElement1 element1){
            System.out.println("具体访问者1访问-->"+element1.operation1());
        }
    }
    /**
     * 抽象元素
     * */
    interface Element{
        void accept(Visitor visitor);
    }
    /**
     * 具体元素
     * */
    class ConcreteElement1 implements Element{
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public String operation1(){
            return "具体元素A的操作。";
        }
    }

    /**
     * 具体元素
     * */
    class ConcreteElement2 implements Element{
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public String operation1(){
            return "具体元素B的操作。";
        }
    }
    /**
     * 对象结构
     * */
    class ObjectStructure{
        private List<Element> list = new ArrayList<>();
        public void accept(Visitor visitor){
            Iterator<Element> i = list.iterator();
            while (i.hasNext()){
                i.next().accept(visitor);
            }
        }

        public void add(Element element){
            list.add(element);
        }
        public void remove(Element element){
            list.remove(element);
        }
    }

    @Test
    public void test(){
        ObjectStructure ob = new ObjectStructure();
        ob.add(new ConcreteElement1());
        ob.add(new ConcreteElement2());
        Visitor visitor = new ConcreteVisitor1();
        ob.accept(visitor);

    }
}

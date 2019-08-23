package com.designPattern;

import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/15 21:05
 * 适配器模式
 * 适配者、代理类、目标接口
 *
 */

public class AdapterTest {
    //目标接口
    interface Target{
        void request();
    }
    //适配者
    class Adaptee{
        public void specificRequest(){
            System.out.println("适配者请求结果……");
        }
    }

    /**
     * 类适配器模式
     * */
    class ClassAdapter extends Adaptee implements Target{

        @Override
        public void request() {
            super.specificRequest();
        }
    }

    @Test
    public void classTest(){
        Target target = new ClassAdapter();
        target.request();
    }
    /**
     * 对象适配模式
     * */
    class ObjectAdapter implements Target{
        private Adaptee adaptee;

        public ObjectAdapter() {

        }

        public ObjectAdapter(Adaptee adaptee) {
            this.adaptee = adaptee;
        }

        @Override
        public void request() {
            if (adaptee == null){
                adaptee = new Adaptee();
            }
            adaptee.specificRequest();
        }
    }

    @Test
    public void objectTest(){

        Target target = new ObjectAdapter(new Adaptee());
        target.request();
    }
}

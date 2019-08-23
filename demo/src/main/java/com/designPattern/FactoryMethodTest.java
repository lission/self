package com.designPattern;

import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/8 16:39
 * 工厂方法模式
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
    }

    @Test
    public void test(){
        AbstractFactory af = new DetailFactory1();
        Product p1 = af.newProduct();
        p1.show();
        af = new DetailFactory2();
        Product p2 = af.newProduct();
        p2.show();
    }

    /**
     * 抽象产品 ，产品的抽象描述
     *
     * */
    interface Product{
        public void show();
    }

    /**
     * 抽象工厂，提供创建产品的接口
     *
     */
    interface AbstractFactory{
        public Product newProduct();
    }

    /**
     * 具体产品1
     * */

    class DetailProduct1 implements Product{
        @Override
        public void show() {
            System.out.println("具体产品1");
        }
    }
    /**
     * 具体产品2
     * */

    class DetailProduct2 implements Product{
        @Override
        public void show() {
            System.out.println("具体产品2");
        }
    }
    /**
     * 具体工厂1
     *
     * */
    class DetailFactory1 implements AbstractFactory{
        @Override
        public Product newProduct() {
            System.out.println("具体工厂1生成——》具体产品1");

            return new DetailProduct1();
        }
    }
    /**
     * 具体工厂2
     *
     * */
    class DetailFactory2 implements AbstractFactory{
        @Override
        public Product newProduct() {
            System.out.println("具体工厂2生成——》具体产品2");

            return new DetailProduct2();
        }
    }
}

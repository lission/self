package com.designPattern;

import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/14 10:16
 * 建造者模式结构原型
 */
public class BuilderTest {


    @Test
    public void test(){
        //建造者 提供创建零部件方法
        Builder builder = new ConcrectBuilder();
        //指挥者创建 组装
        Director director = new Director(builder);
        //产品完成
        Product product = director.construct();
        System.out.println(product.getPartA());
    }
    /**
     * 4要素
     * 产品（复杂对象）
     * 抽象建造者（包含创建各零部件的抽象方法及返回产品的getResult()方法）
     * 具体建造者 （实现Builder接口，完成复杂产品各个部件的具体创建方法）
     * 指挥者 （组装产品，完成复杂对象创建，不包含产品具体信息）
     * */

    //产品
    class Product {
        private String partA;
        private String partB;
        private String partC;

        public void show(){
            //显示产品特性
        }

        public String getPartA() {
            return partA;
        }

        public void setPartA(String partA) {
            this.partA = partA;
        }

        public String getPartB() {
            return partB;
        }

        public void setPartB(String partB) {
            this.partB = partB;
        }

        public String getPartC() {
            return partC;
        }

        public void setPartC(String partC) {
            this.partC = partC;
        }
    }

    //抽象建造者
    abstract class Builder {
        //创建产品对象
        protected Product product = new Product();
        public abstract void buildPartA();
        public abstract void buildPartB();
        public abstract void buildPartC();
        //返回产品对象
        public Product getResult(){
            return product;
        }
    }

    //具体建造者
    class ConcrectBuilder extends Builder{
        @Override
        public void buildPartA() {
            product.setPartA("建造 PartA");
        }

        @Override
        public void buildPartB() {
            product.setPartB("建造 PartB");

        }

        @Override
        public void buildPartC() {
            product.setPartC("建造 PartC");

        }

    }

    //指挥者
    class Director{
        private Builder builder;
        public Director(Builder builder){
            this.builder=builder;
        }

        //产品构建与组装
        public Product construct(){
            builder.buildPartA();
            builder.buildPartB();
            builder.buildPartC();
            return builder.getResult();
        }
    }

}

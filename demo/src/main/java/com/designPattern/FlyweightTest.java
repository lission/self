package com.designPattern;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/22 20:45
 * 享元模式
 * 主要角色：抽象享元角色、具体享元角色、非享元角色、享元工厂角色
 */
public class FlyweightTest {
    /**
     * 非享元角色
     * 不可以共享的外部状态，以参数形式注入具体享元相关方法中
     * */
    class UnsharedConcrectFlyweight{
        private String info;

        public UnsharedConcrectFlyweight(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    /**
     * 抽象享元角色
     * 所有具体享元类的基类，为具体享元规范需要实现的公共接口，非享元外部状态以参数形式通过方法传入
     * */
    interface Flyweight{
        void operation(UnsharedConcrectFlyweight state);
    }

    /**
     * 具体享元角色1
     * */
    class ConcrectFlyweight1 implements Flyweight{
        private String key;
        public ConcrectFlyweight1(String key) {
            this.key = key;
            System.out.println("具体享元角色1");
        }

        @Override
        public void operation(UnsharedConcrectFlyweight state) {
            System.out.println("具体享元角色1……操作");

        }
    }

    /**
     * 具体享元角色2
     * */
    class ConcrectFlyweight2 implements Flyweight{
        private String key;
        public ConcrectFlyweight2(String key) {
            this.key = key;
            System.out.println("具体享元角色2");
        }

        @Override
        public void operation(UnsharedConcrectFlyweight state) {
            System.out.println("具体享元角色2……操作");
        }
    }

    /**
     * 享元工厂角色
     * 负责创建和管理享元角色
     * */
    class FlyweightFactory{
        private HashMap<String,Flyweight> flyweights = new HashMap<>();

        public Flyweight getFlyweight(String key){
            Flyweight flyweight = flyweights.get(key);
            if (flyweight == null){
                flyweight = new ConcrectFlyweight1(key);
                flyweights.put(key,flyweight);
            }else {
                System.out.println("具体享元"+key+"已经存在，被成功获取！");
            }
            return flyweight;
        }
    }

    @Test
    public void test(){
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight f01=factory.getFlyweight("a");
        Flyweight f02=factory.getFlyweight("a");
        Flyweight f03=factory.getFlyweight("b");
        Flyweight f04=factory.getFlyweight("b");
        f01.operation(new UnsharedConcrectFlyweight("第1次调用a。"));
        f02.operation(new UnsharedConcrectFlyweight("第2次调用a。"));
        f03.operation(new UnsharedConcrectFlyweight("第1次调用b。"));
        f04.operation(new UnsharedConcrectFlyweight("第2次调用b。"));
    }
}

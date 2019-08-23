package com.designPattern;

import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/20 18:23
 * 外观模式
 * 主要角色：外观角色、子系统角色、客户角色
 * 外观角色：为多个子系统提供一个共同接口
 * 子系统角色：实现系统的部分功能，客户通过外观角色访问
 * 客户角色：通过一个外观角色访问各个子系统功能
 */
public class FacadeTest {

    /**
     * 子系统角色1
     * */
    class SubSystem1{
        public void method1(){
            System.out.println("子系统角色1");
        }
    }
    /**
     * 子系统角色2
     * */
    class SubSystem2{
        public void method2(){
            System.out.println("子系统角色2");
        }
    }
    /**
     * 子系统角色3
     * */
    class SubSystem3{
        public void method3(){
            System.out.println("子系统角色3");
        }
    }

    /**
     * 外观角色
     * */
    class Facade{
        private SubSystem1 obj1 = new SubSystem1();
        private SubSystem2 obj2 = new SubSystem2();
        private SubSystem3 obj3 = new SubSystem3();

        public void method(){
            obj1.method1();
            obj2.method2();
            obj3.method3();
        }
    }

    class Client{

    }
    @Test
    public void test(){
        Facade facade = new Facade();
        facade.method();
    }
}

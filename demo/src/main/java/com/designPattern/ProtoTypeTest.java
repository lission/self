package com.designPattern;

import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/6 18:18
 * 原型模式
 * 主要角色：抽象原型类、具体原型类、访问类
 * 抽象原型类：规定具体原型对象必须实现的接口
 * 具体原型类：实现抽象原型类的clone方法，它是可被复制的对象
 * 访问类：使用具体原型类的clone方法复制新的对象
 */
public class ProtoTypeTest{

    /**
     * 抽象原型类
     * */
    interface ProtoType{

    }
    /**
     * 具体原型类
     * */
    class Realizetype implements Cloneable{
        private int i;
        @Override
        public Object clone() throws CloneNotSupportedException {
            System.out.println("具体原型创建成功");
            return (Realizetype)super.clone();
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }

    @Test
    public void test(){
        try {
            Realizetype realizetype = new Realizetype();
            Realizetype realizetype1 = (Realizetype) realizetype.clone();
            realizetype.setI(2);
            System.out.println(realizetype1.getI());
//            realizetype1.setI(1);
            realizetype.setI(3);
            System.out.println(realizetype.getI());
            System.out.println(realizetype1.getI());
            System.out.println(realizetype == realizetype1);
            System.out.println(realizetype.equals(realizetype1));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}

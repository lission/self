package com.designPattern;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/25 15:56
 * 组合模式
 * 主要角色：抽象构件角色，树叶构件角色、树枝构件角色
 * 安全方式实现
 */
public class CompositeSafeTest {

    /**
     * 抽象构件角色
     * 主要作用是为树叶构件和树枝构件声明公共接口
     * 透明式：抽象构件角色中还声明访问和管理子类的接口
     * 安全式：管理工作由树枝构件完成
     * */
    interface Component{
        void operation();

    }

    /**
     * 树叶构件角色
     * 组合中的叶节点对象，没有子节点，用于实现抽象构件角色中声明的接口
     * */
    class Leaf implements Component{
        private String name;

        public Leaf(String name) {
            this.name = name;
        }

        @Override
        public void operation() {
            System.out.println("访问叶子对象"+name);
        }

    }

    class Composite implements Component{
        private ArrayList<Component> children = new ArrayList<>();

        @Override
        public void operation() {
            System.out.println("访问树枝节点");
            for (Object obj:children){
                ((Component)obj).operation();
            }

        }

        public void add(Component c) {
            children.add(c);
        }

        public void remove(Component c) {
            children.remove(c);
        }

        public Component getChild(int i) {
            return children.get(i);
        }
    }

    @Test
    public void test(){
        Component c0 =new Composite();
        Component c1 = new Composite();
        ((Composite) c0).add(new Leaf("leaf1"));

        ((Composite) c0).add(c1);
        ((Composite) c1).add(new Leaf("leaf2"));
        ((Composite) c1).add(new Leaf("leaf3"));
        c0.operation();
    }

}

package com.designPattern.singlepattern;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/17 17:13
 * @Description 静态内部类方式实现单例模式，推荐使用
 */
public class StaticInnerClassSinglePattern {

    private StaticInnerClassSinglePattern(){

    }

    /*
    * 类加载机制中，静态内部类，在外部类加载初始化时，静态内部类不会一起初始化
    * static属性只会跟随类加载初始化一次，天然保证线程安全问题
    * */
    private static class StaticInnerClassSinglePatternInstance{
        private static final StaticInnerClassSinglePattern instance= new StaticInnerClassSinglePattern();
    }

    public static StaticInnerClassSinglePattern getInstance(){
        return StaticInnerClassSinglePatternInstance.instance;
    }


}

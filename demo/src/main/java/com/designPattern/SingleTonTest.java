package com.designPattern;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/6 10:42
 * 单例设计模式
 */
public class SingleTonTest {
    private SingleTonTest(){

    }
    //饿汉式创建单例模式
    private static final SingleTonTest instance = new SingleTonTest();

    public static SingleTonTest getInstance(){
        return instance;
    }

    public void getName(){
        System.out.println("我是饿汉db");
    }

}

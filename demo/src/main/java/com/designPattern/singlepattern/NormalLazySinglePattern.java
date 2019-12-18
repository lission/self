package com.designPattern.singlepattern;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/17 15:01
 * @description 普通懒汉式创建单例模式
 */
public class NormalLazySinglePattern {
    private static NormalLazySinglePattern instance = null;
    /*
    * 构造方法要改成private
    * */
    private NormalLazySinglePattern(){

    }
    /*
    * 懒汉式加载，做到了延迟加载，只有显示调用getInstance时，instance==null，才会初始化对象，分配内存空间
    * 但是多线程情况下，可能会创建多个对象，无法保证线程安全
    * */
    public static NormalLazySinglePattern getInstance(){
        if (instance == null){
            instance = new NormalLazySinglePattern();
        }
        return instance;
    }
}

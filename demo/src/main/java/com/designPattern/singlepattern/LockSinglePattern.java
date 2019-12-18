package com.designPattern.singlepattern;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/17 15:09
 * @desctiption 加锁线程安全版懒汉式创建单例模式
 */
public class LockSinglePattern {

    private static volatile LockSinglePattern instance;

    /**
     * 如何防止反射破解，只需要在无参构造函数中加上，实例判断逻辑，如果已经声明，则抛出异常
     * */
    private LockSinglePattern(){
        if (instance != null){
            throw new RuntimeException("只能创建一个对象!");
        }

    }
    /*
    * 实现了延迟加载，保证线程安全，但是性能变低，会造成线程阻塞
    *
    * */
    public static synchronized LockSinglePattern getInstance(){
        if (instance == null){
            instance = new LockSinglePattern();
        }
        return instance;
    }
}

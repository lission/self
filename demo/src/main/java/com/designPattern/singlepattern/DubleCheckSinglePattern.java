package com.designPattern.singlepattern;

import java.io.Serializable;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/17 15:09
 * @desctiption 加强线程安全版懒汉式创建单例模式
 */
public class DubleCheckSinglePattern implements Serializable {

    private static volatile DubleCheckSinglePattern instance;

    private DubleCheckSinglePattern(){

    }
    /*
    * 双重判断
    * 第一次是为了提高效率，只有真未创建实例时才需要走加锁逻辑
    * 第二次是为了保证多线程下实现单实例，防止多线程情况下，A先获取锁，创建实例，在返回前，B线程又获取锁，重复创建实例
    * instance使用voliatle 保证变量可见性，有序性，防止指令重排
    * */
    public static DubleCheckSinglePattern getInstance(){
        if (instance == null){
            synchronized (DubleCheckSinglePattern.class){
                if (instance == null){
                    instance = new DubleCheckSinglePattern();
                }
            }
        }
        return instance;
    }
    /**
     * 单例类中加入readResolve()方法，然后在方法体中返回我们的单例实例即可，
     * 因为readResolve()方法是在readObject()方法之后才被调用，因而它每次都会用我们自己生成的单实例替换从流中读取的对象。这样自然就保证了单例
     * 源码在ObjectInputStream类的readOrdinaryObject方法中
     * */

    private Object readResolve() {
        return instance;
    }
}

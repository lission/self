package com.designPattern.singlepattern;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/17 14:58
 * @description 饿汉式创建单例模式
 */
public class HungrySinglePattern {
    /*
    * 缺陷：类初始化加载时就会创建对象，假如该对象并不会常用且占用很大的资源，会造成资源浪费
    * static 属性只会跟随类加载初始化一次，天然保证了线程安全问题
    * */

    /*
    * 构造方法修改为private类型，可以避免new 创建对象
    * */
    private HungrySinglePattern() {
    }

    private static HungrySinglePattern instance = new HungrySinglePattern();

    public static HungrySinglePattern getInstance(){
        return instance;
    }
}

package com.designPattern.singlepattern;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/17 17:37
 * @description 枚举实现单例模式，枚举类型天然单例
 */
public enum EnumSinglePattern {
    /*
    * 与懒汉模式一样，枚举类型无法延迟加载，枚举类加载会初始化INSTANCE
    * */
    INSTANCE;

    public static EnumSinglePattern getInstance(){
        return INSTANCE;
    }
}

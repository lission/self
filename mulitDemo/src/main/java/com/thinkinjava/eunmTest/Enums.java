package com.thinkinjava.eunmTest;

import java.util.Random;

/**
 * @author lisong@mimidai.com
 * @date 2019/7/2 15:11
 * 利用泛型，实现枚举类型实例的随机获取
 * 目的：减少重复代码
 */
public class Enums {
    private static Random rand = new Random();
    public static <T extends Enum<T>> T random(Class<T> ec){
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values){
        return values[rand.nextInt(values.length)];
    }

}

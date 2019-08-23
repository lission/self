package com.thinkinjava.genericity;

/**
 * @author lisong@mimidai.com
 * @date 2019/7/7 20:22
 * 泛型接口，实现生成器
 */
public interface Generator<T> {
    T next();


}

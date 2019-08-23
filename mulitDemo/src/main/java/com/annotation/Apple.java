package com.annotation;

/**
 * @author lisong@mimidai.com
 * @date 2019/4/16 14:56
 * 注解使用
 */
public class Apple {
    @FruitProvider(id=1,name = "陕西红富士",address = "陕西西安")
    private String appleProvider;

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }
}

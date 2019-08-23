package com.annotation;

/**
 * @author lisong@mimidai.com
 * @date 2019/4/16 14:56
 * 注解使用
 */
public class Banana {
    @FruitProvider(id=2,name = "海南蓝富士",address = "海南海口")
    private String appleProvider;

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }
}

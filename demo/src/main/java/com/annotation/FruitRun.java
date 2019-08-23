package com.annotation;

/**
 * @author lisong@mimidai.com
 * @date 2019/4/16 15:51
 */
public class FruitRun {
    public static void main(String[] args) {
        FruitInfoUtil.getFruitInfo(Apple.class);
        FruitInfoUtil.getFruitInfo(Banana.class);
    }
}

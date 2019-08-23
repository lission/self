package com.thinkinjava.eunmTest;

/**
 * @author lisong@mimidai.com
 * @date 2019/7/2 18:05
 * 通过接口，使enum子类化，实现元素分类
 */
public interface Food {
    enum Appetizer implements Food{
        SALAD,SOUP,SPRING_ROLLS;
    }
    enum MainCourse implements Food{
        LASAGEN,BURRITO,PAD_THAI,HUMMOUS;
    }
    enum Dessert implements Food{
        APPLE,GELATO,FRUIT;
    }
    enum Coffee implements Food{
        BLACK_COFFEE,ESPRESSO,LATTE,CAPPUCCINO;
    }
}

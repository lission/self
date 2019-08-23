package com.thinkinjava.eunmTest;
import static com.thinkinjava.eunmTest.Food.*;

/**
 * @author lisong@mimidai.com
 * @date 2019/7/2 18:11
 */
public class TypeOfFood {
    public static void main(String[] args) {
        Food food = Appetizer.SALAD;
        food = Dessert.FRUIT;
        food = Coffee.CAPPUCCINO;
    }
}

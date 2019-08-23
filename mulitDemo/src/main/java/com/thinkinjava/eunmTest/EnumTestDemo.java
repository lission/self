package com.thinkinjava.eunmTest;

import com.collectionTest.Money;
import com.collectionTest.WeekDay;

/**
 * @author lisong@mimidai.com
 * @date 2019/7/2 15:32
 */
public class EnumTestDemo {

    public static void main(String[] args) {
        for (int i = 0;i<10;i++){
            System.out.println(Enums.random(WeekDay.class));
        }
        for (int i = 0;i<10;i++){
            System.out.println(Enums.random(Money.class));
        }
    }
}

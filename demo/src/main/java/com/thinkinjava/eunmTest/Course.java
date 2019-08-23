package com.thinkinjava.eunmTest;

/**
 * @author lisong@mimidai.com
 * @date 2019/7/2 18:24
 * 创建一个枚举的枚举
 */
public enum Course {
    APPETIZER(Food.Appetizer.class),MAINCOURSE(Food.MainCourse.class),DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private Food[] values;
    Course(Class<? extends Food> kind) {
        this.values=kind.getEnumConstants();
    }
    public Food randomSelection(){
        return Enums.random(values);
    }
}

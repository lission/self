package com.thinkinjava.eunmTest;

/**
 * @author lisong@mimidai.com
 * @date 2019/7/3 10:58
 */
public class Meal {
    public static void main(String[] args) {
        for (int i =0;i<10;i++){
            for (Course course:Course.values()){
                Food food = course.randomSelection();
                System.out.println(food);
            }
        }
    }
}

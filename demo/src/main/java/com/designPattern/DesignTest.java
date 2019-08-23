package com.designPattern;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/6 10:50
 */
public class DesignTest {

    public static void main(String[] args) {
        SingleTonTest singleTonTest = SingleTonTest.getInstance();
        SingleTonTest singleTonTest1 = SingleTonTest.getInstance();
        singleTonTest.getName();
        singleTonTest1.getName();
        System.out.println(singleTonTest.getClass());
        System.out.println(singleTonTest.getClass().getName());
        System.out.println(singleTonTest.getClass().getDeclaredFields());
        try {
            System.out.println(singleTonTest.getClass().getDeclaredField("instance"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.println(singleTonTest.getClass().getCanonicalName());


    }
}

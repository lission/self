package com.collectionTest;

/**
 * @author lisong@mimidai.com
 * @date 2019/6/30 11:14
 */
public enum WeekDay {
    Mon("Monday"),Tue("Tuesday"),Wed("Wednesday"),Thu("Thursday"),Fri("Friday"),Sat("Saturday"),Sun("Sunday");

    private String day;
    WeekDay(String day) {
        this.day = day;
    }

    public String getDay(){
        return day;
    }

    @Override
    public String toString() {
        return this.day + this.ordinal();
    }}



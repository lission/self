package com.testTemp;

import lombok.Data;

/**
 * @author lisong@mimidai.com
 * @date 2018/6/8 17:52
 */
@Data
public class Student {
    private String name;
    private Integer age;
    private Integer tall;

    public Student(String name, Integer age, Integer tall) {
        this.name = name;
        this.age = age;
        this.tall = tall;
    }
}

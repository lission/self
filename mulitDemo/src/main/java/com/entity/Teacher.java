package com.entity;

/**
 * @author lisong@mimidai.com
 * @date 2019/5/8 21:54
 */
public class Teacher {
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    private Student student;
}

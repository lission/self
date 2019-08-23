package com.prictice;

import java.util.Objects;

public class Phone {
    private String userId;
    private String phoneString;
    private Integer phoneSize;
    private Integer userIdSize;

    public Phone() {
    }

    public Phone(String userId, String phoneString, Integer phoneSize, Integer userIdSize) {
        this.userId = userId;
        this.phoneString = phoneString;
        this.phoneSize = phoneSize;
        this.userIdSize = userIdSize;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneString() {
        return phoneString;
    }

    public void setPhoneString(String phoneString) {
        this.phoneString = phoneString;
    }

    public Integer getPhoneSize() {
        return phoneSize;
    }

    public void setPhoneSize(Integer phoneSize) {
        this.phoneSize = phoneSize;
    }

    public Integer getUserIdSize() {
        return userIdSize;
    }

    public void setUserIdSize(Integer userIdSize) {
        this.userIdSize = userIdSize;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "userId='" + userId + '\'' +
                ", phoneString='" + phoneString + '\'' +
                ", phoneSize=" + phoneSize +
                ", userIdSize=" + userIdSize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(userId, phone.userId) &&
                Objects.equals(phoneString, phone.phoneString) &&
                Objects.equals(phoneSize, phone.phoneSize) &&
                Objects.equals(userIdSize, phone.userIdSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, phoneString, phoneSize, userIdSize);
    }
}

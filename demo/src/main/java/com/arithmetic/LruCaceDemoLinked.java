package com.arithmetic;

import lombok.Data;

import java.util.LinkedList;

/**
 * @author lisong@mimidai.com
 * @date 2019/10/23 11:46
 * 基于链表实现的LRU算法（最近最少算法）
 */
@Data
public class LruCaceDemoLinked {
    /**
     * 思路：每次新插入数据时，将数据插到链表头部；每次缓存命中（数据被访问），将数据移到链表头部，当链表满时，移除链表尾部数据
     * */
    /**
     * 链表容量
     * */
    private int capacity;
    /**
     * 初始化链表
     * */
    private LinkedList<User> cacheList = new LinkedList<>();

    public LruCaceDemoLinked(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 根据用户名称获取数据，如果名称相同则返回用户实体，且将该用户移动到链表头部
     * */
    public User get(String userName){
        User u = null;
        for (User user :cacheList){
            if (user.getUserName().equals(userName)){
                cacheList.remove(user);
                cacheList.add(0,user);
                u = user;
                break;
            }
        }
        return u;
    }

    /**
     * 插入新数据，将数据插入到链表头部，如果链表已满，则移除链表尾部数据
     * */
    public void put(User user){
        if (cacheList.size() == capacity){
            cacheList.remove(capacity-1);
        }
        cacheList.add(0,user);
    }



    @Data
    static class User {
        private String userName;
        private int age;

        public User(String userName, int age) {
            this.userName = userName;
            this.age = age;
        }
    }


    public static void main(String[] args) {
        LruCaceDemoLinked cacheDemo = new LruCaceDemoLinked(3);
        User user1 = new User("a1", 1);
        cacheDemo.put(user1);
        User user2 = new User("a2", 2);
        cacheDemo.put(user2);
        System.out.println(cacheDemo.get("a1"));
        User user3 = new User("a3", 3);
        cacheDemo.put(user3);
    }
}

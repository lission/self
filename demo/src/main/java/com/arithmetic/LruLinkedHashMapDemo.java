package com.arithmetic;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lisong@mimidai.com
 * @date 2019/10/23 15:20
 */
public class LruLinkedHashMapDemo<K,V> {
    /**
     * 思路：每次新插入数据时，将数据插到链表头部；每次缓存命中（数据被访问），将数据移到链表头部，当链表满时，移除链表尾部数据
     * */
    private int capacity;

    private LinkedHashMap<K,V> cacheMap;

    public LruLinkedHashMapDemo(int capacity) {
        this.capacity = capacity;
        cacheMap = new LinkedHashMap(16,0.75F,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {

                if (capacity +1 == cacheMap.size()){
                    return true;
                }else {
                    return false;
                }
            }
        };

    }

    public void put(K key,V value){
        cacheMap.put(key,value);
    }

    public V get(K key){
        return cacheMap.get(key);
    }

}

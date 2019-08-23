package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lisong@mimidai.com
 * @date 2019/3/26 16:23
 */
public class PoolTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);

    }
}

package com.thread;

/**
 * @author lisong@mimidai.com
 * @date 2019/3/21 14:38
 */
public class MythreadTest extends Thread {

    @Override
    public void run(){
        System.out.println("MythreadTest.run");
    }

    public static void main(String[] args) {
        MythreadTest mythreadTest = new MythreadTest();
        mythreadTest.start();
    }
}



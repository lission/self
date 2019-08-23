package com.thread;

/**
 * @author lisong@mimidai.com
 * @date 2019/3/21 14:41
 */
public class MythreadTest1 implements Runnable {

    private String taskId;
    private MythreadTest1(String s){
        this.taskId =s;
    }

    @Override
    public void run() {
        System.out.println("MythreadTest1.run");
        MyService service = new MyService();
        service.semaphoreTest(taskId);
    }

    public static void main(String[] args) {
        MythreadTest1 mythreadTest1 = new MythreadTest1("1");
        MythreadTest1 mythreadTest2 = new MythreadTest1("2");
        MythreadTest1 mythreadTest3 = new MythreadTest1("3");
        MythreadTest1 mythreadTest4 = new MythreadTest1("4");
        MythreadTest1 mythreadTest5 = new MythreadTest1("5");
        MythreadTest1 mythreadTest6 = new MythreadTest1("6");
        Thread thread1 = new Thread(mythreadTest1);
        Thread thread2 = new Thread(mythreadTest2);
        Thread thread3 = new Thread(mythreadTest3);
        Thread thread4 = new Thread(mythreadTest4);
        Thread thread5 = new Thread(mythreadTest5);
        Thread thread6 = new Thread(mythreadTest6);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }
}

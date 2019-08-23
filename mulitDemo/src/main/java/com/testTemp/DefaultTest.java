package com.testTemp;

/**
 * @author lisong@mimidai.com
 * @date 2019/3/8 14:58
 */
public interface DefaultTest  {

    void doSomeThing();

    default void doSomeNewThing(){
        System.out.println("new thing since jdk 1.8");
    }
}

class DeaultClass implements DefaultTest{

    public DeaultClass(){

    }

    @Override
    public void doSomeThing(){
        System.out.println("do some thing");
    }

    public static DefaultTest getDeaultTest(){
        return (DefaultTest) new DeaultClass();
    }

    public static void main(String[] args) {
        /*DeaultClass deaultClass = new DeaultClass();
        deaultClass.doSomeThing();
        deaultClass.doSomeNewThing();*/
        DefaultTest defaultTest = DeaultClass.getDeaultTest();
        defaultTest.doSomeThing();
    }
}
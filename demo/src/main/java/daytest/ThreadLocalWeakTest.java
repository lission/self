package daytest;

import java.lang.ref.WeakReference;

/**
 * @author lisong@mimidai.com
 * @date 2019/11/22 15:44
 */
public class ThreadLocalWeakTest {
    private static final ThreadLocal<String> threadLocalA = new ThreadLocal<>();
    private static final ThreadLocal<String> threadLocalB = new ThreadLocal<>();
    private static final WeakReference<ThreadLocal> threadLocalWeakReference1 =
        new WeakReference<>(threadLocalA);
    private static final WeakReference<ThreadLocal> threadLocalWeakReference2 =
        new WeakReference<>(threadLocalB);
    /**
     * 在调用的线程的map中存入key为ThreadLocal本身，value为在该线程设置的值
     * @param value
     */
    public static void setThreadLocalWeakReference1(String value){
        setValueA(value);
    }
    
    public static String getThreadLocalWeakReference1(){
        return getValueA();
    }
    
    public static void setThreadLocalWeakReference2(String value){
        setValueB(value);
    }
    
    public static String getThreadLocalWeakReference2(){
        return getValueB();
    }
    
    public static void setValueA(String value){
        threadLocalA.set(value);
    }

    public static String getValueA(){
        return threadLocalA.get();
    }

    public static void clearValueA(){
        threadLocalA.remove();
    }

    public static void setValueB(String value){
        threadLocalB.set(value);
    }

    public static String getValueB(){
        return threadLocalB.get();
    }

    public static void clearValueB(){
        threadLocalB.remove();
    }


    public static void main(String[] args) {
        //线程1的ThreadLocalMap中存着key为threadLocalA，value为A1；key为threadLocalB，value为B1
        new Thread(){
            @Override
            public void run(){
                setThreadLocalWeakReference1("A1");
                setThreadLocalWeakReference2("B1");
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    
                System.gc();
                System.out.println("沉睡结束1");
                System.out.println("thread1:" + getThreadLocalWeakReference1());
                System.out.println("thread1:" + getThreadLocalWeakReference2());
            }
        }.start();
    // 线程2的ThreadLocalMap中存着key为threadLocalA，value为A2；key为threadLocalB，value为B2
    new Thread() {
      @Override
      public void run() {
        setThreadLocalWeakReference1("A2");
        setThreadLocalWeakReference2("B2");
        System.gc();
        try {
          Thread.sleep(100000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("沉睡结束2");
        System.out.println("thread2:" + getThreadLocalWeakReference1());
        System.out.println("thread2:" + getThreadLocalWeakReference2());
      }
    }.start();
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        System.gc();
        System.out.println("主线程！！");
    
    }

}

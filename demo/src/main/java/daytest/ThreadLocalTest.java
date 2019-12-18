package daytest;

/**
 * @author lisong@mimidai.com
 * @date 2019/11/22 15:44
 */
public class ThreadLocalTest {
    private static final ThreadLocal<String> threadLocalA = new ThreadLocal<>();
    private static final ThreadLocal<String> threadLocalB = new ThreadLocal<>();

    /**
     * 在调用的线程的map中存入key为ThreadLocal本身，value为在该线程设置的值
     * @param value
     */
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
                setValueA("A1");
//                clearValueA();
                setValueB("B1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.gc();
                System.out.println("thread1:" + getValueA());
                System.out.println("thread1:" + getValueB());
//                clearValueB();
            }
        }.start();
        //线程2的ThreadLocalMap中存着key为threadLocalA，value为A2；key为threadLocalB，value为B2
        new Thread(){
            @Override
            public void run(){
                setValueA("A2");
                setValueB("B2");
//                clearValueA();
                System.gc();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2:" + getValueA());
                System.out.println("thread2:" + getValueB());
//                clearValueB();
            }
        }.start();
       
    }

}

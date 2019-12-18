package daytest;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/3 19:55
 */
public class DeadDayTestTwelve {
    private String s1 ="s1";
    private String s2 = "s2";
    
    public void syncTest(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (s1){
                        System.out.println(Thread.currentThread().getName()+"s1加锁");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (s2){
                            System.out.println(Thread.currentThread().getName()+"s2加锁");
                            
                        }
                    }
                    
                }
            }
        };
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (s2){
                        System.out.println(Thread.currentThread().getName()+
                            "s2加锁");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (s1){
                            System.out.println(Thread.currentThread().getName()+"s1加锁");
                        }
                    }
                    
                }
            }
        };
        
        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable1);
        thread.start();
        thread1.start();
    }
    public static void main(String[] args) {
        DeadDayTestTwelve deadDayTestTwelve = new DeadDayTestTwelve();
        deadDayTestTwelve.syncTest();
    }
}

package com.testTemp;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lisong@mimidai.com
 * @date 2018/8/6 14:03
 */
public class ThreadTest {


    public static void outTest(List<Integer> list){
        for (int i=0;i<100;i++){
//            System.out.println(i);
            list.add(i);
        }
    }

    /*@Test
    public void rsaTest(){
        byte[] bytes = getBytes("D:\\project\\mmd\\米米贷\\渠道对接技术文档\\易宝\\代付\\openssl\\rsa_private_key_pkcs8.pem");
        String ss = Base64.getBASE64(bytes);
        System.out.println(ss);
    }*/

    /**
     * 获得指定文件的byte数组
     */
    private static byte[] getBytes(String filePath){
        byte[] buffer = null;
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            File file = new File(filePath);
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            buffer = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                }
            }
        }
        return buffer;
    }

    @Test
    public static void shutTest(){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0;i<1000;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("task_start");
                        Thread.sleep(1000);
                        System.out.println("task_end");
                    } catch (InterruptedException e) {
                        System.out.println("中断异常："+e);
                    }
                }
            });
        }
//        executorService.shutdown();
        try {
            executorService.shutdown();
            System.out.println("等待时间");
			if (!executorService.awaitTermination(30000L, TimeUnit.MILLISECONDS)){
                executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
            System.out.println("每日逾期数据反馈至闪蝶-方法中断异常{}: 当前时间为：{}"+e);
            executorService.shutdownNow();
		}
        System.out.println("end");

    }

    public static void main(String[] args) {
        shutTest();
        System.out.println("end-end");

    }



}

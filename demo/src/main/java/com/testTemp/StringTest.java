package com.testTemp;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import utils.HttpClientUtil;


/**
 * @author lisong@mimidai.com
 * @date 2019/1/2 17:16
 */
public class StringTest {
    @Test
    public void splitTest(){
        String s = "您在松鼠应急的#money#元借款已划扣成功，良好的还款记录将会提升您的信用分。关注公众号“松鼠应急”领取更多专属福利！退订回T";
        String s1 = "6222000200103326351";
        String s2 = "12";
        System.out.println(s1.substring(s1.length()-4,s1.length()));
//        System.out.println(s2.substring(s2.length()-4,s2.length()));
//        System.out.println(s.replace("退订回T",""));
    }

    @Test
    public void splitTest1(){
        String s = "11,";
//        String s = "1";
        String[] s1 = new String[4];
        s1 = s.split(",");
        System.out.println(s1.length);
        for (String s2:s1){
            if (!s2.equals(",")){
                System.out.println(s2);
            }
        }
//        System.out.println(s2.substring(s2.length()-4,s2.length()));
//        System.out.println(s.replace("退订回T",""));
    }

    @Test
    public void splitTest2(){
        String s = "/api/callbackRepaymentNotify/kjt";
        String[] s1 = s.split("/");
        System.out.println(s1.length);
        for (String s2:s1){
//            if (!s2.equals(",")){
                System.out.println(s2);
//            }
        }
        System.out.println("最后"+s1[s1.length-1]);
    }

    @Test
    public void mysqlPassword(){
        String username= "bNVOqb7WKLX5Bjnw+LMv92taj25KOxDimXxILPQjw42wgv+1lHzOH8kr97xDwWdhpY67QuYCS7sWN4W46YbkFA==";
        String username_songshu= "DcpDFIIEuOKR3wU2Jj1ozirFQVXn/rQkoa6gqfujIio+0lk8cKtlKVxxdXBNPzVcmtl4UYtG/4yH69im024GUw==";
        String password= "iMPm7NtlZrtC5jLSquczmzyzIsRM5a0HgDu6TWoPQwe75txV1knjsYxbdaM2a3zNOz0pmWnYL8O0vyic1jseqQ==";
        String password_test= "TYviITPes4cKXB5V58LKZ/e9ki8B0rxwjrauOUF/FfV7efnMpjp1+Yeb8zXX3pDU2Qox69lQKYF5oxI56WsmFA==";
        String password_songshu= "hmalk/P0QI5E+fLEAxQaFd3vFk0QvhrfSXUNx+NqCozR/seO2s1HoB8g351RDjRmy255zGZrJtxbmcsAXC4mmA==";
        /**
         *
         * #正式
         * #spring.datasource.url=jdbc:mysql://rm-2zej92cw2g316er56.mysql.rds.aliyuncs.com:3306/mimidai?characterEncoding=utf-8
         * #spring.datasource.username=midaizi_db
         * #spring.datasource.password=XvR!BQfLcUZgoMfLildr
         *
         * */

        try {
            System.out.println(ConfigTools.decrypt(username_songshu));
            System.out.println(ConfigTools.decrypt(password));
            System.out.println(ConfigTools.encrypt("mimijobs"));
            System.out.println(ConfigTools.encrypt("60R47XuX$zQS"));
            System.out.println(ConfigTools.encrypt("Mimidai123"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getOpenId() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"+ "&appid="
                + "wx2c43750e3b4aaea1" + "&secret=" + "fe7e32c546cd775409a55f914ee31c07";
        try {
            String retUrl = HttpClientUtil.get(url);
            JSONObject json = JSONObject.parseObject(retUrl);
            System.out.println(json.get("access_token").toString());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void indexOfTest(){
        String s1 = "flo"+"w";
        String s3 = s1+"w";
        String s4 = "flow";
//        String s2 = "flow";
//        System.out.println(s2.indexOf(s1));
//        System.out.println(s2.substring(1,4));
    }
    
    @Test
    public void constantTest(){
        String s1 = new StringBuffer("va").append("ja").toString();
//        String s2 = new StringBuffer("ja").append("va").toString();
        /**
         * vaja不存在JVM字符串常量池中，因此，String s1 = new StringBuffer("va").append
         * ("ja").toString();执行完成之后，堆上存在了一个"vaja"对象，且在字符串常量池中保留该字符串引用
         * 此时调用s1.intern(),直接返回“vaja”对象引用，因此 s1.intern() == s1 为true
        */
         System.out.println(s1.intern() == s1);
        /**
         * java存在JVM字符串常量池中，因此，String s2 = new StringBuffer("ja").append("va").toString();
         * 执行完成之后，因为使用了new关键字，会重新创建一个“java”对象引用，此时调用s2.intern(),
         * 返回JVM中原有的“java”对象引用，因此 s2.intern() == s2 为false
         */
//         System.out.println(s2.intern() == s2);
         
         String s3 = new String("str3");
         String s4 = new String("java");
         String s5 = "java";
         String s6 = "java";
         System.out.println(s3.intern()==s3);
         System.out.println(s4.intern()==s4);
         System.out.println(s5.intern()==s5);
         System.out.println(s5==s6);
    }

    @Test
    public void lowerTest(){
        String s = "WE ALWAYS WAITING FOR";
        System.out.println(s.toLowerCase());
    }

}

package com.testTemp;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;


/**
 * @author lisong@mimidai.com
 * @date 2019/1/2 17:16
 */
public class StringTest {
    @Test
    public void splitTest(){
        String s = "您在松鼠应急的#money#元借款已划扣成功，良好的还款记录将会提升您的信用分。关注公众号“松鼠应急”领取更多专属福利！退订回T";
        String s1 = "北京市";
        String s2 = "123456789019286545";
        System.out.println(s2.substring(0,6));
        System.out.println(s2.substring(s2.length()-4,s2.length()));
//        System.out.println(s.replace("退订回T",""));
    }


    @Test
    public void mysqlPassword(){
        String username= "bNVOqb7WKLX5Bjnw+LMv92taj25KOxDimXxILPQjw42wgv+1lHzOH8kr97xDwWdhpY67QuYCS7sWN4W46YbkFA==";
        String password= "iMPm7NtlZrtC5jLSquczmzyzIsRM5a0HgDu6TWoPQwe75txV1knjsYxbdaM2a3zNOz0pmWnYL8O0vyic1jseqQ==";
        /**
         *
         * #正式
         * #spring.datasource.url=jdbc:mysql://rm-2zej92cw2g316er56.mysql.rds.aliyuncs.com:3306/mimidai?characterEncoding=utf-8
         * #spring.datasource.username=midaizi_db
         * #spring.datasource.password=XvR!BQfLcUZgoMfLildr
         *
         * */

        try {
            System.out.println(ConfigTools.decrypt(username));
            System.out.println(ConfigTools.decrypt(password));
            System.out.println(ConfigTools.encrypt("root"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

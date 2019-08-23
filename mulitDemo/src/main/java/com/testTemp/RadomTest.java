package com.testTemp;

import org.junit.Test;
import utils.DateUtils;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lisong@mimidai.com
 * @date 2018/11/8 11:01
 */
public class RadomTest {

    private Random random = SecureRandom.getInstanceStrong();

    public RadomTest() throws NoSuchAlgorithmException {
    }

    @Test
    public void testEx(){
        int rValue = this.random.nextInt();
        System.out.println(rValue);
    }

    @Test
    public void intEx(){
        Integer i = 128;
        Integer i1 = 128;
        Integer i2 = 127;
        Integer i3 = 127;
        System.out.println(i.equals(i1));
        System.out.println(i == i1);
        System.out.println(i2.equals(i3));
        System.out.println(i2 == i3);
    }

    @Test
    public void ratTest(){
        BigDecimal rating = new BigDecimal("0.2829");
        BigDecimal newValue3 = rating.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(newValue3);

    }
    @Test
    public void batchNo(){
        String order = createOrder("KJTAGRE");
        System.out.println(order);
        System.out.println(order.substring(7,21));



    }

    /**
     * 创建购卡订单号
     * 修改 -- @author liaowei -- 20180316 -- 为了防止订单号出现重复，订单号由原来的27位增加到30位(时间增加3位毫秒)
     * @param prefixStr
     * @return
     */
    public  static String createOrder(String prefixStr){
        String date = DateUtils.format(new Date(), "yyyyMMddHHmmssSSS");
        Integer suffixStr = (int) ((Math.random()*9+1)*100000);
        return prefixStr+date+suffixStr;
    }
    /**
     * 产生随机的3位数
     * @return
     */
    public String getThree(){
        Random rad=new Random();

        String result  = rad.nextInt(1000) +"";

        if(result.length()<=2){
            result = "0" + result;
        }
        return result;
    }

    public String getThree2(){
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

        String result  = threadLocalRandom.nextInt(0,1000) +"";

        if(result.length()<=2){
            result = "0" + result;
        }
        return result;
    }
}

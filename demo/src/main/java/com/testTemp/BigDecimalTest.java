package com.testTemp;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author lisong@mimidai.com
 * @date 2019/3/19 11:28
 */
public class BigDecimalTest {

    @Test
    public void addTest(){
        BigDecimal manageFee = new BigDecimal(10);
        BigDecimal premiumFee = new BigDecimal(100);
        BigDecimal detail = new BigDecimal(10);
        BigDecimal total = new BigDecimal(0);
//        total = premiumFee.add(manageFee).add(detail);
        System.out.println(total.add(detail));
    }

    @Test
    public void equalTest(){
        BigDecimal manageFee = new BigDecimal(10);
        BigDecimal premiumFee = new BigDecimal(100);
        BigDecimal premiumFee1 = null;
        BigDecimal total = new BigDecimal(10).add(manageFee);
        total = total.add(premiumFee);
        System.out.println(manageFee.compareTo(premiumFee));
        if (premiumFee1 == null || premiumFee1.compareTo(manageFee) == 0){
            System.out.println(11111111);
        }
        System.out.println(manageFee.compareTo(new BigDecimal(10)));
    }
}

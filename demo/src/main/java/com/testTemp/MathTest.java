package com.testTemp;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lisong@mimidai.com
 * @date 2018/10/25 10:07
 */
public class MathTest {
    @Test
    public void divisionTest(){
        int j = 9000;
        int size = 1;
        System.out.println(j/size);//取整
        System.out.println(j%size);//取余
    }

    @Test
    public void distributeTest(){

        int num = 2;

        List<Long> managers = new ArrayList<Long>();
        managers.add(3L);
        managers.add(4L);
        managers.add(5L);

        List<Long> outsourcingManagers = new ArrayList<Long>();
        /*outsourcingManagers.add(1L);
        outsourcingManagers.add(2L);*/

        List<Long> idsForDistribute = new ArrayList<Long>();
        idsForDistribute.add(1L);
        idsForDistribute.add(2L);
        idsForDistribute.add(3L);
        idsForDistribute.add(4L);
        idsForDistribute.add(5L);
        idsForDistribute.add(6L);
        idsForDistribute.add(7L);

        int i = 0;
        int j=0;//内催ID
        //此处目的是往redis中添加最后一个被分案的人，例如 ABC 分5个案件 那AB拿两个案件 C拿一个 下次就从C开始分案
        for (Long loanId : idsForDistribute) {
            if (i < outsourcingManagers.size() * num) { // 优先分发给委外，每人分发 num 个
                if (i==idsForDistribute.size()-1){
                    System.out.println("外催："+outsourcingManagers.get(i % outsourcingManagers.size()));
                    System.out.println("外催i："+i);
                    System.out.println("外催outsourcingManagers.size()："+outsourcingManagers.size());
                }
            } else { // 剩余案件平均分发给其他人员
                if (i==idsForDistribute.size()-1){
                    System.out.println("内催："+managers.get(j % managers.size()));
                    System.out.println("内催j："+j);
                    System.out.println("内催managers.size()："+managers.size());
                }
                j++;
            }
            i++;
        }

    }
    /**
     * 调用方法
     * */
    @Test
    public void bigdecimalTest(){
        BigDecimal bigDecimal = new BigDecimal("1600");
        BigDecimal fine = computeFeeByDays(1,0.0075,bigDecimal);
        System.out.println(fine);
    }


    @Test
    public void bigdecimalTestdivde(){
        BigDecimal bigDecimal = new BigDecimal("1600");
        System.out.println(bigDecimal.divide(new BigDecimal("100")));
    }

    @Test
    public void longTest(){
        Integer suffixStr = (int) ((Math.random()*9+1)*100000);
        System.out.println(suffixStr);
    }


    /**
     * 根据天数和费率计算费用
     * @param days 天数
     * @param percent 每天的费率
     * @param feeTotal 总费用
     * @return 计算后的费用
     * @author:	haidong
     * @date: 2016年1月21日 上午9:58:11
     */
    public static BigDecimal computeFeeByDays(int days,double percent,BigDecimal feeTotal){
        BigDecimal feePercent = new BigDecimal(percent);
        BigDecimal total = feeTotal;
        for(int i = 0 ; i < days ;i++){
            total = total.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(feePercent.add(new BigDecimal(1)));
        }
        return total.subtract(feeTotal);
    }


    /**
     * 位运算测试
     * size >> 1
     * */
    @Test
    public void test(){
        Integer i = 9;
        Integer j = 3;
        System.out.println(i.compareTo(j));
    }


}

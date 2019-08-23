package com.testTemp;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;
import utils.DateUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lisong@mimidai.com
 * @date 2018/11/7 17:38
 */
public class DateTest {
    @Test
    public void addTest(){
        Integer overdueDayL = 0;
        Integer overdueDayR = 151;
        String planDateBegin = DateUtils.getyyyyMMddTime(new Date(), -(overdueDayR+1));
        String planDateEnd   = DateUtils.getyyyyMMddTime(new Date(), -(overdueDayL+1));
        System.out.println(planDateBegin);
        System.out.println(planDateEnd);
    }
    @Test
    public void startTest(){
        System.out.println(DateUtils.format(DateUtils.getCurrentDayStartTime()));
        System.out.println(DateUtils.format(DateUtils.getCurrentDayEndTime()));

        Calendar calendar = Calendar.getInstance();
        Date dayBeginTime = DateUtils.getDayBeginTimeByDate(calendar.getTime());
        System.out.println(dayBeginTime);
        System.out.println(DateUtils.getCurrentDayStartTime());
    }

    @Test
    public void yearTest(){
        LocalDate localDate = LocalDate.now();
        int days = localDate.getDayOfYear();
//        int days = 2;
        int year = localDate.getYear();
        int yearstoday = days - 1;
        LocalDate yearstodayDate;
        if (yearstoday > 0) {
            yearstodayDate = LocalDate.ofYearDay(year, yearstoday);
        } else if (yearstoday == 0){
            Date yearstodayDate1 = DateUtils.addDays(new Date(),-1);
            String yearstodayDates = DateUtils.format(yearstodayDate1,"yyyy-MM-dd");
            System.out.println(yearstodayDates);
//            yearstodayDate = LocalDate.ofYearDay(year - 1, yearstoday);
        }else {
            yearstodayDate = LocalDate.ofYearDay(year - 1, yearstoday);
        }
        /*String yearstodayStr = yearstodayDate.toString();
        System.out.println(yearstodayStr);*/
    }

    @Test
    public void dat1(){
        Date overdueLimitDate = DateUtils.parse("2019-05-22 00:00:00",DateUtils.DEFAULT_PATTERN);
        Date overdueLimitPro = DateUtils.parse("2019-04-23 00:00:00",DateUtils.DEFAULT_PATTERN);

//        System.out.println(overdueLimitDate.after(overdueLimitPro));
        try {
//            Integer day = DateUtils.daysBetween(overdueLimitDate,new Date(),false);
            Integer day1 = DateUtils.daysBetween(overdueLimitDate,overdueLimitPro,false);
//            System.out.println(day);
            System.out.println(day1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void timeTest(){
        String planDateBegin = DateUtils.getyyyyMMddTime(new Date(), -(0+1));
        String planDateEnd   = DateUtils.getyyyyMMddTime(new Date(), -(3000+1));
        System.out.println(planDateBegin);
        System.out.println(planDateEnd);

    }

    @Test
    public void batisEncrypt(){
        String name = null;
        try {
            name = ConfigTools.encrypt("squirrel");
            String password = ConfigTools.encrypt("DBoMcl5v");
            System.out.println(name);
            System.out.println(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseTest(){
        String endTimeStr = DateUtils.getNow("yyyy-MM-dd");
        Date nowDate = DateUtils.addDays(new Date(),6);
        String startTimeStr = DateUtils.format(nowDate,"yyyy-MM-dd");
        String s = "201904172212230110iSXL1e98";
        Date applyTime = DateUtils.parse(s.substring(0, 14), "yyyyMMddHHmmss");

        System.out.println(applyTime);
    }

}

package com.testTemp;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

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
        Date overdueLimitDate = DateUtils.parse("2019-04-22 01:00:00",DateUtils.DEFAULT_PATTERN);
        Date overdueLimitPro = DateUtils.parse("2019-04-22 04:00:00",DateUtils.DEFAULT_PATTERN);

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
        String startTimeStr = DateUtils.format(new Date(),"dd");
        System.out.println(startTimeStr);
        Date overdueLimitDate = DateUtils.parse("2019-05-22 00:00:00",DateUtils.DEFAULT_PATTERN);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH,22);
        System.out.println(cal.getTime());

        /*String s = "201904172212230110iSXL1e98";
        Date applyTime = DateUtils.parse(s.substring(0, 14), "yyyyMMddHHmmss");

        System.out.println(applyTime);*/
    }

    @Test
    public void getCurrentWeek(){
        Map<String,Date> map = DateUtils.getCurrentWeekScope();
        System.out.println(map.get("startTime"));
        System.out.println(map.get("endTime"));
        System.out.println(DateUtils.addDays(map.get("startTime"),-7));
        System.out.println(DateUtils.addDays(map.get("endTime"),-8));
    }

    @Test
    public void salaryDay(){
        Date salaryDate =DateUtils.parse("2019-10-22 00:00:00",DateUtils.DEFAULT_PATTERN);
        int i = getMonthDiff(new Date(),salaryDate);
        System.out.println(i);
        if (i == 0){
            i++;
        }
        System.out.println(i);

        Date repayDate = getPlanDate(salaryDate,i);
        System.out.println(repayDate);
    }

    public static Date getPlanDate(Date salaryDate,int i){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(salaryDate);
        calendar.add(Calendar.MONTH, i);
        return calendar.getTime();
    }

    public static int getMonthDiff(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if(c1.getTimeInMillis() < c2.getTimeInMillis()) {
            return 0;
        }
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-8-16 d2 = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if(month1 < month2 || month1 == month2 && day1 < day2){
            yearInterval --;
        }
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2 ;
        if(day1 < day2) {
            monthInterval --;
        }
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval + 1;
    }

    @Test
    public void monthTest(){
        Date salaryDate =DateUtils.parse("2019-10-11 00:00:00",DateUtils.DEFAULT_PATTERN);
        Date now = new Date();
        System.out.println(sameDay(now,salaryDate));
    }
    public static boolean sameMonth(Date d1, Date d2){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
        //fmt.setTimeZone(new TimeZone()); // 如果需要设置时间区域，可以在这里设置
        return fmt.format(d1).equals(fmt.format(d2));
    }
    public static boolean sameDay(Date d1, Date d2){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        //fmt.setTimeZone(new TimeZone()); // 如果需要设置时间区域，可以在这里设置
        return fmt.format(d1).equals(fmt.format(d2));
    }
}

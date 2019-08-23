package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
    private static SimpleDateFormat sdf;
    /**
     * 短时间字符串格式
     */
    public static String SHORT_PATTERN = "yyyy-MM-dd";
    /**
     * 默认时间字符串格式
     */
    public static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 天的单位
     */
    public static final String DAY_UNIT = "d";
    /**
     * 小时的单位
     */
    public static final String HOUR_UNIT = "h";
    /**
     * 分钟的单位
     */
    public static final String MINUTE_UNIT = "m";

    /**
     * 以默认模式格式化时间字符串
     *
     * @param timeStr 时间字符串
     * @return 格式化后的时间类
     */
    public static Date parse(String timeStr) {
        return parse(timeStr, DEFAULT_PATTERN);
    }

    /**
     * 格式化时间字符串返回Date
     *
     * @param timeStr 时间字符串
     * @param pattern 字符串模式 例如:yyyy-MM-dd
     * @return 格式化后的时间类
     */
    public static Date parse(String timeStr, String pattern) {
        Date result = null;
        sdf = new SimpleDateFormat(pattern);
        try {
            result = sdf.parse(timeStr);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
        return result;
    }

    /**
     * 格式化时间字符串返回Date
     *
     * @param timestamp 时间戳字符串
     * @param pattern   字符串模式 例如:yyyy-MM-dd
     * @return 格式化后的时间类
     */
    public static Date parseTimeStamp(String timestamp, String pattern) {
        Date result = null;
        sdf = new SimpleDateFormat(pattern);
        try {
            String str = sdf.format(Long.parseLong(timestamp));
            result = sdf.parse(str);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
        return result;
    }


    /**
     * 以字符串格式获取当前时间的
     *
     * @return 当前时间的字符串
     */
    public static String getNow() {
        return getNow(DEFAULT_PATTERN);
    }

    /**
     * 以特定的模式获取当前时间的字符串
     *
     * @param pattern 模式
     * @return 当前时间的字符串
     */
    public static String getNow(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 以默认模式格式化一个时间类为字符串
     *
     * @param date 时间
     * @return 格式化后的字符串
     */
    public static String format(Date date) {
        return format(date, DEFAULT_PATTERN);
    }

    /**
     * 格式化一个时间类为字符串
     *
     * @param date    时间
     * @param pattern 模式
     * @return 格式化后的字符串
     */
    public static String format(Date date, String pattern) {
        sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        return daysBetween(smdate, bdate, true);
    }

    /**
     * @param minDate 小
     * @param maxDate 大
     * @throws ParseException GY 2017年9月12日 获取分钟差
     */
    public static long minuteBetween(Date minDate, Date maxDate) {
        long minMinute = minDate.getTime();
        long maxMinute = maxDate.getTime();
        long minuteDiff = (maxMinute - minMinute) / (1000 * 60);
        return minuteDiff;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate      较小的时间
     * @param bdate       较大的时间
     * @param includeTody 是否包含今天
     * @author: haidong
     * @date: 2016年1月23日 下午7:04:24
     */
    public static int daysBetween(Date smdate, Date bdate, boolean includeTody) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        if (includeTody) {
            between_days++;
        }
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * @return Date 返回类型
     * @Title: addDays
     * @Description: 日期增加多少天
     */
    public static Date addDays(Date date, Integer day) {
        return addTime(date, day, DAY_UNIT);
    }

    /**
     * 给时间添加指定的时间段,返回计算后的时间
     *
     * @param startTime 开始时间
     * @param time      需要添加的时间
     * @param unit      单位
     * @return Date 返回类型
     */
    public static Date addTime(Date startTime, Integer time, String unit) {
        Calendar calendar = Calendar.getInstance();
        // 设置开始时间
        calendar.setTime(startTime);
        // 默认为分钟
        Integer type = Calendar.MINUTE;
        switch (unit) {
            case DAY_UNIT:
                type = Calendar.DATE;
                break;
            case HOUR_UNIT:
                type = Calendar.HOUR;
                break;
            case MINUTE_UNIT:
                type = Calendar.MINUTE;
                break;
        }

        calendar.add(type, time);

        return calendar.getTime();
    }

    /**
     * @return Date 返回去掉时分秒的日期
     * @Title: toDay
     * @Description: 将传入的日期 去掉 时分秒
     */
    public static Date toDay(Date time) {
        String p = "yyyy-MM-dd";
        String str = format(time, p);
        return parse(str, p);
    }

    /**
     * @return Map<String,Date> 返回类型
     * @Title: getCurrentYearScope
     * @Description: 得到本年的时间范围
     */
    public static Map<String, Date> getCurrentYearScope() {
        Map<String, Date> data = new HashMap<String, Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Integer year = calendar.get(Calendar.YEAR);
        String start = year + "-1-1 00:00:00";
        String end = year + "-12-31 23:59:59";
        data.put("startTime", parse(start));
        data.put("endTime", parse(end));
        return data;
    }

    /**
     * @return Map<String,Date> 返回类型
     * @Title: getCurrentQuarterScope
     * @Description: 得到本季度的时间范围
     */
    public static Map<String, Date> getCurrentQuarterScope() {
        Map<String, Date> data = new HashMap<String, Date>();
        data.put("startTime", getCurrentQuarterStartTime());
        data.put("endTime", getCurrentQuarterEndTime());
        return data;
    }

    /**
     * @return Map<String,Date> 返回类型
     * @Title: getCurrentMonthScope
     * @Description: 得到本月的时间范围
     */
    public static Map<String, Date> getCurrentMonthScope() {
        Map<String, Date> data = new HashMap<String, Date>();
        data.put("startTime", getCurrentMonthStartTime());
        data.put("endTime", getCurrentMonthEndTime());
        return data;
    }

    /**
     * @return Map<String,Date> 返回类型
     * @Title: getCurrentWeekScope
     * @Description: 得到本周的时间范围
     */
    public static Map<String, Date> getCurrentWeekScope() {
        Map<String, Date> data = new HashMap<String, Date>();
        data.put("startTime", getTimesWeekmorning());
        data.put("endTime", getTimesWeeknight());
        return data;
    }

    // 获得本周一0点时间
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    // 获得本周日24点时间
    public static Date getTimesWeeknight() {
        SimpleDateFormat sdf = new SimpleDateFormat(SHORT_PATTERN);
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekmorning());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return parse(sdf.format(cal.getTime()) + " 23:59:59");
    }

    /**
     * 当前季度的开始时间，即2012-01-1 00:00:00
     */
    public static Date getCurrentQuarterStartTime() {
        Date now = null;
        try {
            now = getQuarterStartTime(getCurrentQuarterNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     */
    public static Date getCurrentQuarterEndTime() {
        Date now = null;
        try {
            now = getQuarterEndTime(getCurrentQuarterNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取当前季度数
     */
    public static int getCurrentQuarterNumber() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        int currentQuarter = 0;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                currentQuarter = 1;
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                currentQuarter = 2;
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                currentQuarter = 3;
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                currentQuarter = 4;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentQuarter;
    }

    /**
     * 获取季度的开始时间
     *
     * @param quarter 季度数
     */
    public static Date getQuarterStartTime(int quarter) {
        SimpleDateFormat sdf = new SimpleDateFormat(SHORT_PATTERN);
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            switch (quarter) {
                case 1:
                    c.set(Calendar.MONTH, 0);
                    break;
                case 2:
                    c.set(Calendar.MONTH, 3);
                    break;
                case 3:
                    c.set(Calendar.MONTH, 6);
                    break;
                case 4:
                    c.set(Calendar.MONTH, 9);
                    break;
                default:
                    c.set(Calendar.MONTH, 0);
                    break;
            }
            c.set(Calendar.DATE, 1);
            now = parse(sdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取季度的结束时间
     *
     * @param quarter 季度数
     */
    public static Date getQuarterEndTime(int quarter) {
        SimpleDateFormat sdf = new SimpleDateFormat(SHORT_PATTERN);
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            switch (quarter) {
                case 1:
                    c.set(Calendar.MONTH, 2);
                    c.set(Calendar.DATE, 31);
                    break;
                case 2:
                    c.set(Calendar.MONTH, 5);
                    c.set(Calendar.DATE, 30);
                    break;
                case 3:
                    c.set(Calendar.MONTH, 8);
                    c.set(Calendar.DATE, 30);
                    break;
                case 4:
                    c.set(Calendar.MONTH, 11);
                    c.set(Calendar.DATE, 31);
                    break;
                default:
                    c.set(Calendar.MONTH, 2);
                    c.set(Calendar.DATE, 31);
                    break;
            }
            now = parse(sdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得本月的开始时间，即2012-01-01 00:00:00
     */
    public static Date getCurrentMonthStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(SHORT_PATTERN);
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            now = parse(sdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前月的结束时间，即2012-01-31 23:59:59
     */
    public static Date getCurrentMonthEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            now = getMonthEndTime(c.get(Calendar.MONTH) + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得上月的开始时间，即2012-01-01 00:00:00
     */
    public static Date getPreviousMonthStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(SHORT_PATTERN);
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.add(Calendar.MONTH, -1);
            c.set(Calendar.DATE, 1);
            now = parse(sdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当上月的结束时间，即2012-01-31 23:59:59
     */
    public static Date getPreviousMonthEndTime() {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            now = getMonthEndTime(c.get(Calendar.MONTH));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取月份的开始时间
     *
     * @param month 月份
     */
    public static Date getMonthStartTime(int month) {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.MONTH, month - 1);
            c.set(Calendar.DATE, 1);
            now = parse(format(c.getTime(), SHORT_PATTERN) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取某月的开始时间
     *
     * @param date 月份
     */
    public static Date getMonthStartTimeByDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            now = parse(format(c.getTime(), SHORT_PATTERN) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取月份的结束时间
     *
     * @param month 月份
     */
    public static Date getMonthEndTime(int month) {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.MONTH, month - 1);
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            now = parse(format(c.getTime(), SHORT_PATTERN) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取某月的结束时间
     *
     * @param date 月份
     */
    public static Date getMonthEndTimeByDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date now = null;
        try {
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            now = parse(format(c.getTime(), SHORT_PATTERN) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取当天的开始时间
     */
    public static Date getCurrentDayStartTime() {
        return getDayBeginTimeByDate(new Date());
    }

    /**
     * 获取当天的结束时间
     */
    public static Date getCurrentDayEndTime() {
        return getDayEndTimeByDate(new Date());
    }

    /**
     * 获取某天的开始时间
     *
     * @author: haidong
     * @date: 2016年1月16日 上午10:24:09
     */
    public static Date getDayBeginTimeByDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date now = null;
        try {
            now = parse(format(c.getTime(), SHORT_PATTERN) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取某天的结束时间
     *
     * @author: haidong
     * @date: 2016年1月16日 上午10:24:18
     */
    public static Date getDayEndTimeByDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date now = null;
        try {
            now = parse(format(c.getTime(), SHORT_PATTERN) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 计算两个时间段之间的天数
     *
     * @param date   时间1
     * @param date2  时间2
     * @param hasSTA 是否包括周六
     * @param hasSUN 是否包括周日
     * @return int 天数
     */
    public static int getDayNumber(Date date, Date date2, boolean hasSTA, boolean hasSUN) {
        int num = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBefore(date, date2));
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(getAfter(date, date2));

        for (; cal.compareTo(cal2) <= 0; cal.add(Calendar.DAY_OF_MONTH, 1)) {
            switch (cal.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.MONDAY:
                case Calendar.TUESDAY:
                case Calendar.WEDNESDAY:
                case Calendar.THURSDAY:
                case Calendar.FRIDAY:
                    num++;
                    break;
                case Calendar.SATURDAY:
                    if (hasSTA) {
                        num++;
                    }
                    break;
                case Calendar.SUNDAY:
                    if (hasSUN) {
                        num++;
                    }
                    break;
                default:
                    break;
            }
        }
        return num;
    }



    /**
     * 比较两个时间,取靠前的时间
     */
    public static Date getBefore(Date date, Date date2) {
        if (date.before(date2)) {
            return date;
        } else {
            return date2;
        }
    }

    /**
     * 比较两个时间,取靠后的时间
     */
    public static Date getAfter(Date date, Date date2) {
        if (date.after(date2)) {
            return date;
        } else {
            return date2;
        }
    }

    /**
     * 获取日期的星期数
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取日期的星期数的名称
     *
     * @param date     日期
     * @param language 语言 中文用 zh,英文用en
     */
    public static String getDayOfWeekName(Date date, String language) {
        String name = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                name = language.equals("zh") ? "星期一" : "MON";
                break;
            case Calendar.TUESDAY:
                name = language.equals("zh") ? "星期二" : "TUE";
                break;
            case Calendar.WEDNESDAY:
                name = language.equals("zh") ? "星期三" : "WED";
                break;
            case Calendar.THURSDAY:
                name = language.equals("zh") ? "星期四" : "THU";
                break;
            case Calendar.FRIDAY:
                name = language.equals("zh") ? "星期五" : "FRI";
                break;
            case Calendar.SATURDAY:
                name = language.equals("zh") ? "星期六" : "STA";
                break;
            case Calendar.SUNDAY:
                name = language.equals("zh") ? "星期日" : "SUN";
                break;
            default:
                break;
        }
        return name;
    }

    /**
     * 获取日期的字符串
     *
     * @param date   日期
     * @param addDay 增加的天数
     * @return String
     */
    @SuppressWarnings("static-access")
    public static String getyyyyMMddTime(Date date, int addDay) {
        String dateString = null;
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE, addDay);// 把日期往后增加一天.整数往后推,负数往前移动
            date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
            SimpleDateFormat formatter = new SimpleDateFormat(DateUtils.SHORT_PATTERN);
            dateString = formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    /**
     * @description 给一个｀2017年08月07日16时56分13秒｀格式的日期，拿到它与当前时间相差的天数
     */
    public static int getDayCount(String time) {
        int days = 0;
        if (time.contains("年") && time.contains("月") && time.contains("日") && time.contains("时") && time.contains
                ("分") && time.contains("秒")) {
            time = time.replace("年", "-");
            time = time.replace("月", "-");
            time = time.replace("日", " ");
            time = time.replace("时", ":");
            time = time.replace("分", ":");
            time = time.substring(0, time.length() - 1);
            Date crtDate = DateUtils.parse(time, DEFAULT_PATTERN);
            try {
                days = DateUtils.daysBetween(crtDate, new Date());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return days;
    }


    public static List<String> getMonthBetween(Date minDate, Date maxDate) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(sdf.format(minDate)));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(sdf.format(maxDate)));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    /**
     * 指定年月 获取月份的开始时间
     *
     * @param month 月份
     */
    public static Date getSpecifyMonthStartTime(int year, int month) {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month - 1);
            c.set(Calendar.DATE, 1);
            now = parse(format(c.getTime(), SHORT_PATTERN) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 指定年月 获取月份的开始时间
     *
     * @param month 月份
     */
    public static Date getSpecifyMonthEndTime(int year, int month) {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DATE, 0);
            now = parse(format(c.getTime(), SHORT_PATTERN) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }


    public static void main(String[] args) throws ParseException {
        //        System.out.println(DateUtils.daysBetween(DateUtils.parse("2016-02-13", "yyyy-MM-dd"),
        //                DateUtils.parse("2016-02-23", "yyyy-MM-dd")));
        //        System.out.println(DateUtils.getDayCount("2017年08月07日16时56分13秒"));
        //    		Date now = new Date();
        //    		Date MonthEnd = DateUtils.getCurrentMonthEndTime();
        //    		Date MonthEnd1 = getDayBeginTimeByDate(MonthEnd);
        //    		Date MonthEnd2 = getDayEndTimeByDate(MonthEnd);
        //
        //    		System.out.println("MonthEnd=" + MonthEnd.toLocaleString() + ",MonthEnd1=" +
        // MonthEnd1.toLocaleString() + ",MonthEnd2=" + MonthEnd2.toLocaleString());
        //
        //    		System.out.println(getCurrentMonthStartTime().toLocaleString());
        //
        //    		System.out.println(DateUtils.getCurrentMonthEndTime().toLocaleString());
        //
        //    	  System.out.println(DateUtils.getMonthStartTime(10).toLocaleString());
        //    	  System.out.println(DateUtils.getMonthEndTime(10).toLocaleString());

        // Date startDate = DateUtils.parse("2016-01-01", "yyyy-mm-dd");
        // Date endDate = DateUtils.parse("2017-01-07", "yyyy-mm-dd");
        // List<String> monthBetween = getMonthBetween(startDate, endDate);
        // System.out.println(startDate);
        // System.out.println(endDate);
        // System.out.println(DateUtils.daysBetween(startDate, endDate));
        // System.out.println(DateUtils.daysBetween(startDate, endDate, false));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.getSpecifyMonthEndTime(2000, 02)));


    }
}

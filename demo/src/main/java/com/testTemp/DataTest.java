package com.testTemp;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.ParseException;
import java.time.Instant;
import java.util.*;

import static utils.DateUtils.*;

/**
 * @author lisong@mimidai.com
 * @date 2018/8/1 22:24
 */
public class DataTest {

    @Test
    public void listTest(){
        List<Object> list = new ArrayList<Object>();
        list.add(1);
        list.add("1");
        System.out.println(list.size());
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        LinkedList<Object> linkedList = new LinkedList<Object>();

    }
    @Test
    public void dateTest(){
        Date sdate = parse("2018-11-06 11:42:40");
        Date mdate = new Date();
        try {
            int i = daysBetween(sdate,mdate);
            System.out.println(i);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void innitTest(){
        Date now = new Date();
        Date yesterday = addDays(now, -1);
        String sdate = format(yesterday,"yyyy-MM-dd 09:30:00");
        String mdate = format(now,"yyyy-MM-dd 09:30:00");
        String mdate1 = format(yesterday,"yyyy-MM-dd");
        System.out.println(now);
        System.out.println(yesterday);
        System.out.println(sdate);
        System.out.println(mdate);
        System.out.println(mdate1);
    }

    @Test
    public void longTest(){
        Long l1 = 123L;
        Long l2 = 123L;
        System.out.println(l1.equals(l2));
        System.out.println(l1==l2);
    }

    @Test
    public void stringTest(){
        ArrayList<String> batch = new ArrayList<>();
        batch.add("123");
        batch.add("223");
        batch.add("323");
        String eachBachPhone = StringUtils.strip(batch.toString(), "[]");
        String str2 = eachBachPhone.replaceAll(" ", "");
        System.out.println(eachBachPhone);
        System.out.println(str2);
    }

    @Test
    public void instantTest(){
        Instant start = Instant.now();
        Date star = new Date();
        System.out.println(start.getNano());
        System.out.println(star);
        System.out.println(star.getTime());
    }

    @Test
    public void listTestIm(){
        List<Long> l1 = new ArrayList<>();
        l1.add(1L);
        l1.add(1L);
        l1.add(1L);
        l1.add(2L);
        System.out.println(l1);
        HashSet h = new HashSet(l1);
        l1.clear();
        l1.addAll(h);
        System.out.println(l1);
    }


}

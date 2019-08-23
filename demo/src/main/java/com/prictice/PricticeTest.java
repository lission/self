package com.prictice;

import com.entity.Teacher;
import com.config.KjtEnum;
import com.entity.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lisong@mimidai.com
 * @date 2018/11/23 10:12
 * java基础功能疑惑点验证实例
 *
 * */
public class PricticeTest {
    public static void main(String[] args){
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(0);
        list2.add(0);
        func(list1,list2);
        System.out.println(list1);
        System.out.println(list2);
    }
    static void func(List<Integer> list1,List<Integer> list2){
        list1 = new ArrayList<>();
        list1.add(1);
        list2.add(1);
        list2 = new ArrayList<>();
        list1.add(2);
        list2.add(2);
    }


    @Test
    public void bitOperation(){
        int i = Integer.SIZE -3;
        int j = 1 << i;
        int h = -1 << i;
        System.out.println(i);
        System.out.println(j);
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(j));
        System.out.println(Integer.toBinaryString(h));
        int d= h|0;
        System.out.println(d);

    }

    @Test
    public void linkTest(){
        List<Long> longList =new ArrayList<>();
        List<Long> longListNew =new ArrayList<>();
        longList.add(1L);
        longList.add(2L);
        longList.add(3L);
        longList.add(4L);
        longList.add(5L);
        longList.add(6L);
        longList.add(7L);

        Long l = 4L;
        int index;
        if (longList.contains(l)){
            index = longList.indexOf(l);
        }else {
            Long managerId = null;
            //①流方式实现循环比较，
            /*Stream<Long> stream = longList.stream();
            managerId = stream.filter((Long) -> Long > l).findAny().orElse(null);*/

            //②使用迭代器实现
            /*Long nowL=null;
            for (Iterator it = longList.iterator();it.hasNext();){
                nowL=(Long) it.next();
                if (l.compareTo(nowL) <= 0){
                    System.out.println("3333333333333"+nowL);
                    managerId = nowL;
                    break;
                }
            }
            */
            //③for-each
            for (Long init:longList){
                if (l.compareTo(init) <=0){
                    managerId = init;
                    break;
                }
            }
            System.out.println("1111111"+managerId);
            if (managerId != null){
                index = longList.indexOf(managerId) - 1;
            }else {
                index = longList.indexOf(managerId);
            }
        }
        System.out.println("index"+index);
        if (index != longList.size() - 1) {
            List<Long> longList1 = longList.subList(0, index + 1);
            List<Long> longList2 = longList.subList(index + 1, longList.size());
            longListNew.addAll(longList2);
            longListNew.addAll(longList1);
        } else {
            longListNew = longList;
        }

        System.out.println(longListNew);
    }


    @Test
    public void stringTest(){
        String s = "abcdefg";
        String s1 = new String("abcdefg");
        int i = s1.length();
        System.out.println(s1);
        System.out.println(s1.length());
    }

    @Test
    public void charArrayTest(){
        char[] chars = new char[3];
        chars[0] = '1';
        chars[1] = '2';
        chars[2] = '3';
        System.out.println(chars[1]);
        System.out.println(chars.length);
        char c = 'a';
    }
    @Test
    public void changeTwoNumber(){
        //栈实现
        Stack<Long> longs = new Stack<>();
        Long a=1L;
        Long b=2L;
        longs.push(a);
        longs.push(b);
        a=longs.pop();
        b=longs.pop();
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void changeTwoNumber1(){
        //算法实现
        Long a=1L;
        Long b=2L;
        a = b-a;
        b = b-a;
        a = b+a;
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void codeTest(){
        System.out.println(KjtEnum.F.getCode());
        System.out.println(KjtEnum.TRADE_SUCCESS.getCode());
    }

    @Test
    public void classTest(){
        Teacher teacher = new Teacher();
        Student student = new Student();
        System.out.println(teacher.getStudent());
        System.out.println(student.getTeacher());
    }
}

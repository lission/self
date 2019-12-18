package com.leetcode;

import org.apache.logging.log4j.util.Chars;
import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/10/30 18:14
 */
public class Solution191030 {

    /**
     * 判断整数回文数
     * 普通解法，整数转字符串
     * */
    @Test
    public void palindromeTest(){
        /*
        * 思路
        * 1、找到中间值
        * 2、将中间值之后的数值反序
        * 3、比较中间值前后是否相等
        * */
        Long l = 123456543821L;
        System.out.println(l.toString().length());
        int mid;
        String front;
        String back;
        if (l.toString().length()%2 ==0){
            mid = l.toString().length()/2;
            front = l.toString().substring(0,mid);
        }else {
            mid = (l.toString().length()+1)/2;
            front = l.toString().substring(0,mid-1);
        }
        back = l.toString().substring(mid);

        System.out.println(mid);
        System.out.println(front);
        System.out.println(back);
        if (front.length() != back.length()){
            System.out.println(false);
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=back.length()-1;i>=0;i--){
            stringBuffer =stringBuffer.append(back.charAt(i));
        }
        System.out.println(stringBuffer.toString());
        if (front.equals(stringBuffer.toString())){
            System.out.println(true);
        }else {
            System.out.println(false);
        }
    }

    /**
     * 数学解法：通过取整和取余操作获取整数中对应的数字进行比较
     * % 取余；/ 取商整
     * int 后半部分反转，与前半部分比较
     * %10 得到最后一位数字，/10 得到剩余部分
     * */
    @Test
    public void palindromeLertCode(){
        int input = 0;
        if (input < 0 || (input%10 == 0 && input != 0)){
            System.out.println(false);
            return;
        }

        int palindrome = 0;
        while (input > palindrome){
            palindrome = palindrome*10 + input%10;
            input = input/10;
        }

        if (input == palindrome || (input == (palindrome/10))){
            System.out.println(true);
        }else {
            System.out.println(false);
        }
    }
}

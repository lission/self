package com.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lisong@mimidai.com
 * @date 2019/11/1 15:52
 */
public class Solution191101 {
    /**
     * Roma char change to int
     * */

    /*
    * 静态罗马字符整数关系对应
    * */
    public static Map<String,Integer> relationMap = new HashMap<>();
    static {
        relationMap.put("I",1);
        relationMap.put("IV",4);
        relationMap.put("V",5);
        relationMap.put("IX",9);
        relationMap.put("X",10);
        relationMap.put("XL",40);
        relationMap.put("L",50);
        relationMap.put("XC",90);
        relationMap.put("C",100);
        relationMap.put("CD",400);
        relationMap.put("D",500);
        relationMap.put("CM",900);
        relationMap.put("M",1000);

    }
    /*
    * 双位的，增加2
    * 单位的，增加1
    * */
    @Test
    public void romaCharToInt(){
        String roma = "II";
        int answer = 0;
        for (int i=0;i<roma.length();){
            if (i+1<roma.length() && relationMap.containsKey(roma.substring(i,i+2))){
                answer += relationMap.get(roma.substring(i,i+2));
                i+=2;
            }else {
                answer += relationMap.get(roma.substring(i,i+1));
                i+=1;
            }
        }
        System.out.println(answer);
    }

    /**
     * the longest common prefix
     * */
    @Test
    public void longestCommonPrefixTest(){
        /*
        * 首先获取数组所有字符串
        * 对每一个字符串前缀进行比较
        * */
        String[] strings = new String[]{"flower","flow","flight"};
        String prefix = strings[0];
        if(strings.length == 0){
            return ;
        }
        for (int i=1;i<strings.length;i++){
            while (strings[i].indexOf(prefix) !=0){
                prefix = prefix.substring(0,prefix.length()-1);
                if (prefix.isEmpty()){
                    return;
                }
            }

        }
        System.out.println(prefix);
    }
}

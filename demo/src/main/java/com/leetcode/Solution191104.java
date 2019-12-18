package com.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author lisong@mimidai.com
 * @date 2019/11/4 15:31
 */
public class Solution191104 {
    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效
     * */

    @Test
    public void test(){
        System.out.println(isValid());
    }
    public Boolean isValid(){
        /*
        * 整体表达式正确，则每一个子表达式都是正确的，每次匹配到一个正确的表达式，将其删除掉，则最终只剩下一个空字符串
        * 使用栈存储匹配过程
        * */
        String s = "]";
        Map<Character,Character> maches = new HashMap<>();
        maches.put(')','(');
        maches.put('}','{');
        maches.put(']','[');
        if (s == ""){
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (int i=0;i<s.length();i++){
            if (maches.containsKey(s.charAt(i))){
                if (stack.empty()){
                    stack.push('#');
                }
                if (!stack.pop().equals(maches.get(s.charAt(i)))){
                    return false;
                }
            }else {
                stack.push(s.charAt(i));
            }


        }
        return stack.empty();
    }

    /**
    * 将两个有序链表合并为一个新的有序链表并返回
    * */
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    /*
    * 常规算法：循环比较每一个节点大小，添加进指定位置
    * */
    public ListNode self1(ListNode l1,ListNode l2){
        ListNode listNode = new ListNode(-1);
        ListNode mid = listNode;
        while (l1 != null && l2 != null){
            if (l1.val <=l2.val){
                mid.next = l1;
                l1 = l1.next;
            }else {
                mid.next = l2;
                l2 = l2.next;
            }
            mid = mid.next;
        }
        mid.next = l1==null?l2:l1;
        return listNode.next;

    }

    /*
     *递归算法
     * */
    public ListNode mergeTwoLists(ListNode l1,ListNode l2){
        if (l1 == null){
            return l2;
        }else if (l2 == null){
            return l1;
        }else if (l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }

}

package com.leetcode;

import org.junit.Test;

import java.util.HashSet;

/**
 * @author lisong@mimidai.com
 * @date 2019/8/26 10:27
 * 算法测试
 */
public class Solution {
    /**
    * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标
    * */
    public int[] twoSum(int[] nums, int target) {
        int[] key= new int[2];
        for(int i =0;i<nums.length;i++){
            for (int j=0;j<nums.length;j++){
                int result = nums[i] +nums[j];
                if(target == result){
                    key[0] = i;
                    key[1] = j;
                }
            }
        }
        return key;
    }
    @Test
    public void twoSumTset(){
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] key=twoSum(nums,target);
        System.out.println("1:"+key[0]+",2:"+key[1]);
    }

    /**
     *给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * */
    /**
     * 定义链表类
     */
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x){this.val = x;}

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    public ListNode addTwoNumbers(ListNode l1,ListNode l2){
        ListNode start = new ListNode(0);
        ListNode p = l1, q = l2,key = start;
        int j = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;

            int i = x + y +j;
            j=i/10;
            key.setNext(new ListNode(i%10));
            key = key.getNext();
            System.out.println(key.val);

            if (p != null) {
                p = p.next;
            }
            if (q != null) {q = q.next;}
        }
        if (j >0){
            key.setNext(new ListNode(j));
        }
        return start.next;
    }
    @Test
    public void twoNumbersTset(){
        ListNode l1 = new ListNode(2);
        l1.setNext(new ListNode(4));
        l1.getNext().setNext(new ListNode(3));
        ListNode l2 = new ListNode(5);
        l2.setNext(new ListNode(6));
        l2.getNext().setNext(new ListNode(4));
        ListNode key=addTwoNumbers(l1,l2);
        System.out.println(key.val+"|"+key.next.val+"|"+key.next.next.val);
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * */
    public int lengthOfLongestSubstring(String s) {
        if (s ==null){
            return 0;
        }

        if (s.length() == 1){
            return 1;
        }
        int res = 0;
        int m = 0;
        int l = 0;
        int r = 0;
        int length = s.length();
        HashSet<Character> hashSet = new HashSet<>();
        while (l<length && r<length){
            Character cha = s.charAt(r);
            if (!hashSet.contains(cha)){
                hashSet.add(cha);
                r++;
                res = Math.max(res,r-l);
            }else {
                hashSet.remove(s.charAt(l));
                l++;
            }
        }
        return res;
    }

    @Test
    public void lenthTest(){
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}

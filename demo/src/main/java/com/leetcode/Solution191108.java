package com.leetcode;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lisong@mimidai.com
 * @date 2019/11/8 14:49
 */
public class Solution191108 {

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 你可以假设数组中无重复元素
     * */

    /*
    * 直接查找
    * */
    public int searchInsert(int[]nums,int target){
        if (nums.length ==0){
            return 0;
        }
        for (int i=0;i<nums.length;i++){
            if (target <= nums[i]){
                return i;
            }
        }
        return nums.length;
    }

    /*
    * 二分查找
    * */

    public int searchInsertTwo(int[]nums,int target){
        if (nums.length ==0){
            return 0;
        }
        int left =0;
        int right =nums.length-1;
        while (left < right){
            int mid = (left + right+1)/2;
            if (nums[mid] > target){
                right = mid-1;
            }else {
                return mid;
            }
        }

        return nums.length;
    }
}

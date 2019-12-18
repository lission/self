package com.arithmetic;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author lisong@mimidai.com
 * @date 2019/11/19 18:26
 * 排序算法
 * 本demo中默认排序方向为从小到大
 */
public class SortedAirthmetic {

    @Test
    public void test(){
        Integer[] arrays1 = {1,4,8,3,2,9,5,7};

//        Integer[] result = arrayBubbleSorted(arrays1);
        Integer[] result = sort(arrays1);
        for (Integer integer:result){
            System.out.println(integer);
        }
    }


    public Integer[] arrayBubbleSorted(Integer[] integers){
        Integer[] bak = Arrays.copyOf(integers,integers.length);
        /*
        * 冒泡排序，双重循环，时间复杂度（O(N2)），不申请额外空间
        * */
        for (int i=1;i<bak.length;i++){
            //设置该标识位的意义在于，如果本身数组排序就是正确的，则不需要进行剩余循环，直接返回即可，减少不必要的循环次数
            Boolean flag = true;
            for (int j=0;j<bak.length-i;j++){
                if (bak[j] > bak[j+1]){
                    int temp = bak[j];
                    bak[j] = bak[j+1];
                    bak[j+1] = temp;
                    flag = false;
                }
            }
            if (flag){
                break;
            }
        }
        return bak;
    }

    /**
     * 归并排序
     * 递归，分段排序，合并两个排好序的数组
     * */
    public Integer[] sort(Integer[] integers){
        Integer[] result = Arrays.copyOf(integers,integers.length);
        if (result.length<2){
            return result;
        }
        Integer middle = result.length/2;
        Integer[] left = Arrays.copyOfRange(result,0,middle);
        Integer[] right = Arrays.copyOfRange(result,middle,result.length);
        return mergerSorted(sort(left),sort(right));

    }

    public Integer[] mergerSorted(Integer[] left,Integer[] right){
        Integer[] result = new Integer[left.length+right.length];
        Integer i =0;
        while (left.length>0&&right.length>0){
            if (left[0] <= right[0]){
                result[i] = left[0];
                i++;
                left = Arrays.copyOfRange(left,1,left.length);
            }else {
                result[i] = right[0];
                i++;
                right = Arrays.copyOfRange(right,1,right.length);
            }
        }
        while (left.length>0){
            result[i++] = left[0];
            left = Arrays.copyOfRange(left,1,left.length);
        }
        while (right.length>0){
            result[i++] = right[0];
            right = Arrays.copyOfRange(right,1,right.length);
        }
        return result;
    }


}

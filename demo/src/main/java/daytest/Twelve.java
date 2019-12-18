package daytest;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/4 19:21
 */
public class Twelve {
    
    @Test
    public void test(){
        int[] ints ={9,2,3,1,7,5,4,4};
        
        int[] result = mergeSort(ints);
        for (int i:result){
            System.out.println(i);
        }
    }
    
    /**
     * 冒泡排序
     *  思路：比较相邻两个元素，如果前边的大于后边的，交换位置，重复n次，完成排序
     * */
    public int[] bubbleTest(int[] source){
        /*
        * 2、重复N次，完成最终排序
        * */
        for (int j=0;j<source.length;j++){
            Boolean flag = true;
            /*
             * 1、第一次排序，相邻交换
             * */
            for (int i=0;i<source.length-1;i++){
                if (source[i] > source[i+1]){
                    int temp = source[i];
                    source[i] = source[i+1];
                    source[i+1] =temp;
                    flag = false;
                }
            }
            if (flag){
                break;
            }
        }
        return source;
    }
    
    
    /**
     * 选择排序
     * 思路：选取队列中最小的值放入队列头部，重复上述步骤，直至遍历数组一遍
     * 注意：最小值选取的过程中，记住最小值下标，替换当前遍历的位置和最小值下标
     * */
    public int[] choiceSort(int[] source){
        
        /*
        * 循环n次
        * */
        for (int i=0;i<source.length;i++){
            /*
             * 找到最小值下标
             * */
            int m = i;
            for (int j=i;j<source.length;j++){
                if (source[j] <source[m]){
                    m=j;
                }
            }
            if (m !=i){
                int temp = source[i];
                source[i] = source[m];
                source[m] = temp;
            }
        }
        
        return source;
    }
    
    /**
     * 插入排序
     * 思路：默认第一个元素为有序数组，将剩余数组元素遍历，插入到有序数组的合适位置
     * */
    public int[] insertSort(int[] source){
        
        /*
         *遍历剩余元素
         * */
        for (int i=1;i<source.length;i++){

            for (int j=i;j>0;j--){
                if (source[j] < source[j-1]){
                    int temp = source[j-1];
                    source[j-1] = source[j];
                    source[j] = temp;
                }
            }
            
        }
        
        return source;
    }
    
    
    /**
     * 希尔排序
     * 思路：创建一个增量序列，按增量序列个数进行K次排序，根据对应的增量将待排序队列替换为若干长度为m的子序列，
     * 分别对各子表进行直接插入排序，仅增量因子为1时，整个序列作为一个表处理
     * */
    
    public int[] hillSort(int[] source){
        //确定最大增量序列值
        int gap = 1;
        while (gap<source.length){
            gap=gap*3+1;
        }
        //K次排序
        while (gap>0){
            //子队列排序
            for (int i=gap;i<source.length;i++){
            
            }
            
            gap = gap/3;
        }
        
        
        return source;
    }


    /**
     * 归并排序
     * 思路：将两个排序队列，合并插入到一个新队列中
     *      运用递归思想进行队列排序
     * */

    public int[] mergeSort(int[] source){
        if (source.length<2){
            return source;
        }
        int mid = source.length/2;
        int[] left = Arrays.copyOfRange(source,0,mid);
        int[] right = Arrays.copyOfRange(source,mid,source.length);

        return merge(mergeSort(left),mergeSort(right));
    }

    public int[] merge(int[] left,int[] right){
        int[] arr = new int[left.length+right.length];
        int i=0;
        while (left.length>0&&right.length>0){
            if (left[0]>right[0]){
                arr[i] = right[0];
                right = Arrays.copyOfRange(right,1,right.length);
            }else {
                arr[i] = left[0];
                left = Arrays.copyOfRange(left,1,left.length);
            }
            i++;
        }
        while (left.length>0){
            arr[i] = left[0];
            left = Arrays.copyOfRange(left,1,left.length);
            i++;
        }
        while (right.length>0){
            arr[i] = right[0];
            right = Arrays.copyOfRange(right,1,right.length);
            i++;
        }
        return arr;
    }

    /**
     * 快速排序
     * 思路：为队列选定一个基准值，将队列中比基准值小的放到左边，大的放到右边
     *  运用递归思想
     *
     * */

    public int[] quickSort(int[] source){
        //递归终结条件
        if (source.length<2){
            return source;
        }

        int mid = source.length/2;
        int[] left = Arrays.copyOfRange(source,0,mid);
        int[] right = Arrays.copyOfRange(source,mid,source.length);
        return source;
    }

    /*
    * 根据基准值，对两个序列进行位置替换
    * */

    public int[] quick(int[] arr,int index,int pivot){
        if (arr.length<2){
            return arr;
        }
        for (int i=index;i<arr.length;i++){
            if (arr[i] >pivot){

            }
        }
        return arr;

    }
 }

package daytest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lisong@mimidai.com
 * @date 2019/11/22 11:47
 * daydaytest,show me your code
 */
public class ElevenTwentyTwo {

    //TODO 思考一下Arrays.sort()的底层实现原理
    @Test
    public void BubbleTest(){
        //时间复杂度 O(N),空间复杂度O(1)
        Integer[] integers ={9,2,3,1,7,5,4,4};
        Integer temp;
        for (int i=1;i<integers.length;i++){
            Boolean flag=true;//设定一个标记，若为true，则表示此次循环没有进行交换，即待排序队列已排序完成
            for (int j=0;j<integers.length-1;j++){
                if (integers[j] >integers[j+1]){
                    temp = integers[j];
                    integers[j] = integers[j+1];
                    integers[j+1] = temp;
                    flag = false;
                }
            }
            if (flag){
                break; }
        }

        for (Integer integer:integers){
            System.out.println(integer);
        }



//        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(integers));



        /*list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });*/
//        for (Integer integer:list){
//            System.out.println(integer);
//        }

    }



    @Test
    public void test(){
        Integer[] integers ={9,2,3,1,7,5,4,4};

        Integer[] result = mergeTest(integers);
        for (Integer integer:result){
            System.out.println(integer);
        }
    }

    @Test
    public void test1(){
        /**
         * 合并两个有序数组，要求输出后顺序为由小至大
         *
         * */
        Integer[] integers ={9,2,3,1,7,5,4,4};
        Integer[] integers1 ={11,1,7,5,4,6};
        /*Integer[] integers1 ={1,2,3,4,5,6};
        Integer[] integers ={3,4,5,6,7,8,9};*/
        Integer[] result = mergeTest(integers);
        Integer[] result1 = mergeTest(integers1);
        Integer[] result2 = merge(result,result1);
        for (Integer integer:result2){
            System.out.println(integer);
        }
    }

    //归并排序
    /*
     * 申请空间，为两个已经排序序列之和，该空间用来存放合并后的序列
     * Integer[] integers ={9,2,3,1,7,5,4,4};
     * 时间复杂度  (O(nlogn))   空间复杂度O(n)
     * */

    public Integer[] mergeTest(Integer[] integers){
        if (integers.length<2){
            return integers;
        }
        int mid = integers.length/2;
        Integer[] left = Arrays.copyOfRange(integers,0,mid);
        Integer[] right = Arrays.copyOfRange(integers,mid,integers.length);
        return merge(mergeTest(left),mergeTest(right));

    }

    public Integer[] merge(Integer[] left,Integer[] right){
        Integer[] result = new Integer[left.length+right.length];
        int i=0;
        while (left.length>0&&right.length>0){
            if (left[0]<right[0]){
                result[i]=left[0];
                left = Arrays.copyOfRange(left,1,left.length);
            }else {
                result[i]=right[0];
                right = Arrays.copyOfRange(right,1,right.length);
            }
            i++;
        }
        while (left.length>0){
            result[i]=left[0];
            left = Arrays.copyOfRange(left,1,left.length);
            i++;
        }
        while (right.length>0){
            result[i]=right[0];
            right = Arrays.copyOfRange(right,1,right.length);
            i++;
        }

        return result;
    }


    /**
     * 计数排序(也可以叫下标排序)
     * 1、花O(n)的时间扫描一下整个序列 A，获取最大值 max
     * 2、开辟一块新的空间创建新的数组 B，长度为 ( max+ 1)
     * 3、遍历A数组，将A的值作为B对应下标，B当前下标对应值为此时A值出现次数
     * 4、A重新排序赋值
     * */

    @Test
    public void test2(){
        Integer[] integers ={9,2,3,1,7,5,4,4};

        Integer[] result = countSortTest(integers);
        for (Integer integer:result){
            System.out.println(integer);
        }
    }

    public Integer[] countSortTest(Integer[] integers){
        int length = getMax(integers)+1;
        Integer[] sup = new Integer[length];

        for (Integer integer:integers){
            sup[integer]=sup[integer]==null?0:sup[integer];
            sup[integer]++;
        }
        int sort = 0;
        for (int i=0;i<sup.length;i++){
            sup[i]=sup[i]==null?0:sup[i];
            while (sup[i]>0){
                integers[sort]=i;
                sort++;
                sup[i]--;
            }
        }

        return integers;
    }

    public Integer getMax(Integer[] integers){
        Integer max = integers[0];
        for (int i=1;i<integers.length;i++){
            if (integers[i]>max){
                max=integers[i];
            }
        }
        return max;

    }

    /**
     * 插入排序，类似于冒泡排序，时间复杂度O(n2)
     * 依次将每一个数插入新数组
     * */

    @Test
    public void test11(){
        int[] ints = new int[]{9,2,3,1,7,5,4,4};
        int[] resp = insetSort(ints);
        for (int i:resp){
            System.out.println(i);
        }
    }

    public int[] insetSort(int[] ints){
        if (ints.length<2){
            return ints;
        }
//        int[] resp = new int[ints.length];

        for (int i=1;i<ints.length;i++){
            int temp = ints[i];
            int j =i;
            while (j>0 && temp<ints[j-1]){
                ints[j] = ints[j-1];
                j--;
            }
            if (j!=i){
                ints[j]=temp;
            }
        }


        return ints;
    }

    @Test
    public void test12(){
        int[] ints = new int[]{9,2,3,1,7,5,4,4};
        int[] resp = quickSortBase(ints);
        for (int i:resp){
            System.out.println(i);
        }
    }

    /**
     * quickSort 快速排序
     * 首先确定一个基准值，将基准值小的排在左边，基准值大的排到右边，运用递归的思想（recursive），把两边的队列各自排好序
     * */
    public int[] quickSortBase(int[] source){
        int[] result = Arrays.copyOf(source,source.length);
        result = quickSortDetail(result,0,result.length-1);

        return result;
    }
    public int[] quickSortDetail(int[] source,int left,int right){
        if (left<right){
            int pivot = confirmPivot(source,left,right);
            quickSortDetail(source,0,pivot-1);
            quickSortDetail(source,pivot+1,right);
        }
        return source;
    }

    public int confirmPivot(int[] source,int left,int right){
        int pivot=left;
        int index = pivot+1;
        for (int i=index;i<=right;i++){
            if (source[i]<source[pivot]){
                int temp = source[index];
                source[index] = source[i];
                source[i] = temp;
                index++;
            }
        }
        //思考这一步的目的是什么呢
        int temp = source[pivot];
        source[pivot] = source[index-1];
        source[index-1] = temp;
        return index-1;
    }



    /**
     * 希尔排序
     * 对一个增量序列进行多趟排序，每趟排序，根据步长划分若干长度子序列，对子序列进行插入排序，增量因子为1时，整个序列作为一个表处理
     * */

    public int[] sheelSort(int[] source){
        int gap = 1;
        while (gap < source.length){
            gap = gap*3+1;
        }
        while (gap>0){

        }
        ReentrantLock lock = new ReentrantLock();
        ReadWriteLock readWriteLock = new ReadWriteLock() {
            @Override
            public Lock readLock() {
                return null;
            }

            @Override
            public Lock writeLock() {
                return null;
            }
        };
        ReentrantReadWriteLock reentrantReadWriteLock =
            new ReentrantReadWriteLock();
        return source;
    }

    /**
     * 桶排序
     * */

}

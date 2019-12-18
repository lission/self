package daytest;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author lisong@mimidai.com
 * @date 2019/12/3 19:31
 */
public class TwelveFore {
    
    @Test
    public void test(){
        int[] ints ={9,2,3,1,7,5,4,4};
        
        int[] result = mergeTest(ints);
        for (int i:result){
            System.out.println(i);
        }
    }
    /**
     * bubbleSort
     * */
    public int[] bubbleSort(int[] ints){
        if (ints.length<2){
            return ints;
        }
        int temp = 0;
        for (int i=1;i<ints.length;i++){
            Boolean flag = true;
            for (int j=0;j<ints.length-1;j++){
                if (ints[j] >ints[j+1]){
                    temp = ints[j];
                    ints[j] = ints[j+1];
                    ints[j+1] = temp;
                    flag =false;
                }
            }
            if (flag){
                break;
            }
        }
        return ints;
    }
    /**
     * mergeTest
     * */
    public int[] mergeTest(int[] ints){
        if (ints.length<2){
            return ints;
        }
        int mid = ints.length/2;
        int[] left = Arrays.copyOfRange(ints,0,mid);
        int[] right = Arrays.copyOfRange(ints,mid,ints.length);
        return mergeFunc(mergeTest(left),mergeTest(right));
    }
    
    public int[] mergeFunc(int[] left,int[] right){
        int[] arr = new int[left.length+right.length];
        
        int i=0;
        while (left.length>0 && right.length>0){
            if (left[0] >right[0]){
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
    
    @Test
    public void test1(){
        int[] ints ={9,2,3,1,7,5,4,4};
        
        int[] result = hillSort(ints);
        for (int i:result){
            System.out.println(i);
        }
    }
    
    public int[] hillSort(int[] arr){
        int gap=1;
        while (gap <arr.length){
            gap = gap*3+1;
        }
        while (gap>0){
            for (int i=gap;i<arr.length;i++){
                int temp=arr[i];
                int j=i-gap;
                while (j>=0&&arr[j]>temp){
                    arr[j+gap]=arr[j];
                    j=j-gap;
                }
                arr[j+gap]=temp;
            }
            gap=gap/3;
        }
        return arr;
    }
    
    
    
}

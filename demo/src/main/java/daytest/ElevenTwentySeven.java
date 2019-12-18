package daytest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author lisong@mimidai.com
 * @date 2019/11/27 20:15
 */
public class ElevenTwentySeven {
    @Test
    public void test(){
        int[] ints = new int[]{9,2,3,1,7,5,4,4};
        int[] resp = countSort(ints);
//        int[] resp = bubleTest(ints);
//        int[] resp = sort(ints);
        for (int i:resp){
            System.out.println(i);
        }
    }
    
    public int[] bubleTest(int[] ints){
        int[] resp = Arrays.copyOf(ints,ints.length);
        int temp =0;
        for (int i=1;i<resp.length;i++){
            Boolean flag = true;
            for (int j=0;j<resp.length-1;j++){
                if (resp[j]>resp[j+1]){
                    temp = resp[j];
                    resp[j]=resp[j+1];
                    resp[j+1]=temp;
                    flag = false;
                }
                
            }
            if (flag){
                break;
            }
            
        }
        for (int i:ints){
            System.out.println(i);
        }
        System.out.println("_________________________");
        return resp;
    }
    
    /**
     * 归并排序： 对两个有序数组进行合并
     * 单个数组的话，先进行拆分，然后排序
     *
     */
    
    public int[] sort(int[] ints){
        int[] resp = Arrays.copyOf(ints,ints.length);
        if (ints.length<2){
            return resp;
        }
        int mid = ints.length/2;
        int[] left = Arrays.copyOfRange(resp,0,mid);
        int[] right = Arrays.copyOfRange(resp,mid,resp.length);
        return merge(sort(left),sort(right));
    }
    
    public int[] merge(int[] left,int[] right){
        int[] resp = new int[left.length+right.length];
        int i = 0;
        while (left.length >0&&right.length>0){
            if (left[0]<right[0]){
                resp[i]=left[0];
                left = Arrays.copyOfRange(left,1,left.length);
            }else {
                resp[i]=right[0];
                right = Arrays.copyOfRange(right,1,right.length);
            }
            i++;
        }
        while (left.length>0){
            resp[i]=left[0];
            left = Arrays.copyOfRange(left,1,left.length);
            i++;
        }
        while (right.length>0){
            resp[i]=right[0];
            right = Arrays.copyOfRange(right,1,right.length);
            i++;
        }
        return resp;
    
    }
    
    
    /**
     * 计数排序（O(n+k)）
     * 思路，找到原数组ints中的最大值m，生成一个以m为容量大小的新数组resp，
     * 遍历ints，把ints每个位置的值当做resp的下标，resp存储当前下标在ints出现的次数
     * */
    
    public int[] countSort(int[] ints){
        int max = ints[0];
        for (int i=1;i<ints.length;i++){
            if (max<ints[i]){
                max=ints[i];
            }
        }
        max = max+1;
        int[] resp =new int[max];
        for (int j=0;j<ints.length;j++){
            int index = ints[j];
            resp[index]++;
        }
        int[] ints1 =new int[ints.length];
        int sort=0;
        for (int i=0;i<resp.length;i++){
            while (resp[i]>0){
                ints1[sort]=i;
                resp[i]--;
                sort++;
            }
        }
        return ints1;
    }
    
    @Test
    public void test11(){
    
    }
    
    /**
     * 指定位置插入
     * */
    public int[] insert(int[] ints,int element,int index){
        if (ints.length <index || index <0){
            System.out.println("参数异常");
            return ints;
        }
        for (int i=ints.length-2;i>index;i--){
            ints[i+1] = ints[i];
        }
        ints[index] = element;
        return ints;
    }
}

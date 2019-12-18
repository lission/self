package com.leetcode;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author lisong@mimidai.com
 * @date 2019/11/5 14:10
 */
public class Solution191105 {
    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成
     * */

    @Test
    public void test(){
        int[] nums = new int[]{1,1,2};
        int i = removeDuplicates(nums);
        System.out.println(i);
    }

    public int removeDuplicates(int[] nums){
        if (nums == null){
            return 0;
        }
        int j = 0;
        for (int i = 1;i<nums.length;i++){
            if (nums[i] != nums[j]){
                j++;
                nums[j] = nums[i];
            }
        }
        System.out.println(nums.toString());
        return j+1;

    }

    /**
     * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * */
    public int removeElement(int[] nums,int val){
        if (nums == null){
            return 0;
        }
        int i = 0;
        for (int j =0;j<nums.length;j++){
            if (nums[j] != val){
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /**
     * 实现 indexOf() 函数
     * 定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
     * 如果不存在，则返回  -1
     * */

    @Test
    public void test1(){
        String s1 = "mississippi";
        String s2 = "pi";
        int i = indexOfSelf(s1,s2);
        System.out.println(i);
    }
    public int indexOfSelf(String haystack,String needle){
        /*if (StringUtils.isEmpty(haystack) || StringUtils.isEmpty(needle)){
            return 0;
        }*/
        if (haystack == null  || needle == null ){
            return 0;
        }
        if (haystack.length() == 0 && needle.length() == 0){
            return 0;
        }
        int nLength = needle.length();
        if (haystack.length() < nLength){
            return -1;
        }
        if(haystack.length() == nLength) {
            if (haystack.equals(needle)){
                return 0;
            }else {
                return -1;
            }
        }
        int j = -1;
        for (int i = 0;i<=haystack.length()-nLength;i++){
            if (haystack.substring(i,i+nLength).equals(needle)){
                j = i;
                break;
            }
        }
        return j;

    }

    /**
     * KMP 字符串匹配算法
     * 特点：KMP 算法永不回退 txt 的指针 i，不走回头路（不会重复扫描 txt），而是借助 dp 数组中储存的信息把 pat 移到正确的位置继续匹配
     * 难点：如何根据pat构建动态规划db数组
     * 本次按照二维数组实现 db[j][c],要确定状态转移的行为，必须明确两个变量，一个是当前的匹配状态，另一个是遇到的字符
     * 作者：laluladong
     * */

    @Test
    public void test2(){
        String haystack = "hello";
        String needle = "ll";
        int i = indexOfSelf(haystack,needle);
        System.out.println(i);
        KMP kmp = new KMP(needle);
        System.out.println(kmp.search(haystack));
    }

    class KMP{
        private String pat;
        private int[][] db;

        public KMP(String pat) {
            this.pat = pat;
            /*
            * 初始化 db数组
            * */
            int M = pat.length();
            db = new int[M][256];
            db[0][pat.charAt(0)] = 1;
            int X = 0;//影子状态
            for (int j=1;j<M;j++){
                for (int c=0;c<256;c++){
                    if (pat.charAt(j) == c){
                        //状态进1;
                        db[j][c] = j+1;
                    }else {
                        db[j][c] = db[X][c];//回退到影子状态值
                    }
                }
                //更新影子状态
                X = db[X][pat.charAt(j)];
            }
        }

        public int search(String txt){
            int N = txt.length();
            int M = pat.length();
            int j =0;
            for (int i=0;i<N;i++){
                j=db[j][txt.charAt(i)];
                if (j==M){
                    return i-M+1;
                }
            }
            return -1;
        }

    }
    /**
     * 字符串匹配算法： BM 算法、Sunday 算法、KMP 算法并不能算是最优的算法
     * */

    /**
     * 经典KMP算法
     * KMP 算法是一种字符串匹配算法，由 D.E.Knuth，J.H.Morris 和 V.R.Pratt 提出的，因此人们称它为克努特—莫里斯—普拉特算法（简称KMP算法）
     * KMP 算法目的就是：在出错时，利用原有的匹配信息，尽量减少重新匹配的次数。
     * 确定有限状态机(Finite State Machine):当前状态 + 匹配字符 = 目标状态
     * 孪生词缀，即前后缀内容相同而不相等，而 a 这个前缀/后缀就叫做当前状态的孪生词缀状态
     * 当前状态 + 匹配失败字符 = 孪生词缀状态 + 匹配字符 = 目标状态（降级或重置）
     * 为构建状态机，我们需要为每一个状态找到相应的孪生词缀状态（设 X 代表孪生词缀状态），然后借助 X 判断匹配失败后是重置还是降级
     *
     * 作者：zoharyips
     * 链接：https://leetcode-cn.com/problems/implement-strstr/solution/shen-ru-qian-chu-kmp-suan-fa-yu-ping-jie-by-zohary/
     * */

    /**
     * 构建状态机：
     *  1、声明状态机和 X；因为还没读取 pattern 串，所以默认所有目标状态都为 0；
     *  2、更新状态机的每一列和更新每一个状态的孪生词缀状态
     * */

    /**
     * KMP算法评价
     * 典型的牺牲空间换取时间的算法
     * 1、目的：减少重新匹配次数，让主串遍历永不回头
     * 2、方法：通过利用已有的匹配信息，借助已匹配的前缀与后缀关系，在重新匹配时跳过已有的前缀
     * 3、适用：pattern 串的中间必须出现与其前缀相同的内容，这个算法才能够派上用场，出现重复的越多，就越有价值，因此像匹配 橡胶橡胶、chop-chop、恍恍惚惚、win-win 这种叠词较为适合，而最为适合的情景是匹配二进制串（都是 0101 的重复）
     * 4、缺陷：对于长句而言，状态机所占用的空间是巨大的，而并不高效
     * */

    public int[][] buildFSM(String pattern){
        // 除去最终状态，状态机有pattern.length()种输入状态，默认遇到256个字符，表格所有成员初始值为0
        int[][] FSM = new int[pattern.length()][256];
        //最初所有状态的孪生词缀状态未知，默认为0
        int X = 0;
        for (int i=0;i<pattern.length();i++){
            int match = pattern.charAt(i);//当前能成功匹配的字符
            for (int c=0;c<256;c++){
                //当前状态+匹配失败字符=孪生词缀状态+匹配字符=目标状态
                FSM[i][c] = FSM[X][c];
            }
            // 当前状态 + 匹配成功字符 = 升级状态
            FSM[i][match] = i+1;
            if (i>0){
                /*
                 * 零或一状态是不可能有孪生词缀，因此不更新 0 状态的孪生词缀状态，
                 * 但 1 状态匹配出错，匹配到第一个字符会回到 1 状态，因此需要更新。
                 *
                 * 当前状态有孪生词缀状态的话，X 状态就是当前状态的前缀和后缀，
                 * 当前状态遇到 match 会升级到下一个状态，X + match 一定是下一状态的后缀，
                 *
                 * 如果 X 遇到 match 能升级，就表示 X + match 一定是下一个状态的前缀，那么 X + match 是下一状态的孪生词缀状态；
                 * abab = aba + b；X(ab) = X(a) + b;
                 * 如果无法升级，就表示 X + match 不是下一个状态的前缀，那么 X + match 就要降级或者重置状态了；
                 * ababc = abab + c; X(0) = X(ab) + c;
                 *
                 * 所以： 下一状态的孪生词缀状态 = 当前状态的孪生词缀状态 + 当前状态的更新字符
                 */
                X = FSM[X][match];
            }
        }

        return FSM;
    }

    public int strStr(String haystack, String needle) {
        int strLen = haystack.length(), subLen = needle.length();
        if (subLen == 0) {
            return 0;
        }
        if (strLen == 0) {
            return -1;
        }
        // 构建状态机
        int[][] FSM = buildFSM(needle);
        // 匹配子串
        int state = 0;
        for (int i = 0; i < strLen; i++) {
            state = FSM[state][haystack.charAt(i)];
            if (state == subLen) {
                return i - subLen + 1;
            }
        }
        return -1;
    }

}

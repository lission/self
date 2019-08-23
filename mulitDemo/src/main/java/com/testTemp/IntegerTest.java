package com.testTemp;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lisong@mimidai.com
 * @date 2019/4/17 14:32
 */
public class IntegerTest {
    @Test
    public void compare(){
        List<String> list = new ArrayList<String>(Arrays.asList("151666666666","16504620608","17157718028"));
        for (int i = 0;i<list.size();i++){
            System.out.println("当前手机号"+i+",格式化结果"+checkCellphone(list.get(i)));
        }
    }




    /**
     * 添加用户电话判断，拼接条件 必然是电话，否则移除
     *
     * 验证手机号码
     *
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147、182
     * 联通号码段:130、131、132、136、185、186、145
     * 电信号码段:133、153、180、189、177
     *
     * @param cellphone
     * @return
     */
    private boolean checkCellphone(String cellphone) {
        if(StringUtils.isBlank(cellphone)){
            return false;
        }
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,1,2,5-9])|(177))\\d{8}$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(cellphone);
        return matcher.matches();
    }
}

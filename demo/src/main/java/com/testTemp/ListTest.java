package com.testTemp;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import utils.RSAUtils;

import java.security.PublicKey;
import java.util.*;

/**
 * @author lisong@mimidai.com
 * @date 2018/11/6 14:41
 */
public class ListTest {

    @Test
    public void orderTest(){
        List<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        List<Integer> result = new ArrayList<>();
        Integer integer = 3;

        //小于等于
        for (Integer i:list) {
            if (i <= integer){
                result.add(i);
            }
        }
        //大于等于
        for (Integer i:list) {
            if (i > integer){
                result.add(i);
            }
        }



        System.out.println(result);
    }

    @Test
    public void orderTest1(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        List<Integer> result = new ArrayList<>();
        Integer integer = 3;

        //大于
        for (Integer i:list) {
            if (i > integer){
                result.add(i);
            }
        }
        //小于等于
        for (Integer i:list) {
            if (i <= integer){
                result.add(i);
            }
        }
        System.out.println(list.contains(4));



        System.out.println(result);
    }

    @Test
    public void characterTest(){
        List<String> a = new LinkedList();
        List<String> b = new ArrayList<>();
    }
    @Test
    public void stringTest(){
        String t = "YB20181114151723";
        System.out.println(t.substring(0,15));
    }

    @Test
    public void stringKeyTest(){
        String t = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDNbabK5o1JzcH2NBfSGGw+6oUMl0d8gYne51SY9LKxcG4vNT/Nzt4wE/WlsfFswjHf6r39MGTiJVtpLykkOPmMI1gQvOJGOZWnYRuRNLHkCymGP4xVwWG0W2SCmqk9NXnT8stPX9/RnmhSnwX3UoIQT41Z3N1eG2nhY4sPku23Jv1YXSOCZUDWhCjZcCeRoJa3Y17owLR+vaFMpoFs7/1yVEaI2g5wGaqZkf4MWmJhQpfnHUzi7dPg8j2jv57fXL7Pr2mdHM9DUaI5ZbBqmBJo6ovzF3ibfL9yQjmwSFQ9Rvsly9BifSXbMkK3u3/JfDZnSPF6912UEL+AoarbUcvRAgMBAAECggEBAIUkiHIBM5meDhRwdu/ofF/ayBj9A4wiuSULcb0g+6AdaoyWSqAtb7xhc1jNF9iRlJm4HyM2dmxMIg7uTg/4DWVSkwVmJpG1SNWPDrryGEnhIiysi1wZViX7Bg3QB9hwDGHefv8W+I13yc2i288MXtgLd2XCzIbt5ZX7WmnhpMiLB2dJMXzQfve2XaUDqtQt5EISDMiWXNhkfQh6LHtuoafKlHs/3iIbb2YFkst6BEYCnuIMUWLHj5lfj7gXA8/ZKFeaiOdkEtEwmOqfm/yuJ5447XQfQbTVENOF5IxWojDMPztms6vQ528Ibtb2C7gYsTWd/q7ShuIvSLBZ6UNehfECgYEA6A7XV0/l+rxYdKWfdbrD39ll4DCn6Zb6LJir9JG1Bg3KpJkjJ5Ux7HHn/0Qck/hL3mZhuJcgS1lJnXJTRajuOiQxW8x/X1yAoKDPeZ/fbvROUJ4ysf6CXzmIzMcbpmp3Zb/VXQNZn5VYR7ITrTZYat3+BX6jgTiHGCMoANWeNnMCgYEA4p92jpDXxk5OxcGuahh6pDU/DQLcLVg9/KQGMD+3eevmr8+4rWsoDZrw/NK5MPAqpbTVmE2tdKjWA5dh6RH/xCQWBqrbPGyLqOJgm1fucfXGtid4bLcFk3Jr/bA8q9meYSixe+oqInFwZsmMtZWYqVZuHgdD5CPcIhNgY7dmn6sCgYEAy1v2KnXCPLDBc7eQvkMlVA3X0VfSTkoYguxoBLOLx/peE1CAFjgQv/wyKuktgh6dnhBqorENg/qDII8Hmown+JuvBZt4I8GUly9VomgQDdParZt1Iba/2WM6FvqFqJi0eV5bRF+jMs5Vxr6vr6NoaH6SFY6dCqjW+rgUXmbVdzECgYEAq50wbaPYEu8oDe+uwd9JuGIuHg9FZv1E67XWjeEicyymZAr8gi78hNkJl5uH7DxjP1siyRRBv28/qjaQSax8sC7XkzL9lNNqK3lKyTicdCY31acsNb+fdFoY12MHfufsXQ0w4nKgd7bJ9Ly6VqQzmEDAYPMFgZV6VT+xJbIz/8MCgYBp6i/7Vxd6z2hxOIAX1jIftlOjsIhuB6N1cq2cnVDmTNWd3T746VuVFTNNW2wmAtkb05D3CQn8SMsvYE4PawkRxARwdX8k1/yh/OivK1g/0SWSPHfsRQY8FZCiidkRBTANvZG72L/3/2o4zj7CLSeY9xK4btUm3vbWkX8KfhWSIg==";
        PublicKey publicKey = RSAUtils.getPublicKey(t);
        System.out.println(publicKey);
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(3);
        treeSet.add(4);
        System.out.println(treeSet);
    }

    @Test
    public void subTest1(){
        List<Integer> list = new ArrayList<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        System.out.println(list);
        /*Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());

        }*/

    }

    @Test
    public void vectorTest(){
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        vector.add(4);
        System.out.println(vector);
    }

    @Test
    public void setTets(){
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    @Test
    public void initTest(){
        TerminalInfo terminalInfo = new TerminalInfo();
        System.out.println(terminalInfo.toString());
        String str = JSONObject.toJSONString(terminalInfo);
        System.out.println(terminalInfo.getMerchant_custom());
        System.out.println(JSONObject.toJSONString(terminalInfo));
    }
}

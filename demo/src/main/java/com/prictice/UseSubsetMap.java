package com.prictice;



import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author tianfuxian
 * @Description:
 * @Date: 2019-05-24 15:19
 */
public class UseSubsetMap {
    public List<Phone> getUseSubSetMap(Map<String, String> sourceMap, Integer n, Long nb) {
        Map<String, List<String>> userIdListPhoneMap = new HashMap<>();

        sourceMap.forEach((k, v) -> {
            String[] split = v.split(",");
            List<String> splitList = Arrays.asList(split);
            if (splitList.size() >=n) {
                userIdListPhoneMap.put(k, splitList);
            }
        });
        sourceMap.clear();

        //单字段listPhone
        List<String> groupByList = new ArrayList<>();

        userIdListPhoneMap.forEach((k, v) ->
                v.forEach(groupByList::add));

        //电话号码 和 出现次数 map
        Map<String, Long> phomeCountMap = groupByList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        //中间公共元素结果集
        List<String> resStringList = new ArrayList<>();

        phomeCountMap.forEach((k, v) -> {
            if (v >= nb) {
                resStringList.add(k);
            }
        });
        phomeCountMap.clear();
        //过滤公共元素的map
        Map<String, List<String>> filterLongListMap = new HashMap<>();

        userIdListPhoneMap.forEach((k, v) -> {
            List<String> resFilterList = new ArrayList<>();
            v.forEach(s -> {
                resStringList.forEach(resString -> {
                    if (resString.equals(s)) {
                        resFilterList.add(resString);
                    }
                });
            });
            if (resFilterList.size() >= n) {
                filterLongListMap.put(k, resFilterList);
            }
        });
        resStringList.clear();
        //释放内存
        userIdListPhoneMap.clear();

        //求子集后获取的全量list 中间结果集list
        List<Phone> finallyPhoneList = new ArrayList<>();

        filterLongListMap.forEach((k, v) -> {
            if(v.size()>=20) {
                System.out.println("超长子集长度为--->"+ v.size());
                List<Phone> resSubset = this.getSubset(v.subList(0,20), n, k);
                resSubset.forEach(finallyPhoneList::add);
            }else{
                List<Phone> resSubset = this.getSubset(v, n, k);
                resSubset.forEach(finallyPhoneList::add);
            }
        });
        filterLongListMap.clear();

        //以手机号分组获取到的map
        Map<String, List<Phone>> phoneListMap = finallyPhoneList.stream()
                .collect(Collectors.groupingBy(Phone::getPhoneString));

        finallyPhoneList.clear();

        //打算排序的list
        List<Phone> sortList = new ArrayList<>();

        //生成数组UserId    String类型输出
        phoneListMap.forEach((k, v) -> {
            StringBuilder resUserId = new StringBuilder("");
            for (Phone phone : v) {
                resUserId.append(phone.getUserId());
                resUserId.append(",");
            }
            resUserId.append("-9999");
            Phone resPhone = new Phone(resUserId.toString(), v.get(0).getPhoneString(), v.get(0).getPhoneSize(), v.size());
            sortList.add(resPhone);
        });
        phoneListMap.clear();
        LinkedHashMap<String, List<Phone>> groupByUserIdLinkedMap = sortList.stream()
                .collect(Collectors.groupingBy(Phone::getUserId, LinkedHashMap::new, Collectors.toList()));
        sortList.clear();
        List<Phone> phoneList = new ArrayList<>();
        groupByUserIdLinkedMap.forEach((k,v)->{
            phoneList.add(v.get(0));
        });
        groupByUserIdLinkedMap.clear();
        //倒序 人数，手机号码数
      //  ListUtils.sort(phoneList, false, "userIdSize", "phoneSize");
        phoneList.sort(
                Comparator.comparing(Phone::getUserIdSize)
                        .reversed()
                        .thenComparing(Comparator.comparing(Phone::getPhoneSize)
                                .reversed())
        );
        return phoneList;
    }

    private List<Phone> getSubset(List<String> stringList, Integer n, String userId) {
        System.out.println("子集长度--------------》" + stringList.size());
        List<Phone> listList = new ArrayList<>();
        //元素个数
        int length = stringList.size();

        //非空子集个数
        int num = (2 << stringList.size() - 1) - 1;

        IntStream.range(1, num + 1).forEach(i -> {
            List<String> resStringList = new ArrayList<>();
            //暂存正在判断第i种可能
            int now = i;
            for (int j = 0; j < length; j++) {
                //为1时表示该元素已经存在
                if ((now & 1) == 1) {
                    //数组中就这几个数配
                    resStringList.add(stringList.get(j));
                }
                //依次判断下一位
                now = now >> 1;
            }
            if (resStringList.size() >= n) {
                Collections.sort(resStringList);
                String ss = Arrays.toString(resStringList.toArray());
                Integer phoneSize = resStringList.size();
                Phone phone = new Phone(userId, ss, phoneSize, 0);
                listList.add(phone);
                ss = null;
                phone = null;
                phoneSize = null;
            }
            resStringList.clear();
        });

        return listList;

    }
    // 把json格式的字符串写到文件
    public boolean writeFile(String filePath, String sets) {
        FileWriter fw;
        try {
            fw = new FileWriter (filePath);
            PrintWriter out = new PrintWriter(fw);
            out.write(sets);
            out.println();
            fw.close();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}



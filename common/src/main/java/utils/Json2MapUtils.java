package utils;
/**
 * This file created by mengqingyi on 2017-12-15.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类文件注释(Class file)
 *
 * @author mengqingyi
 * @classDescription 复杂json转map 但是需要注意 json中的键对应到map中不可重复
 * @create 2017-12-15 17:03
 **/
public class Json2MapUtils {
    /**
     * testTemp 转为 map
     *
     * @param json chuanru 标准的json串
     */
    public static Map json2Map(String json) {
        Map map = new HashMap();
        List<Map<String,Object>> list = new ArrayList<>();
        map.put("listMap",list);
        JSONObject jsonObject = JSONObject.parseObject(json);
        parseJson2MapNew(jsonObject, map);
        return map;
    }

    /**
     * 将 jsonObject 分析填装到 map
     * 最外层
     * @param jsonObject 传入的jsonObject
     * @param map        返回的map
     * @return 返回map
     */
    private static Map<String, Object> parseJson2MapNew(JSONObject jsonObject, Map map) {
        //JSONObject 第 N 层
        int index = 0;
        Map subMap = new HashMap();
        List<Map<String,Object>> listMap = new ArrayList<>();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            //转换json第N+1层
            String key = entry.getKey();
            Object value = entry.getValue();
            Object o = JSON.toJSON(value);
            if (o instanceof JSONObject) {
                //内层还是map结构类型
                parseJson2MapNew((JSONObject) o, subMap);
                map.put(reName(key, map), subMap);
            } else if (o instanceof JSONArray) {
                //内层是 list结构类型
                singleArray2Map((JSONArray) o, map);
                map.put(reName(key, map), listMap);
            } else {
                //普通类型
//                System.out.println("普通类型+"+key);
                map.put(reName(key, map), value);
            }
        }
        System.out.println("最外层循环次数+"+index);
        return map;
    }

    /**
     * 将 jsonObject 分析填装到 map
     * 最外层
     * @param jsonObject 传入的jsonObject
     * @param map        返回的map
     * @return 返回map
     */
    private static Map<String, Object> parseJson2Map(JSONObject jsonObject, Map map) {
        //JSONObject 第 N 层
        int index = 0;
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            index++;
            //转换json第N+1层
            String key = entry.getKey();
            Object value = entry.getValue();
            Object o = JSON.toJSON(value);
            if (o instanceof JSONObject) {
                //内层还是map结构类型
                singleJson2Map((JSONObject) o, map);

            } else if (o instanceof JSONArray) {
                //内层是 list结构类型
                singleArray2Map((JSONArray) o, map);
            } else {
                //普通类型
                map.put(reName(key, map), value);
            }
        }
        System.out.println("最外层循环次数+"+index);
        return map;
    }


    /**
     * 将 array转为 map 进行保存
     *
     * @param jsonArray 传入的array
     * @param map       传入map
     * @return 返回map
     */
    private static Map parseArray2Map(JSONArray jsonArray, Map map) {
        int index = 0;

        for (Object o : jsonArray) {

            o = JSON.toJSON(o);
            if (o instanceof JSONObject) {
                //内层还是map结构类型
                parseJson2Map((JSONObject) o, map);
            } else if (o instanceof JSONArray) {
                //内层是 list结构类型
                singleArray2Map((JSONArray) o, map);
            } else {
                //普通类型
                map.put(reName(index + "", map), o);
            }
            index++;
            System.out.println("第几层+"+index+",存储结果+"+map);
            System.out.println("第几层+"+index+",元数据+"+o);
        }
        System.out.println("循环你次数"+index);
        return map;
    }

    /**
     * 将 jsonObject 分析填装到 map
     * 最外层
     * @param jsonObject 传入的jsonObject
     * @param map        返回的map
     * @return 返回map
     */
    private static Map<String, Object> singleJson2Map(JSONObject jsonObject, Map map) {
        Map<String,Object> subMap = new HashMap();
        //JSONObject 第 N 层
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            //转换json第N+1层
            String key = entry.getKey();
            Object value = entry.getValue();
            Object o = JSON.toJSON(value);
            if (o instanceof JSONObject) {
                //内层还是map结构类型
                singleJson2Map((JSONObject) o, subMap);
                map.put(reName(key, map), subMap);
            } else if (o instanceof JSONArray) {
                //内层是 list结构类型
                singleArray2Map((JSONArray) o, subMap);
            } else {
                //普通类型
//                System.out.println("普通类型+"+key);
                subMap.put(reName(key, subMap), value);
            }
        }
        return map;
    }

    /**
     * 将 array转为 map 进行保存
     *
     * @param jsonArray 传入的array
     * @param map       传入map
     * @return 返回map
     */
    private static Map singleArray2Map(JSONArray jsonArray, Map map) {
        int index = 0;
        Map<String,Object> subMap = new HashMap();
        List<Map<String,Object>> listMap = new ArrayList<>();
        for (Object o : jsonArray) {
            o = JSON.toJSON(o);
            if (o instanceof JSONObject) {
                //内层还是map结构类型
                singleJson2Map((JSONObject) o, subMap);
                map.put(reName(index+"", map), subMap);
            } else if (o instanceof JSONArray) {
                //内层是 list结构类型
                singleArray2Map((JSONArray) o, subMap);

                map.put(reName(index + "", map), subMap);
            } else {
                //普通类型
                subMap.put(reName(index + "", subMap), o);
            }
            index++;

            System.out.println("s第几层+"+index+",元数据+"+o);
        }
        System.out.println("s第几层+存储结果+"+subMap);
        System.out.println("s循环你次数"+index);
        return map;
    }
    /**
     * 检查 map中是否已有 该 key值，如有则重命名。避免数据覆盖
     *
     * @param key map的键名
     * @param map 传入的map
     * @return key+_+随机数+_+当前时间戳
     */
    private static String reName(String key, Map map) {
        StringBuilder name = new StringBuilder();
        name.append(key);
        int index = 0;
        long timeStamp = Instant.now().getEpochSecond();
        for (Object o : map.keySet()) {
            index = (int)(Math.random()*1000);
            if (key.equals(o.toString())) {
                name.append(key);
                name.append("_");
                name.append(index);
                name.append("_");
                name.append(timeStamp);
            }
        }
        return name.toString();
    }

    public static void main(String[] args) {
        String js = "{\n" +
                "\t\"code\": \"0\",\n" +
                "\t\"msg\": \"查询还款信息成功\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"count\": 0,\n" +
                "\t\t\"user\": {\n" +
                "\t\t\t\"name\": \"徐春颖\",\n" +
                "\t\t},\n" +
                "\t\t\"repayList \": [{\n" +
                "\t\t\t\t\"amount\": 1000.00,\n" +
                "\t\t\t\t\"overdueStatus\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"amount\": 1200.00,\n" +
                "\t\t\t\t\"overdueStatus\": 1\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"amount\": 1300.00,\n" +
                "\t\t\t\t\"overdueStatus\": 2\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}\n" +
                "}";
        Map maps = (Map) JSON.parse(js);
        Map re= JSON.parseObject(js);

        System.out.println(maps.size());
        System.out.println(maps);
        System.out.println(maps.get("data"));
        System.out.println((Map)maps.get("data"));

        System.out.println(re.size());
        System.out.println(re);
        System.out.println(re.get("data"));
        System.out.println(maps.get("user"));
    }
}
